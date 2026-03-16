Necessario avere: **NVIDIA Container Toolkit**.

Server:
- **cartella datasets** (qui datasets condivisi ) questa cartella viene montata read only nei containers
	- dati grezzi: immagini con le labels, file di testo .txt etichettati con le labels o solo senteces per fare embedding
- **cartella home students** cartelle studenti dove si crea il container 
- **cartella jobs** coda job training
- **cartella scripts training** job worker accesso GPU -> se si prevede GPU worker questo è un container separato

Esempio:
/srv/datasets
	- cats_dogs
	- recycling
	- film_review
/srv/students
	- \<classe\>_\<progetto\>_<\cognome_studente_responsabile\> 
	- \<classe\>_\<progetto\>_<\cognome_studente_responsabile\>
	-\<classe\>_\<progetto\>_<\cognome_studente_responsabile\>

Ogni gruppo di studenti (o studente) riceve un container separato e dentro il container vede:
- home/\<classe\>_\<progetto\>_<\cognome_studente_responsabile\>/work
- /datasets (read-only)

Lo studente può:
- leggere dataset
- salvare notebook nella sua cartella
- **non può cancellare / creare dataset**
- ha un container con JupyterLab e SOLO CPU! NO GPU!
- vede la sua cartella di lavoro

Per avviare un training lo studente deve:
- leggere la guida
- superare un test? 
- **da pensare bene** non può avviare direttamente training, deve passare da 
	- script copiato in read only che lancia training (lo scrive root user): !python train_gpu.py e questo viene richiamato nel notebook. **Questo script richiama un sh che accoda il lavoro per gestire la GPU impegnata**
	- niente notebook proprio e carica files .npy e modello rete neurale e si avvia training con un uno scheduler
	- si notebook per fare pre-processing nel server (crea nel server i file .npy da dati grezzi) ma container non vede GPU 


Il container deve
- limitare uso CPU
- non dare uso GPU 
- limitare uso RAM
- no accesso a shell da Jupyter Notebook

## Worker GPU
Nel container GPU gira uno script continuo.

while True:

    jobs = lista file /jobs

    se esiste job:
        prendi il primo
        esegui training
        salva risultato
        elimina job
import os
import time
import json

while True:

    jobs = os.listdir("/jobs")

    if len(jobs) > 0:

        job_file = jobs[0]

        with open("/jobs/" + job_file) as f:
            config = json.load(f)

        run_training(config)

        os.remove("/jobs/" + job_file)

    time.sleep(5)

**Se usiamo job gli studenti non devono vedere script di training ma alla fine salvano solo un json nei jobs in modo che train_gpu.py sappia da dove partire! e no .sh script richiamato in train_gpu.py**
## Gestione training - scheduler e train_gpu.py
!python run_training.py config.json

Dentro run_training decidiamo noi 

- GPU queue
- dimensione batch
- numero epoche
- tempo massimo
- si deve salvare modello in una dir data dagli studenti

Nel file .json si specificano le info
MAX_EPOCHS = 20

if config["epochs"] > MAX_EPOCHS:
    raise Exception("Troppe epoche")

Cosa fa questo script?
1. Chiama wait_gpu.sh → aspetta se la GPU è occupata
    
2. Controlla i parametri di config.json → es. batch troppo grande? → errore
    
3. Limita memoria GPU in PyTorch
    
4. Lancia il training vero e proprio
    
5. Scrive log su chi ha lanciato cosa

### Idee per limitare uso GPU

1. Da codice stesso con librerie deep learning

	import torch
	torch.cuda.set_per_process_memory_fraction(0.5)
2. fare uno script che cerca le funzioni delle librerie di deep learning che lanciano i trainings
	
	```python
	python validate_notebook.py student_notebook.ipynb
	```
	

### Gestione Logging

i**TO DO** si deve avere un log negli script per capire cosa succede in caso di problemi...  
in parte sarà fatto nello script root che è l'unico da cui si lanciano i trainings
## Approvazione - creazione progetto studenti

All'approvazione si dovrà creare cartelle nel server e container...



```sh
# Crea il container
pct create 100 local:vztmpl/ubuntu-22.04-standard_22.04-1_amd64.tar.zst \
  --hostname jupyter-alice \
  --cores 2 \
  --memory 4096 \
  --rootfs local-lvm:10 \
  --net0 name=eth0,bridge=vmbr0,ip=dhcp \
  --mp0 /srv/students/alice,mp=/home/jovyan/work \
  --mp1 /srv/datasets,mp=/datasets,ro=1 \
  --unprivileged 1

# Avvia
pct start 100

# Installa Jupyter + PyTorch dentro il container 
pct exec 100 -- bash -c "
  apt-get update && apt-get install -y python3-pip &&
  pip3 install jupyter torch torchvision &&
  jupyter notebook --ip=0.0.0.0 --port=8888 --no-browser --allow-root
"
# Crea il service
pct exec 100 -- bash -c "cat > /etc/systemd/system/jupyter.service << EOF
[Unit]
Description=Jupyter Notebook
After=network.target

[Service]
User=root
ExecStart=/usr/local/bin/jupyter notebook --ip=0.0.0.0 --port=8888 --no-browser --allow-root
Restart=always

[Install]
WantedBy=multi-user.target
EOF

systemctl daemon-reload &&
systemctl enable jupyter &&
systemctl start jupyter"
```

In LXC non c'è NAT. Jupyter sarà raggiungibile su `http://<IP-del-container>:8888`. Trovi l'IP con `pct exec 100 -- ip a`.

**da aggiungere** 
docker run \ --cpus=2 \ --memory=4g \ --pids-limit=200 \NotebookApp.disable_terminal = True \ --gpus '"device=none"'


Nel server si avrà
|  server | container  | commenti | 
|---|---|---|---|---|
| /srv/students/alice  |  /home/alice/work |  qui lo studente può scrivere  |   |   |
|  /srv/datasets |/datasets   |  solo lettura |   |   |
| /srv/guidelines  | /guidelines  |  solo lettura |   |   |
| /srv/scripts  | /scripts  |  solo lettura |   |   |

In Jupyter lo studente vede 
work/          ← suoi notebook
datasets/      ← dataset condivisi

### App web per approvare
Creare app che permette di approvare un progetto. 
- nome studente/progetto
- uso di quale libreria? keras, pytorch, tensorflow? vincoliamo o qualsiasi?
- deve onviare mail ai docneti che devono approvare
- 

## Monitorare

Comando nvidia-smi

### Tensorboard

container separato?

### Tool per rimuover/aggougnere docker? fa già Proxmox?
## TO DO: permettiamo di caricare datasets?

## TODO : JupyterLAB o JupyterHub?