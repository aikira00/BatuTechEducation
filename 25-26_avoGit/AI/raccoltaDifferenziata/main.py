"""
============================================================================
 Avogadro Progetto DIGA - Raccolta differenziata
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
   2) Transfer learning (fine-tuning di una rete pre-addestrata)

 Fasi del progetto:
   - Raccolta dati (fotografie)
   - Annotazione delle immagini
   - Affinamento del dataset
   - Costruzione di una CNN da zero
   - Fine-tuning (transfer learning)
   - Valutazione e confronto

 NOTA: per ridurre i tempi di addestramento si consiglia di eseguire il
 codice su Google Colab con runtime "T4 GPU" (Runtime -> Cambia tipo di
 runtime -> T4 GPU). A ogni cambio di runtime occorre rieseguire tutte
 le celle e ricaricare il dataset.
============================================================================
"""

# ============================================================================
# 1. IMPORT DELLE LIBRERIE
# ============================================================================
# Ogni libreria serve a un compito specifico nella pipeline ML:
#   - zipfile: per decomprimere l'archivio del dataset
#   - os:      per navigare il file system e leggere le immagini
#   - PIL:     per aprire, convertire e ridimensionare le immagini
#   - numpy:   per la gestione efficiente degli array (tensori di pixel)
#   - matplotlib: per visualizzare immagini, curve di addestramento e grafici
#   - keras / tensorflow: framework per costruire e addestrare reti neurali
#   - sklearn: per lo split train/test e per le metriche di valutazione

import zipfile                      # decompressione dataset
import os                           # gestione file
from PIL import Image               # elaborazione immagini

import numpy as np                  # calcoli numerici
import matplotlib.pyplot as plt     # grafici
import keras                        # reti neurali (API ad alto livello)
from keras import layers, models, optimizers, callbacks
from sklearn.model_selection import train_test_split
from sklearn.metrics import (
    confusion_matrix,
    ConfusionMatrixDisplay,
    classification_report,
)

### GESTIONE GPU _ MAC COMPATIBILE ALTRIMENTI CPU
import tensorflow as tf
import platform
print(f"TensorFlow version: {tf.__version__}")
print(f"Piattaforma:        {platform.system()} {platform.machine()}")

# Su Mac la GPU passa per Metal (PluggableDevice), su Linux/Windows per CUDA.
# Quindi NON controlliamo is_built_with_cuda (sarebbe False su Mac per definizione):
# ci limitiamo a chiedere a TF quali GPU vede.
gpus = tf.config.list_physical_devices('GPU')
print(f"\nGPU disponibili: {len(gpus)}")
for gpu in gpus:
    print(f"  - {gpu}")

if not gpus:
    print("\n⚠  Nessuna GPU rilevata da TensorFlow.")
    # Non usciamo: il codice gira anche su CPU, solo piu' lento.
else:
    # Test rapido: forziamo una piccola operazione sulla GPU
    with tf.device('/GPU:0'):
        a = tf.random.normal((1000, 1000))
        b = tf.matmul(a, a)
    print(f"\nTest matmul su GPU: OK (output shape {b.shape})")
# --- Riproducibilità ---------------------------------------------------------
# Le reti neurali usano numerosi processi stocastici (inizializzazione pesi,
# shuffle dei dati, dropout, ecc.). Per ottenere risultati riproducibili
# fissiamo il "seme" casuale per numpy, tensorflow e keras.
random_state = 42
np.random.seed(random_state)
tf.random.set_seed(random_state)
keras.utils.set_random_seed(random_state)


# ============================================================================
# 2. CREAZIONE E PROCESSING DEL DATASET
# ============================================================================
# Prima di eseguire questo blocco occorre caricare manualmente nella sezione
# "File" di Colab l'archivio zip con il dataset. ATTENZIONE: il file viene
# rimosso ogni volta che ci si scollega da Colab.

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
# alla classe (es. "CARTA", "PLASTICA", ecc.), e contiene tutte le immagini
# di quella categoria. Per ogni file costruiamo:
#   - file_paths: l'elenco dei percorsi assoluti delle immagini
#   - labels:     l'elenco delle etichette corrispondenti (stesso ordine)

dataset_filepath = "./content/dataset"
file_paths = []
labels = []

# Cicliamo su tutte le cartelle (classi) del dataset
for label in os.listdir(dataset_filepath):

    label_dir = os.path.join(dataset_filepath, label)

    # Verifichiamo che si tratti effettivamente di una cartella (e non,
    # ad esempio, di un file nascosto come .DS_Store)
    if os.path.isdir(label_dir):
        # Per ogni file all'interno della cartella di classe, salviamo
        # il percorso e l'etichetta (nome della cartella)
        for file_name in os.listdir(label_dir):
            file_paths.append(os.path.join(label_dir, file_name))
            labels.append(label)


# --- Suddivisione in training set e test set --------------------------------
# Riserviamo il 20% delle immagini per il test set.
#
# ATTENZIONE allo shuffle: noi abbiamo costruito file_paths/labels leggendo
# le cartelle UNA PER VOLTA. Di conseguenza la lista e' ordinata per classe:
# prima tutte le CARTA, poi tutte le PLASTICA, ecc. Senza mescolare, lo split
# 80/20 prenderebbe blocchi contigui e finirebbe per mettere intere classi
# tutte nel training o tutte nel test set!
#
# Parametri usati:
#   shuffle=True     -> mescola i dati prima di tagliarli (default, esplicitato)
#   stratify=labels  -> garantisce che la proporzione delle classi sia
#                       rispettata sia nel training che nel test set (utile
#                       quando le classi sono sbilanciate, come nel nostro caso)
#   random_state     -> rende lo shuffle riproducibile da un'esecuzione all'altra
X_train, X_test, y_train, y_test = train_test_split(
    file_paths, labels,
    test_size=0.2,
    random_state=random_state,
    shuffle=True,        # rimescola la lista prima dello split
    stratify=labels,     # mantiene le proporzioni delle classi
)

# Stampa di controllo: vediamo i primi 10 elementi di ciascun insieme
X_train[:10], X_test[:10], y_train[:10], y_test[:10]


# --- Visualizzazione di alcuni campioni del dataset -------------------------
# Costruiamo una griglia 4x5 (4 immagini per ognuna delle prime 5 classi)
# per controllare visivamente che il dataset sia stato caricato correttamente.

fig, axes = plt.subplots(4, 5, figsize=(15, 9))
fig.suptitle("Campioni dal dataset", fontsize=12, fontweight='bold')

# Prendiamo i primi 5 nomi di classe unici, preservando l'ordine di apparizione
classes = list(dict.fromkeys(y_train))[:5]

# Per ogni classe (colonna) mostriamo 4 immagini (righe)
for col, cls in enumerate(classes):
    # Indici delle immagini di training appartenenti a questa classe
    indices = [i for i, label in enumerate(y_train) if label == cls]

    for row in range(4):
        ax = axes[row, col]
        ax.axis('off')  # nascondiamo gli assi: stiamo mostrando foto

        # Se ci sono abbastanza immagini per questa classe, ne mostriamo una
        if row < len(indices):
            image_path = X_train[indices[row]]
            image = Image.open(image_path).convert('RGB')
            ax.imshow(image)
            # Mettiamo il titolo solo sulla prima riga di ogni colonna
            if row == 0:
                ax.set_title(str(cls), fontsize=10)

plt.tight_layout()
plt.show()


# --- One-hot encoding delle etichette ---------------------------------------
# Le reti neurali lavorano con vettori numerici, non con stringhe. Convertiamo
# quindi le etichette testuali (es. "CARTA") in vettori one-hot di lunghezza
# pari al numero di classi. Esempio con 5 classi:
#   CARTA          -> [1, 0, 0, 0, 0]
#   PLASTICA       -> [0, 1, 0, 0, 0]
#   VETRO_METALLO  -> [0, 0, 1, 0, 0]
#   ORGANICO       -> [0, 0, 0, 1, 0]
#   NON_RECUPER.   -> [0, 0, 0, 0, 1]

# Lista ordinata di tutti i nomi di classe (ordine alfabetico, riproducibile)
class_names = sorted(list(set(labels)))
num_classes = len(class_names)

# Dizionario: nome di classe -> indice numerico
label_to_index = {name: i for i, name in enumerate(class_names)}

# Convertiamo le etichette di training e test in indici interi
y_train_indices = [label_to_index[label] for label in y_train]
y_test_indices = [label_to_index[label] for label in y_test]

# E poi in formato one-hot (matrice di 0 e 1)
y_train_cat = keras.utils.to_categorical(y_train_indices, num_classes=num_classes)
y_test_cat = keras.utils.to_categorical(y_test_indices, num_classes=num_classes)

print("Nomi delle classi:", class_names)
print("Numero di classi:", num_classes)
print("Prime 5 etichette di training:", y_train_cat[:5])
print("Prime 5 etichette di test:", y_test_cat[:5])


# ============================================================================
# 2.bis  DATA AUGMENTATION  (TODO - da completare a cura dello studente)
# ============================================================================
# La data augmentation permette di aumentare la varieta' dei dati di
# addestramento applicando trasformazioni casuali (rotazioni, flip, zoom,
# variazioni di luminosita', ecc.) alle immagini esistenti.
# Questo aiuta la rete a generalizzare meglio.
#
# Suggerimento: usate layers.RandomFlip, layers.RandomRotation, layers.RandomZoom, ecc.
#
# Esempio (da implementare e adattare):
#   data_augmentation = keras.Sequential([
#       layers.RandomFlip("horizontal"),
#       layers.RandomRotation(0.1),
#       layers.RandomZoom(0.1),
#   ])
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
# Definiamo una rete neurale convoluzionale (CNN) "vanilla", strutturata in
# blocchi convoluzionali seguiti da un classificatore fully-connected.
#
# La rete e' simile a quella vista in classe su MNIST, con due differenze:
#   - le immagini sono piu' grandi (224x224 invece di 28x28)
#   - le immagini sono a colori, quindi hanno 3 canali (RGB) invece di 1

cnn_model = models.Sequential(name="CNN_INDIFFERENZIATA")

# --- Input ---
# Specifichiamo la forma dell'input: immagini 224x224 con 3 canali (R, G, B)
cnn_model.add(layers.Input(shape=(224, 224, 3)))

# --- Normalizzazione ---
# I pixel sono originariamente interi nell'intervallo [0, 255]. Per facilitare
# l'apprendimento li riscaliamo nell'intervallo [0, 1] dividendo per 255.
cnn_model.add(layers.Rescaling(1./255))

# --- BLOCCO CONVOLUZIONALE 1 ---
# Conv2D(32, (3,3)): 32 filtri 3x3 -> estraggono caratteristiche base
#                    (bordi, transizioni di colore, ecc.)
# padding='same':    l'output ha le stesse dimensioni spaziali dell'input
# MaxPooling 2x2:    dimezza altezza e larghezza, riduce il numero di parametri
cnn_model.add(layers.Conv2D(32, (3, 3), activation='relu', padding='same'))
cnn_model.add(layers.MaxPooling2D((2, 2)))

# --- BLOCCO CONVOLUZIONALE 2 ---
# Raddoppiamo i filtri a 64: pattern via via piu' complessi a piu' profondita'
cnn_model.add(layers.Conv2D(64, (3, 3), activation='relu', padding='same'))
cnn_model.add(layers.MaxPooling2D((2, 2)))

# --- CLASSIFICATORE FULLY-CONNECTED ---
# Flatten:  trasforma la mappa di feature 3D in un vettore 1D
# Dense 128: strato denso con 128 neuroni e attivazione ReLU
# Dropout 0.5: durante il training "spegne" il 50% dei neuroni a caso,
#              riducendo l'overfitting
# Dense finale: un neurone per ogni classe + softmax per ottenere probabilita'
cnn_model.add(layers.Flatten())
cnn_model.add(layers.Dense(128, activation='relu'))
cnn_model.add(layers.Dropout(0.5))
cnn_model.add(layers.Dense(len(class_names), activation='softmax'))

# --- Compilazione del modello ---
# optimizer='adam':                ottimizzatore con learning rate adattivo
# loss='categorical_crossentropy': funzione di costo standard per la
#                                  classificazione multi-classe con one-hot
# metrics=['accuracy']:            metrica che vogliamo monitorare
cnn_model.compile(
    optimizer='adam',
    loss='categorical_crossentropy',
    metrics=['accuracy'],
)

# Stampa la struttura della rete e il numero di parametri
cnn_model.summary()


# ============================================================================
# 3.bis  VARIARE L'ARCHITETTURA  (TODO)
# ============================================================================
# Sperimentate variazioni dell'architettura sopra (numero di blocchi, numero
# di filtri, dimensione del kernel, presenza di BatchNormalization, ecc.)
# per vedere come cambiano le prestazioni della rete.
# ============================================================================


# ============================================================================
# 4. CARICAMENTO DELLE IMMAGINI E ADDESTRAMENTO
# ============================================================================
# Finora abbiamo lavorato solo con i PERCORSI delle immagini. Per addestrare
# la rete dobbiamo invece caricarle effettivamente in memoria come tensori
# numerici di forma (224, 224, 3).

def carica_immagini(lista_percorsi):
    """
    Carica un elenco di immagini da disco, le converte in RGB e le
    ridimensiona a 224x224 pixel. Restituisce un array numpy di forma
    (N, 224, 224, 3) pronto a essere dato in input alla rete.
    """
    immagini = []
    for percorso in lista_percorsi:
        img = Image.open(percorso).convert('RGB')   # forziamo 3 canali
        img = img.resize((224, 224))                # dimensione fissa per la rete
        immagini.append(np.array(img))
    return np.array(immagini)


print("Caricamento immagini in corso...")
X_train_img = carica_immagini(X_train)   # tensore di training (N_train, 224, 224, 3)
X_test_img = carica_immagini(X_test)     # tensore di test     (N_test,  224, 224, 3)
print("Immagini caricate con successo")


# --- Addestramento della CNN ------------------------------------------------
# epochs=30:     numero di passaggi completi sul training set
# batch_size=32: la rete elabora 32 immagini per volta prima di aggiornare i pesi
# shuffle=True:  a OGNI EPOCA il training set viene rimescolato prima di
#                essere suddiviso in batch. Questo e' fondamentale: senza
#                shuffle, dato che i dati arrivano ancora "ordinati" dallo
#                split iniziale, ogni batch da 32 immagini rischierebbe di
#                contenere prevalentemente una sola classe -> gradienti molto
#                rumorosi e addestramento instabile.
#                Il validation_data invece NON viene mescolato (non avrebbe
#                senso: serve solo per valutare, non per addestrare).
# validation_data: durante il training valutiamo anche sul test set per vedere
#                  se la rete sta facendo overfitting

print("Inizio addestramento CNN...")
history_cnn = cnn_model.fit(
    X_train_img, y_train_cat,
    validation_data=(X_test_img, y_test_cat),
    epochs=30,
    batch_size=32,
    shuffle=True,        # rimescola il training set a ogni epoca
)


# ============================================================================
# 5. FINE-TUNING (TRANSFER LEARNING)
# ============================================================================
# Anziche' partire da zero, riutilizziamo una rete pre-addestrata su ImageNet
# (milioni di immagini). Useremo MobileNetV2: una rete leggera ed efficiente
# pensata per dispositivi mobili.
#
# Strategia:
#   1) Carichiamo MobileNetV2 senza il suo classificatore finale (include_top=False)
#   2) "Congeliamo" i pesi del modello base (base_model.trainable = False) cosi'
#      l'addestramento aggiornera' solo i nostri strati finali
#   3) Aggiungiamo un nostro classificatore in cima per le 5 classi della
#      differenziata di Torino

# --- Caricamento del modello pre-addestrato ---
base_model = keras.applications.MobileNetV2(
    input_shape=(224, 224, 3),
    include_top=False,        # togliamo il classificatore originale (1000 classi ImageNet)
    weights='imagenet',       # usiamo i pesi appresi su ImageNet
)
base_model.trainable = False  # CONGELIAMO i pesi: non li alleniamo

# --- Costruzione del modello completo ---
# GlobalAveragePooling2D: comprime ogni mappa di feature in un singolo valore
#                         (alternativa piu' efficiente di Flatten)
# Dropout 0.2: regolarizzazione leggera
# Dense softmax: classificatore finale con un neurone per classe
tl_model = models.Sequential([
    base_model,
    layers.GlobalAveragePooling2D(),
    layers.Dropout(0.2),
    layers.Dense(len(class_names), activation='softmax'),
])

tl_model.compile(
    optimizer='adam',
    loss='categorical_crossentropy',
    metrics=['accuracy'],
)


# --- Addestramento del modello in transfer learning -------------------------
# Stessi parametri della CNN da zero: anche qui shuffle=True per evitare batch
# composti da una sola classe.
print("Inizio addestramento Transfer Learning...")
history_tl = tl_model.fit(
    X_train_img, y_train_cat,
    validation_data=(X_test_img, y_test_cat),
    epochs=30,
    batch_size=32,
    shuffle=True,        # rimescola il training set a ogni epoca
)


# ============================================================================
# 6. VALUTAZIONE DEL SISTEMA
# ============================================================================
# Per valutare le reti useremo tre strumenti complementari:
#   1) Curve di training: accuracy e loss epoca per epoca
#   2) Report di classificazione: Accuracy, Precision, Recall, F1
#   3) Matrice di confusione: ci dice QUALI classi vengono confuse tra loro

def plot_history(history, title):
    """
    Disegna due grafici affiancati con l'evoluzione di accuracy e loss
    sul training set e sul validation set. Permette di "vedere"
    eventuale overfitting (training che cresce mentre validation peggiora).
    """
    plt.figure(figsize=(12, 4))

    # Grafico 1: accuracy
    plt.subplot(1, 2, 1)
    plt.plot(history.history['accuracy'], label='Train')
    plt.plot(history.history['val_accuracy'], label='Val')
    plt.title(f'Accuracy - {title}')
    plt.legend()

    # Grafico 2: loss
    plt.subplot(1, 2, 2)
    plt.plot(history.history['loss'], label='Train')
    plt.plot(history.history['val_loss'], label='Val')
    plt.title(f'Loss - {title}')
    plt.legend()

    plt.show()


def evaluate_model(model, X, y_true_indices, title):
    """
    Stampa il report di classificazione (Accuracy, Precision, Recall,
    F1-score per classe) confrontando le predizioni del modello con le
    etichette vere.
    """
    print(f"\n--- Valutazione {title} ---")

    # predict restituisce, per ogni immagine, un vettore di probabilita'
    # lungo num_classes (una probabilita' per classe).
    predictions = model.predict(X, verbose=0)

    # argmax estrae la classe con probabilita' massima -> indice intero
    y_pred_indices = np.argmax(predictions, axis=1)

    # classification_report calcola le metriche standard di classificazione
    report = classification_report(
        y_true_indices,
        y_pred_indices,
        target_names=class_names,
    )
    print(report)


# --- Visualizziamo le curve di training delle due reti ----------------------
# E' fondamentale guardare PRIMA le curve di addestramento: ci aiutano a
# capire se la rete e' andata in overfitting (es. accuracy di training molto
# piu' alta della accuracy di validation), in underfitting o se l'addestramento
# si e' fermato troppo presto / troppo tardi.
plot_history(history_cnn, "CNN da zero")
plot_history(history_tl, "Transfer learning")

# TODO (analisi): come commentate questi quattro grafici? Il fatto che
#                 l'accuracy di training della CNN da zero superi (e di molto)
#                 quella di validation cosa indica? Stesse riflessioni
#                 per la rete in transfer learning.


# --- Report di classificazione ----------------------------------------------
# Valutiamo le due reti sul test set con le metriche standard.
evaluate_model(cnn_model, X_test_img, y_test_indices, "CNN da zero")
evaluate_model(tl_model, X_test_img, y_test_indices, "Transfer learning")


# --- Matrice di confusione --------------------------------------------------
# La matrice di confusione mostra, per ogni coppia (classe vera, classe predetta),
# la frequenza delle predizioni. Sulla DIAGONALE PRINCIPALE troviamo le
# predizioni corrette; fuori diagonale gli errori.
#
# Usiamo la versione NORMALIZZATA (normalize='true'): ogni riga somma a 1 e i
# valori rappresentano percentuali rispetto al supporto della classe vera.
# Questo evita che classi piu' numerose "schiaccino" visivamente quelle piu'
# rare.

def show_confusion_matrices(models_dict, X, y_true_indices):
    """
    Visualizza fianco a fianco la matrice di confusione (normalizzata)
    di ogni modello passato nel dizionario models_dict.

    models_dict: dizionario { "titolo": modello_keras, ... }
    """
    num_models = len(models_dict)
    fig, axes = plt.subplots(1, num_models, figsize=(8 * num_models, 7))

    # Se c'e' un solo modello, plt.subplots restituisce un singolo Axes
    # invece di una lista: lo trasformiamo in lista per uniformita'.
    if num_models == 1:
        axes = [axes]

    for ax, (title, model) in zip(axes, models_dict.items()):
        # Predizioni del modello
        predictions = model.predict(X, verbose=0)
        y_pred_indices = np.argmax(predictions, axis=1)

        # Matrice normalizzata per riga (ogni riga somma a 1)
        cm_normalized = confusion_matrix(
            y_true_indices,
            y_pred_indices,
            normalize='true',
        )

        # Visualizzazione "carina" con etichette di classe sugli assi
        disp = ConfusionMatrixDisplay(
            confusion_matrix=cm_normalized,
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


# Dizionario dei modelli da confrontare
models_to_compare = {
    "CNN da zero": cnn_model,
    "Transfer learning": tl_model,
}

# Generiamo le matrici di confusione
show_confusion_matrices(models_to_compare, X_test_img, y_test_indices)

# TODO: in quali casi la versione NON normalizzata della matrice di confusione
#       puo' essere fuorviante? Provate a generarla (basta togliere
#       normalize='true' nella chiamata a confusion_matrix) e confrontatela
#       con quella normalizzata.


# ============================================================================
# 6.bis  ESTENDERE LA VALUTAZIONE  (TODO)
# ============================================================================
# Una volta implementate le vostre varianti (data augmentation, dataset
# esterni, architetture alternative, ecc.) estendete il dizionario
# models_to_compare per includerle, e rieseguite le valutazioni. In questo
# modo potrete confrontare quantitativamente l'efficacia delle diverse
# scelte progettuali.
# ============================================================================