# Un anno è "felice" se applicando ripetutamente la regola
# "somma dei quadrati delle cifre" si arriva a 1.
# Se invece si entra in un ciclo che non contiene 1, l'anno è "infelice".
#
# Esempio: 19 -> 1² + 9² = 82 -> 8² + 2² = 68 -> ... -> 1   (FELICE)

import os

# Cartella dello script (codice/), indipendente da dove lo lanci
script_dir = os.path.dirname(os.path.abspath(__file__))
# Radice del progetto: un livello sopra
radice_dir = os.path.dirname(script_dir)

# Percorsi ai file di input e output
file_input  = os.path.join(radice_dir, "dati", "anni_input.txt")
file_output = os.path.join(radice_dir, "dati", "risultati.txt")

print(f"Leggo da:  {file_input}")
print(f"Scrivo su: {file_output}")


def verifica_felice(n):
    # NOTA DIDATTICA: la lista 'visti' è NECESSARIA per fermare il ciclo.
    # Su un numero infelice (es. 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4 -> ...)
    # la sequenza entra in un loop che non contiene mai 1. Senza memorizzare
    # i numeri già incontrati, il while girerebbe all'infinito.
    # Questo è un caso chiaro in cui una struttura dati (la lista) è
    # indispensabile per risolvere il problema: dobbiamo accumulare la storia
    # dei valori visti per riconoscere quando ci stiamo ripetendo.
    visti = []
    while n != 1 and n not in visti:
        visti.append(n)
        somma = 0
        for cifra in str(n):
            somma += int(cifra) ** 2
        n = somma
    return n == 1


try:
    with open(file_input, 'r') as file_in, open(file_output, 'w') as file_out:
        for riga in file_in:
            anno = int(riga.strip())
            if verifica_felice(anno):
                file_out.write(f"{anno} -> FELICE\n")
            else:
                file_out.write(f"{anno} -> non felice\n")
    print(f"\nElaborazione completata. Risultati salvati in {file_output}")
except FileNotFoundError:
    print(f"\nErrore: non trovo il file {file_input}")
    print("Verifica che la cartella 'dati' esista e contenga 'anni_input.txt'.")