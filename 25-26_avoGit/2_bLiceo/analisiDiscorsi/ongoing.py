def conta_parole(testo):
    # Pulizia: minuscolo, rimuovi punteggiatura
    testo = testo.lower()
    for segno in [",", ".", "!", "?", ";", ":", '"', "'"]:
        testo = testo.replace(segno, "")

    parole_testo = testo.split()

    parole = []
    conteggi = []

    for parola in parole_testo:
        if parola in parole:
            indice = parole.index(parola)
            conteggi[indice] += 1
        else:
            parole.append(parola)
            conteggi.append(1)

    return parole, conteggi


def frequenze_relative(conteggi):
    totale = sum(conteggi)
    return [c / totale for c in conteggi]


def top_n(parole, conteggi, n):
    # selection sort discendente su conteggi, trascina parole
    for i in range(len(conteggi)):
        max_idx = i
        for j in range(i+1, len(conteggi)):
            if conteggi[j] > conteggi[max_idx]:
                max_idx = j
        conteggi[i], conteggi[max_idx] = conteggi[max_idx], conteggi[i]
        parole[i], parole[max_idx] = parole[max_idx], parole[i]
    return parole[:n], conteggi[:n]

import matplotlib.pyplot as plt
import numpy as np

def grafico_confronto(parole1, freq1, parole2, freq2, nome1, nome2, n=10):
    x = np.arange(n)
    larghezza = 0.35

    fig, ax = plt.subplots(figsize=(12, 6))
    ax.bar(x - larghezza/2, freq1[:n], larghezza, label=nome1, color='steelblue')
    ax.bar(x + larghezza/2, freq2[:n], larghezza, label=nome2, color='tomato')

    ax.set_xlabel("Parole")
    ax.set_ylabel("Frequenza relativa")
    ax.set_title(f"Top {n} parole più usate: {nome1} vs {nome2}")
    ax.set_xticks(x)
    ax.set_xticklabels(parole1[:n], rotation=45, ha='right')
    ax.legend()
    plt.tight_layout()
    plt.show()