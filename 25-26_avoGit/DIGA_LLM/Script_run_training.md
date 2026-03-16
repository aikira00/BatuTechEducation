{
  "model_name": "cnn1",
  "epochs": 10,
  "batch_size": 32,
  "dataset": "cats_dogs"
}

# run_training.py
import json

with open("config.json") as f:
    config = json.load(f)

# carica modello
from models import get_model  # funzione definita da te
model = get_model(config["model_name"])


import json
import torch
import subprocess
import logging

logging.basicConfig(filename="/home/jovyan/work/training.log", level=logging.INFO)

# leggi configurazione
with open("config.json") as f:
    config = json.load(f)

# controllo batch size
if config["batch_size"] > 64:
    raise Exception("Batch troppo grande")

# GPU queue
subprocess.call("bash /home/jovyan/scripts/wait_gpu.sh", shell=True)

# limita memoria GPU
torch.cuda.set_per_process_memory_fraction(0.5)

# training vero
print("Training in corso...")
# model.fit(...)  <- qui si fa il training
logging.info(f"Lancio training: {config}")