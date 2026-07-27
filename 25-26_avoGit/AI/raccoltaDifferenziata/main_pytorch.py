"""
============================================================================
 Avogadro Progetto DIGA - Raccolta differenziata  (versione PyTorch)
============================================================================

 Obiettivo: sviluppare un sistema di computer vision in grado di aiutare
 le persone a differenziare meglio i loro rifiuti.

 Si costruisce un classificatore di immagini per distinguere i rifiuti
 secondo le categorie utilizzate a Torino:
   - Carta e cartone
   - Imballaggi in plastica
   - Vetro e imballaggi in metallo
   - Rifiuti organici
   - Rifiuti non recuperabili

 Vengono confrontati due approcci:
   1) CNN addestrata da zero
   2) Transfer learning (fine-tuning di MobileNetV2 pre-addestrato)

 Fasi del progetto:
   - Raccolta dati (fotografie)
   - Annotazione delle immagini
   - Affinamento del dataset
   - Costruzione di una CNN da zero
   - Fine-tuning (transfer learning)
   - Valutazione e confronto

 NOTA: PyTorch su Mac sfrutta la GPU via MPS (Metal Performance Shaders),
 su Linux/Windows con NVIDIA via CUDA, altrimenti CPU. La selezione del
 device e' fatta automaticamente nella prossima sezione.
============================================================================
"""

# ============================================================================
# 1. IMPORT DELLE LIBRERIE
# ============================================================================
# Cosa serve ogni libreria nella pipeline ML:
#   - zipfile: per decomprimere l'archivio del dataset
#   - os:      per navigare il file system e leggere le immagini
#   - PIL:     per aprire le immagini (a monte di torchvision)
#   - numpy:   per array e metriche
#   - matplotlib: per grafici e visualizzazioni
#   - torch / torchvision: framework di deep learning
#   - sklearn: per lo split train/test e per le metriche di valutazione

import zipfile                      # decompressione dataset
import os                           # gestione file
import platform                     # info piattaforma (per la diagnostica device)
from PIL import Image               # apertura immagini

import numpy as np                  # calcoli numerici
import matplotlib.pyplot as plt     # grafici

import torch                        # tensori, autograd, device
import torch.nn as nn               # layer e moduli per le reti
import torch.optim as optim         # ottimizzatori (Adam, SGD, ...)
from torch.utils.data import Dataset, DataLoader

from torchvision import transforms, models  # trasformazioni e modelli pre-addestrati

from sklearn.model_selection import train_test_split
from sklearn.metrics import (
    confusion_matrix,
    ConfusionMatrixDisplay,
    classification_report,
)


# ============================================================================
# 1.bis  GESTIONE DEL DEVICE (GPU / MPS / CPU)
# ============================================================================
# A differenza di Keras, in PyTorch il device va scelto ESPLICITAMENTE e
# modello + tensori vanno trasferiti su quel device con .to(device).
# Tre opzioni in ordine di preferenza:
#   1) "mps"  -> GPU Apple Silicon (Metal Performance Shaders) - Mac M1/M2/M3/M4
#   2) "cuda" -> GPU NVIDIA (server DIGA, Colab, ...)
#   3) "cpu"  -> fallback, sempre disponibile (ma piu' lento)

if torch.backends.mps.is_available():
    device = torch.device("mps")
elif torch.cuda.is_available():
    device = torch.device("cuda")
else:
    device = torch.device("cpu")

print(f"PyTorch version: {torch.__version__}")
print(f"Piattaforma:     {platform.system()} {platform.machine()}")
print(f"Device in uso:   {device}")

# Test rapido: forziamo una piccola operazione sul device per verificarne il
# funzionamento. Se questa va in errore, il problema e' di installazione e
# il training a maggior ragione fallirebbe.
_a = torch.randn(1000, 1000, device=device)
_b = torch.matmul(_a, _a)
print(f"Test matmul su {device}: OK (output shape {tuple(_b.shape)})\n")


# --- Riproducibilita' --------------------------------------------------------
# Le reti neurali usano numerosi processi stocastici (inizializzazione pesi,
# shuffle, dropout, ecc.). Fissiamo il "seme" casuale per riproducibilita'.
random_state = 42
np.random.seed(random_state)
torch.manual_seed(random_state)
if device.type == "cuda":
    torch.cuda.manual_seed_all(random_state)


# ============================================================================
# 2. CREAZIONE E PROCESSING DEL DATASET
# ============================================================================

# --- Estrazione dell'archivio zip -------------------------------------------
zip_filepath = "/Users/cristina/Documents/PCloud_sync/AI_ML-DL/25-26_Diga_AI_4a/progettoFinale/DATASET_AVOGADRO_DIFFERENZIATA_FINALE.zip"
output_filepath = "./content/dataset"

if not os.path.exists(output_filepath):
    os.makedirs(output_filepath)

# Apriamo lo zip in modalita' lettura ('r') ed estraiamo tutto il contenuto
# nella cartella specificata da output_filepath.
with zipfile.ZipFile(zip_filepath, 'r') as zip_ref:
    zip_ref.extractall(output_filepath)


# --- Lettura dei percorsi delle immagini e delle etichette ------------------
# Il dataset e' organizzato in cartelle: il nome di ogni cartella corrisponde
# alla classe (es. "CARTA", "PLASTICA"). Costruiamo due liste parallele:
#   - file_paths: l'elenco dei percorsi assoluti delle immagini
#   - labels:     l'elenco delle etichette corrispondenti (stesso ordine)

dataset_filepath = "./content/dataset"
file_paths = []
labels = []

for label in os.listdir(dataset_filepath):
    label_dir = os.path.join(dataset_filepath, label)

    # Verifichiamo che si tratti effettivamente di una cartella (e non,
    # ad esempio, di un file nascosto come .DS_Store)
    if os.path.isdir(label_dir):
        for file_name in os.listdir(label_dir):
            file_paths.append(os.path.join(label_dir, file_name))
            labels.append(label)


# --- Da etichette testuali a indici interi ----------------------------------
# DIFFERENZA RISPETTO A KERAS: in PyTorch NON usiamo one-hot. La loss
# standard di classificazione (nn.CrossEntropyLoss) lavora direttamente con
# l'INDICE INTERO della classe corretta. E' piu' efficiente e numericamente
# piu' stabile (CrossEntropyLoss combina internamente log_softmax e NLLLoss).
class_names = sorted(list(set(labels)))      # ordine alfabetico, riproducibile
num_classes = len(class_names)
label_to_index = {name: i for i, name in enumerate(class_names)}

# Convertiamo tutte le etichette in indici interi
y_all = [label_to_index[lbl] for lbl in labels]

print(f"Classi: {class_names}")
print(f"Numero di classi: {num_classes}")
print(f"Numero totale di immagini: {len(file_paths)}")


# --- Suddivisione in training set e test set --------------------------------
# Riserviamo il 20% delle immagini per il test set.
#
# ATTENZIONE allo shuffle: noi abbiamo costruito file_paths/labels leggendo
# le cartelle UNA PER VOLTA. Di conseguenza la lista e' ordinata per classe:
# senza mescolare, lo split metterebbe intere classi tutte in train o tutte
# in test!
#   shuffle=True     -> mescola i dati prima di tagliarli
#   stratify=y_all   -> mantiene le proporzioni delle classi nei due insiemi
#   random_state     -> rende lo split riproducibile

X_train, X_test, y_train, y_test = train_test_split(
    file_paths, y_all,
    test_size=0.2,
    random_state=random_state,
    shuffle=True,
    stratify=y_all,
)

print(f"Training set: {len(X_train)} immagini")
print(f"Test set:     {len(X_test)} immagini")


# --- Visualizzazione di alcuni campioni del dataset -------------------------
# Costruiamo una griglia 4x5 (4 immagini per ognuna delle prime 5 classi)
# per controllare visivamente che il dataset sia stato caricato correttamente.

fig, axes = plt.subplots(4, 5, figsize=(15, 9))
fig.suptitle("Campioni dal dataset", fontsize=12, fontweight='bold')

# Prendiamo i primi 5 indici di classe unici, preservando l'ordine di apparizione
shown_class_ids = list(dict.fromkeys(y_train))[:5]

for col, cls_id in enumerate(shown_class_ids):
    # Indici delle immagini di training appartenenti a questa classe
    indices = [i for i, t in enumerate(y_train) if t == cls_id]

    for row in range(4):
        ax = axes[row, col]
        ax.axis('off')

        if row < len(indices):
            image = Image.open(X_train[indices[row]]).convert('RGB')
            ax.imshow(image)
            if row == 0:
                # Mostriamo il NOME testuale della classe, non l'indice
                ax.set_title(class_names[cls_id], fontsize=10)

plt.tight_layout()
plt.show()


# --- Dataset PyTorch --------------------------------------------------------
# DIFFERENZA RISPETTO A KERAS: in PyTorch i dati si gestiscono con due
# astrazioni complementari:
#   - Dataset:    sa rispondere a "dammi il campione i-esimo"
#   - DataLoader: si occupa di batching, shuffle e parallelismo nel caricamento
#
# Questo significa che le immagini NON vengono caricate tutte in RAM
# all'avvio (come faceva 'carica_immagini' nella versione Keras). Vengono
# caricate "on demand", in modo asincrono, durante il training. Funziona
# anche con dataset grandi che non starebbero in RAM.

class WasteDataset(Dataset):
    """Dataset di immagini di rifiuti.

    Argomenti:
        paths:     lista di percorsi delle immagini
        targets:   lista di indici interi (uno per immagine)
        transform: pipeline torchvision da applicare a ciascuna immagine
    """

    def __init__(self, paths, targets, transform):
        self.paths = paths
        self.targets = targets
        self.transform = transform

    def __len__(self):
        # Quante immagini contiene il dataset?
        return len(self.paths)

    def __getitem__(self, idx):
        # Carichiamo l'immagine all'indice idx, applichiamo la transform
        # e la restituiamo insieme alla sua etichetta intera.
        img = Image.open(self.paths[idx]).convert('RGB')
        img = self.transform(img)
        target = self.targets[idx]
        return img, target


# --- Trasformazioni per la CNN da zero --------------------------------------
# ToTensor() converte PIL Image -> tensore PyTorch e divide gia' i pixel
# per 255, quindi non serve un layer Rescaling esplicito come in Keras.
transform_basic = transforms.Compose([
    transforms.Resize((224, 224)),
    transforms.ToTensor(),                 # [0,255] uint8 -> [0,1] float32
])

# --- Trasformazioni per MobileNetV2 (transfer learning) ---------------------
# I modelli pre-addestrati di torchvision si aspettano che l'input sia
# normalizzato con le statistiche di ImageNet (la media e la deviazione
# standard calcolate sul dataset originale ImageNet). Se non normalizziamo
# cosi', l'input al modello e' "fuori distribuzione" e le prestazioni crollano.
imagenet_mean = [0.485, 0.456, 0.406]
imagenet_std = [0.229, 0.224, 0.225]
transform_imagenet = transforms.Compose([
    transforms.Resize((224, 224)),
    transforms.ToTensor(),
    transforms.Normalize(mean=imagenet_mean, std=imagenet_std),
])


# --- DataLoader per le due pipeline -----------------------------------------
# batch_size=32: 32 immagini elaborate insieme prima dell'aggiornamento pesi
# shuffle=True:  rimescola il TRAINING set a ogni epoca. Senza, dato che i
#                dati arrivano ancora ordinati dallo split iniziale, ogni
#                batch da 32 immagini conterrebbe prevalentemente una sola
#                classe -> gradienti rumorosi, training instabile.
#                Il TEST set NON va mescolato (non avrebbe senso, serve solo
#                per valutare).
# num_workers=2: processi paralleli per il caricamento dal disco.

batch_size = 32

# Pipeline "basic" per la CNN da zero
train_ds_basic = WasteDataset(X_train, y_train, transform_basic)
test_ds_basic  = WasteDataset(X_test,  y_test,  transform_basic)
train_loader_basic = DataLoader(train_ds_basic, batch_size=batch_size,
                                shuffle=True, num_workers=2)
test_loader_basic  = DataLoader(test_ds_basic,  batch_size=batch_size,
                                shuffle=False, num_workers=2)

# Pipeline ImageNet-normalized per il transfer learning
train_ds_tl = WasteDataset(X_train, y_train, transform_imagenet)
test_ds_tl  = WasteDataset(X_test,  y_test,  transform_imagenet)
train_loader_tl = DataLoader(train_ds_tl, batch_size=batch_size,
                             shuffle=True, num_workers=2)
test_loader_tl  = DataLoader(test_ds_tl,  batch_size=batch_size,
                             shuffle=False, num_workers=2)


# ============================================================================
# 2.bis  DATA AUGMENTATION  (TODO - da completare a cura dello studente)
# ============================================================================
# La data augmentation permette di aumentare la varieta' dei dati di
# addestramento applicando trasformazioni casuali (flip, rotazioni, zoom,
# variazioni di luminosita', ecc.) alle immagini esistenti.
# Questo aiuta la rete a generalizzare meglio e a ridurre l'overfitting.
#
# In PyTorch si compone una pipeline di trasformazioni, ad esempio:
#
#   transform_aug = transforms.Compose([
#       transforms.Resize((224, 224)),
#       transforms.RandomHorizontalFlip(),
#       transforms.RandomRotation(10),
#       transforms.ColorJitter(brightness=0.2, contrast=0.2),
#       transforms.ToTensor(),
#   ])
#
# ATTENZIONE: la data augmentation si applica SOLO al training set, mai al
# test set! Costruite quindi due Dataset distinti: uno con augmentation per
# il training, uno senza per il test.
# ============================================================================


# ============================================================================
# 2.ter  UTILIZZO DI DATASET ESTERNI  (TODO - da completare)
# ============================================================================
# Alternativa alla data augmentation: utilizzare dataset gia' disponibili
# online (es. Kaggle). Attenzione: la tassonomia di un dataset esterno quasi
# sicuramente non coincidera' con le 5 categorie della differenziata di Torino.
# Sara' quindi necessario "rimappare" le classi unendole opportunamente.
# ============================================================================


# ============================================================================
# 3. CREAZIONE DI UNA CNN DA ZERO
# ============================================================================
# DIFFERENZA RISPETTO A KERAS: in PyTorch una rete e' una CLASSE che estende
# nn.Module. Due metodi vanno definiti:
#   - __init__:   registriamo i layer (li salviamo come attributi)
#   - forward(x): definiamo COME i dati attraversano la rete
# La backward pass (propagazione dell'errore) viene calcolata in automatico
# da PyTorch grazie all'autograd.
#
# Struttura: due blocchi conv + classificatore fully-connected.
# Stessa architettura della versione Keras, solo tradotta nelle API PyTorch.

class CNNFromScratch(nn.Module):

    def __init__(self, num_classes):
        super().__init__()

        # --- BLOCCO CONVOLUZIONALE 1 ---
        # Conv2d(3, 32, kernel_size=3, padding=1):
        #   - 3 canali in input (immagine RGB)
        #   - 32 filtri 3x3 -> estraggono pattern semplici (bordi, transizioni)
        #   - padding=1 -> output con la stessa altezza/larghezza dell'input
        # MaxPool2d(2): dimezza altezza e larghezza
        self.conv1 = nn.Conv2d(3, 32, kernel_size=3, padding=1)
        self.pool1 = nn.MaxPool2d(2)

        # --- BLOCCO CONVOLUZIONALE 2 ---
        # 64 filtri: pattern via via piu' complessi salendo di profondita'.
        self.conv2 = nn.Conv2d(32, 64, kernel_size=3, padding=1)
        self.pool2 = nn.MaxPool2d(2)

        # --- CLASSIFICATORE FULLY-CONNECTED ---
        # Calcolo della dimensione dopo i due max-pool:
        #   224 -> 112 (dopo pool1) -> 56 (dopo pool2)
        # Quindi il tensore appiattito ha 64 * 56 * 56 feature.
        self.flatten = nn.Flatten()
        self.fc1     = nn.Linear(64 * 56 * 56, 128)
        self.dropout = nn.Dropout(0.5)
        self.fc2     = nn.Linear(128, num_classes)

        # NOTA: la ReLU la applichiamo nel forward.
        # NOTA: NON aggiungiamo softmax finale. nn.CrossEntropyLoss applica
        #       gia' internamente log_softmax durante il calcolo della loss.

    def forward(self, x):
        # x ha shape (batch, 3, 224, 224)
        x = torch.relu(self.conv1(x))      # -> (batch, 32, 224, 224)
        x = self.pool1(x)                  # -> (batch, 32, 112, 112)
        x = torch.relu(self.conv2(x))      # -> (batch, 64, 112, 112)
        x = self.pool2(x)                  # -> (batch, 64, 56, 56)
        x = self.flatten(x)                # -> (batch, 64*56*56)
        x = torch.relu(self.fc1(x))        # -> (batch, 128)
        x = self.dropout(x)                # spegne il 50% in training
        x = self.fc2(x)                    # -> (batch, num_classes) logits
        return x


# ============================================================================
# 3.bis  VARIARE L'ARCHITETTURA  (TODO)
# ============================================================================
# Sperimentate variazioni dell'architettura sopra (numero di blocchi, numero
# di filtri, dimensione del kernel, aggiunta di nn.BatchNorm2d, ecc.) per
# vedere come cambiano le prestazioni della rete.
# ============================================================================


# ============================================================================
# 4. TRAINING LOOP E ADDESTRAMENTO DELLA CNN
# ============================================================================
# DIFFERENZA RISPETTO A KERAS: in PyTorch il training loop si scrive a mano.
# E' piu' verboso, ma molto piu' trasparente dal punto di vista didattico:
# si vedono ESPLICITAMENTE forward, calcolo della loss, backward e step.
# Niente di magico nascosto in .fit().

def train_one_epoch(model, loader, loss_fn, optimizer):
    """Esegue UNA epoca di addestramento. Ritorna (loss media, accuracy)."""
    model.train()  # attiva dropout e batch-norm in modalita' training

    total_loss = 0.0
    correct = 0
    total = 0

    for images, targets in loader:
        # 1) Trasferiamo il batch sul device (GPU se disponibile)
        images = images.to(device)
        targets = targets.to(device)

        # 2) Azzeriamo i gradienti accumulati dall'iterazione precedente
        #    (in PyTorch i gradienti si SOMMANO di default, quindi vanno
        #    azzerati manualmente prima di ogni backward)
        optimizer.zero_grad()

        # 3) FORWARD PASS: calcoliamo le predizioni della rete (logits)
        outputs = model(images)            # shape: (batch, num_classes)

        # 4) Calcoliamo la loss confrontando predizioni e etichette vere
        loss = loss_fn(outputs, targets)

        # 5) BACKWARD PASS: PyTorch calcola i gradienti della loss rispetto
        #    a tutti i pesi della rete (autograd)
        loss.backward()

        # 6) L'optimizer aggiorna i pesi usando i gradienti calcolati
        optimizer.step()

        # --- Statistiche per la stampa ---
        total_loss += loss.item() * images.size(0)
        predicted = outputs.argmax(dim=1)  # classe predetta = indice del max
        correct += (predicted == targets).sum().item()
        total += targets.size(0)

    return total_loss / total, correct / total


def evaluate(model, loader, loss_fn):
    """Valuta il modello su un loader. Ritorna (loss media, accuracy)."""
    model.eval()  # disattiva dropout; batch-norm va in modalita' valutazione

    total_loss = 0.0
    correct = 0
    total = 0

    # torch.no_grad() disattiva il tracking dei gradienti -> piu' veloce,
    # meno memoria. In valutazione non ci servono i gradienti.
    with torch.no_grad():
        for images, targets in loader:
            images = images.to(device)
            targets = targets.to(device)
            outputs = model(images)
            loss = loss_fn(outputs, targets)

            total_loss += loss.item() * images.size(0)
            predicted = outputs.argmax(dim=1)
            correct += (predicted == targets).sum().item()
            total += targets.size(0)

    return total_loss / total, correct / total


def fit(model, train_loader, test_loader, epochs, lr=1e-3):
    """Addestra il modello per `epochs` epoche. Ritorna lo storico per i plot.

    Equivalente a model.compile() + model.fit() di Keras, ma scritto
    esplicitamente cosi' si vede dove avviene cosa.
    """
    # CrossEntropyLoss: loss standard per classificazione multi-classe.
    # Si aspetta logits (NON probabilita') e indici interi (NON one-hot).
    loss_fn = nn.CrossEntropyLoss()

    # Adam: optimizer con learning rate adattivo, lo stesso usato in Keras.
    optimizer = optim.Adam(model.parameters(), lr=lr)

    # Dizionario in stile "history" di Keras, cosi' riusiamo la stessa
    # logica per i plot delle curve.
    history = {'loss': [], 'accuracy': [], 'val_loss': [], 'val_accuracy': []}

    for epoch in range(1, epochs + 1):
        tr_loss, tr_acc = train_one_epoch(model, train_loader, loss_fn, optimizer)
        va_loss, va_acc = evaluate(model, test_loader, loss_fn)

        history['loss'].append(tr_loss)
        history['accuracy'].append(tr_acc)
        history['val_loss'].append(va_loss)
        history['val_accuracy'].append(va_acc)

        print(f"Epoca {epoch:3d}/{epochs}  "
              f"train_loss={tr_loss:.4f}  train_acc={tr_acc:.4f}  "
              f"val_loss={va_loss:.4f}  val_acc={va_acc:.4f}")

    return history


# --- Istanza del modello e addestramento ------------------------------------
print("\n=== CNN DA ZERO ===")
cnn_model = CNNFromScratch(num_classes=num_classes).to(device)
print(cnn_model)
print("\nInizio addestramento CNN da zero...")
history_cnn = fit(cnn_model, train_loader_basic, test_loader_basic, epochs=30)


# ============================================================================
# 5. FINE-TUNING (TRANSFER LEARNING) CON MOBILENETV2
# ============================================================================
# Anziche' partire da zero, riutilizziamo una rete pre-addestrata su ImageNet
# (milioni di immagini). Useremo MobileNetV2: una rete leggera ed efficiente
# pensata per dispositivi mobili.
#
# Strategia:
#   1) Carichiamo MobileNetV2 con i pesi ImageNet
#   2) "Congeliamo" i pesi originali (requires_grad=False): non li alleniamo
#   3) Sostituiamo l'ULTIMO STRATO (classificatore) con uno nuovo per le 5
#      classi della differenziata di Torino
#   4) Solo i pesi del nuovo classificatore verranno aggiornati

print("\n=== TRANSFER LEARNING (MobileNetV2) ===")

# --- Caricamento del modello pre-addestrato ---
# In torchvision i pesi pre-addestrati si caricano tramite la classe Weights
# del modello. La prima volta che si esegue, il file dei pesi (qualche
# decina di MB) viene scaricato automaticamente nella cache.
tl_model = models.mobilenet_v2(
    weights=models.MobileNet_V2_Weights.IMAGENET1K_V1
)

# --- Congeliamo tutti i pesi del modello base ---
# requires_grad=False -> autograd NON calcolera' gradienti per questi
# parametri, e l'optimizer NON li aggiornera'.
for param in tl_model.parameters():
    param.requires_grad = False

# --- Sostituiamo il classificatore finale ---
# In MobileNetV2 di torchvision, l'attributo .classifier e' un nn.Sequential
# fatto da: [Dropout(0.2), Linear(1280, 1000)]. Il 1000 e' perche' ImageNet
# ha 1000 classi. Noi ne abbiamo 5: ricostruiamo il classificatore.
#
# I parametri di nn.Linear creati ora hanno requires_grad=True per default,
# quindi SOLO questi verranno aggiornati durante l'addestramento.
in_features = tl_model.classifier[1].in_features    # tipicamente 1280
tl_model.classifier = nn.Sequential(
    nn.Dropout(0.2),
    nn.Linear(in_features, num_classes),
)

tl_model = tl_model.to(device)
print("\nInizio addestramento Transfer Learning...")
history_tl = fit(tl_model, train_loader_tl, test_loader_tl, epochs=30)


# ============================================================================
# 6. VALUTAZIONE DEL SISTEMA
# ============================================================================
# Per valutare le reti useremo tre strumenti complementari:
#   1) Curve di training: accuracy e loss epoca per epoca
#   2) Report di classificazione: Accuracy, Precision, Recall, F1
#   3) Matrice di confusione: ci dice QUALI classi vengono confuse tra loro

def plot_history(history, title):
    """Disegna due grafici affiancati con accuracy e loss su train e val.

    Permette di "vedere" eventuale overfitting (training che cresce mentre
    validation peggiora) o underfitting.
    """
    plt.figure(figsize=(12, 4))

    # Grafico 1: accuracy
    plt.subplot(1, 2, 1)
    plt.plot(history['accuracy'], label='Train')
    plt.plot(history['val_accuracy'], label='Val')
    plt.title(f'Accuracy - {title}')
    plt.legend()

    # Grafico 2: loss
    plt.subplot(1, 2, 2)
    plt.plot(history['loss'], label='Train')
    plt.plot(history['val_loss'], label='Val')
    plt.title(f'Loss - {title}')
    plt.legend()

    plt.show()


def get_predictions(model, loader):
    """Restituisce (y_true, y_pred) come array numpy per un loader dato.

    Funzione di utilita' usata sia da evaluate_model sia da
    show_confusion_matrices.
    """
    model.eval()
    all_true = []
    all_pred = []
    with torch.no_grad():
        for images, targets in loader:
            images = images.to(device)
            outputs = model(images)
            preds = outputs.argmax(dim=1).cpu().numpy()
            all_true.extend(targets.numpy())
            all_pred.extend(preds)
    return np.array(all_true), np.array(all_pred)


def evaluate_model(model, loader, title):
    """Stampa il report di classificazione (Accuracy, Precision, Recall, F1)."""
    print(f"\n--- Valutazione {title} ---")
    y_true, y_pred = get_predictions(model, loader)
    print(classification_report(y_true, y_pred, target_names=class_names))


# --- Visualizziamo le curve di training delle due reti ----------------------
# E' fondamentale guardare PRIMA le curve di addestramento: ci aiutano a
# capire se la rete e' andata in overfitting (training molto piu' alta di
# validation), in underfitting o se l'addestramento si e' fermato troppo
# presto / tardi.
plot_history(history_cnn, "CNN da zero")
plot_history(history_tl, "Transfer learning")

# TODO (analisi): come commentate questi quattro grafici? Il fatto che
#                 l'accuracy di training della CNN da zero superi (e di molto)
#                 quella di validation cosa indica? Stesse riflessioni per la
#                 rete in transfer learning.


# --- Report di classificazione ----------------------------------------------
# ATTENZIONE: ogni rete ha la SUA pipeline di preprocessing (la CNN da zero
# usa transform_basic, MobileNetV2 usa transform_imagenet). Quindi usiamo il
# loader CORRETTO per ciascun modello.
evaluate_model(cnn_model, test_loader_basic, "CNN da zero")
evaluate_model(tl_model,  test_loader_tl,    "Transfer learning")


# --- Matrice di confusione --------------------------------------------------
# La matrice di confusione mostra, per ogni coppia (classe vera, classe predetta),
# la frequenza delle predizioni. Sulla DIAGONALE PRINCIPALE troviamo le
# predizioni corrette; fuori diagonale gli errori.
#
# Usiamo la versione NORMALIZZATA (normalize='true'): ogni riga somma a 1
# e i valori rappresentano percentuali rispetto al supporto della classe vera.
# Questo evita che classi piu' numerose "schiaccino" visivamente quelle piu'
# rare.

def show_confusion_matrices(models_dict, loaders_dict):
    """Visualizza fianco a fianco le matrici di confusione (normalizzate).

    NOVITA' rispetto alla versione Keras: oltre al dizionario dei modelli,
    riceve anche un dizionario di loader (chiavi corrispondenti), perche'
    in PyTorch ogni modello ha la sua pipeline di preprocessing.

    models_dict:  { "titolo": modello_pytorch, ... }
    loaders_dict: { "titolo": dataloader_corrispondente, ... }
    """
    n = len(models_dict)
    fig, axes = plt.subplots(1, n, figsize=(8 * n, 7))

    # Se c'e' un solo modello, plt.subplots restituisce un singolo Axes
    # invece di una lista: lo trasformiamo in lista per uniformita'.
    if n == 1:
        axes = [axes]

    for ax, (title, model) in zip(axes, models_dict.items()):
        loader = loaders_dict[title]
        y_true, y_pred = get_predictions(model, loader)

        # Matrice normalizzata per riga (ogni riga somma a 1)
        cm = confusion_matrix(y_true, y_pred, normalize='true')

        disp = ConfusionMatrixDisplay(
            confusion_matrix=cm,
            display_labels=class_names,
        )
        disp.plot(
            ax=ax,
            cmap=plt.cm.Blues,
            xticks_rotation=45,
            values_format='.2f',
        )
        ax.set_title(f"Matrice di confusione (Normalizzata)\n{title}")

    plt.tight_layout()
    plt.show()


# Dizionari paralleli: stesso titolo -> stesso modello -> stesso loader
models_to_compare = {
    "CNN da zero":        cnn_model,
    "Transfer learning":  tl_model,
}
loaders_to_compare = {
    "CNN da zero":        test_loader_basic,
    "Transfer learning":  test_loader_tl,
}

show_confusion_matrices(models_to_compare, loaders_to_compare)

# TODO: in quali casi la versione NON normalizzata della matrice di confusione
#       puo' essere fuorviante? Provate a generarla (basta togliere
#       normalize='true' nella chiamata a confusion_matrix) e confrontatela
#       con quella normalizzata.


# ============================================================================
# 6.bis  ESTENDERE LA VALUTAZIONE  (TODO)
# ============================================================================
# Una volta implementate le vostre varianti (data augmentation, dataset
# esterni, architetture alternative, ecc.) estendete i dizionari sopra per
# includerle, e rieseguite la valutazione. In questo modo potrete confrontare
# quantitativamente l'efficacia delle diverse scelte progettuali.
# ============================================================================