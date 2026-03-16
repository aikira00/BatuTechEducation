Notebook studente: notebook_cnn.ipynb

# 1️⃣ Import necessari
import torch
import torch.nn as nn
import torch.optim as optim
from torchvision import datasets, transforms
import json

# 2️⃣ Preparazione dataset
transform = transforms.Compose([
    transforms.Resize((28,28)),
    transforms.ToTensor()
])

train_dataset = datasets.FakeData(transform=transform)
# Nota: in un corso reale sostituire FakeData con il dataset vero
train_loader = torch.utils.data.DataLoader(train_dataset, batch_size=32)

# 3️⃣ Definizione modello CNN
class MyCNN(nn.Module):
    def __init__(self):
        super(MyCNN, self).__init__()
        self.conv1 = nn.Conv2d(3, 16, 3, 1)
        self.pool = nn.MaxPool2d(2, 2)
        self.fc1 = nn.Linear(16*13*13, 10)

    def forward(self, x):
        x = self.pool(torch.relu(self.conv1(x)))
        x = x.view(-1, 16*13*13)
        x = self.fc1(x)
        return x

# 4️⃣ Salvo modello in file temporaneo
model = MyCNN()
torch.save(model.state_dict(), "student_model.pth")

# 5️⃣ Preparo config.json per run_training.py
config = {
    "model_file": "student_model.pth",   # modello dello studente
    "dataset": "train_dataset",
    "epochs": 5,
    "batch_size": 32
}

with open("config.json", "w") as f:
    json.dump(config, f)

# 6️⃣ Lancia training tramite run_training.py
!python /home/jovyan/scripts/run_training.py config.json


⸻

3️⃣ Come funziona il flusso
	1.	Lo studente crea modello CNN nel notebook
	2.	Salva il modello (student_model.pth)
	3.	Scrive un config.json con parametri: dataset, epoche, batch size
	4.	Chiama run_training.py → controlli GPU, queue, batch max, timeout
	5.	Training effettivo avviene solo tramite script controllato
	6.	Log automatici salvati → insegnante sa chi ha lanciato cosa

⸻

4️⃣ Vantaggi di questo approccio
	•	Studenti possono definire il loro modello senza limitazioni
	•	Training GPU controllato → nessun crash server
	•	Batch size, epoche e GPU memory limitati dentro lo script
	•	Log automatici → puoi monitorare tutto