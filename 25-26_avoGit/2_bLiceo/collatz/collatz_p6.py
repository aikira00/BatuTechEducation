# Modifica il programma per visualizzare tutte le sequenze nello stesso grafico, usando colori diversi per ciascuna.
# Suggerimento: usa un ciclo for e ripeti plt.plot(...) per ogni sequenza prima di chiamare plt.show().
num = 1
input_list_collatz = []
list_val_collatz_all = []
liste_steps  = []

while(num > 0):
    num = int(input("Inserisci un numero per cui vuoi calcolare la sequenza di Collatz, negativo per terminare inserimento valori "))
    if num > 0:
        input_list_collatz.append(num)

print(f"Hai inserito {len(input_list_collatz)} numeri per calcolare la sequenza di Collatz... procediamo")
for i in range(0, len(input_list_collatz)):
    steps = 0
    n_collatz = input_list_collatz[i]
    list_val_collatz = []

    # calcolo Collatz
    while n_collatz != 1:
        if n_collatz % 2 == 0:
            n_collatz = n_collatz // 2
        else:
            n_collatz= 3 * n_collatz + 1
        list_val_collatz.append(n_collatz)
        steps += 1
    list_val_collatz_all.append(list_val_collatz)
    liste_steps.append(steps)
    print(f"Il numero {input_list_collatz[i]} ha richiesto {steps} passi per calcolare la sequenza di Collatz")
    print(f"La sequenza di Collatz per il numero {input_list_collatz[i]} è: ", end = " ")
    for el in list_val_collatz:
        print(el, end = " ")
    print("")
print("Fine calcolo")

import matplotlib.pyplot as plt

# Chiedi all'utente quale sequenza visualizzare
print(f"\nSono state calcolate {len(list_val_collatz_all)} sequenze.")
plt.figure(figsize=(10, 6)) # Imposta una dimensione leggibile
for i in range(len(list_val_collatz_all)):

    # Recupera la sequenza e i passi selezionati
    sequenza_scelta = list_val_collatz_all[i]
    ## Creiamo una lista di passi (asse x) da 0 a N per questa specifica sequenza
    list_passi = list(range(len(sequenza_scelta)))
    numero_iniziale = input_list_collatz[i] # Il numero iniziale corrispondente

    # Crea il grafico
    plt.plot(list_passi, sequenza_scelta, marker='o', linewidth=2, label=f'Inizio: {numero_iniziale}')

# Personalizzazione del grafico
plt.title("Confronto Sequenze di Collatz")
plt.xlabel("Numero di Passaggi (Steps)")
plt.ylabel("Valore")
plt.grid(True, linestyle='--', alpha=0.6)
plt.legend()  # Mostra la legenda con i colori per ogni numero
plt.show()