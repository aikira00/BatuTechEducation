
num = 1
input_list_collatz = []
list_val_collatz_all = []
liste_steps  = []

while(num > 0):
    num = int(input("Inserisci un numero per cui vuoi calcolare la sequenza di Collatz, negativo per terminare inserimento valori "))
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
indice = int(input("Quale sequenza vuoi visualizzare? (0 per la prima, 1 per la seconda, ...): "))

# Verifica che l'indice sia valido
if 0 <= indice < len(list_val_collatz_all):
    # Recupera la sequenza e i passi selezionati
    sequenza_scelta = list_val_collatz_all[indice]
    passi_scelti = liste_steps[indice]
    numero_iniziale = input_list_collatz[indice]  # Il numero iniziale corrispondente

    # Crea il grafico
    plt.plot(passi_scelti, sequenza_scelta, marker='o', color='blue', linewidth=2)
    plt.title(f"Sequenza di Collatz per n={numero_iniziale}")
    plt.xlabel("Passo")
    plt.ylabel("Valore")
    plt.grid(True, alpha=0.3)
    plt.show()
else:
    print("Indice non valido!")