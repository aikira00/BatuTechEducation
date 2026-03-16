
num = 1
input_list_collatz = []
all_list_val_collatz = []
while(num >0):
    num = int(input("Inserisci un numero per cui vuoi calcolare la sequenza di Collatz, negativo per terminare inserimento valori "))
    input_list_collatz.append(num)

print(f"Hai inserito {len(input_list_collatz)} numeri per calcolare la sequenza di Collatz... procediamo")
for i in range(0, len(input_list_collatz)):
    steps = 0
    n_collatz = input_list_collatz[i]
    list_val_collatz = []
    while n_collatz != 1:
        if n_collatz % 2 == 0:
            n_collatz = n_collatz // 2
        else:
            n_collatz= 3 * n_collatz + 1
        list_val_collatz.append(n_collatz)
        steps += 1
    all_list_val_collatz.append(list_val_collatz)
    print(f"Il numero {input_list_collatz[i]} ha richiesto {steps} passi per calcolare la sequenza di Collatz")
    print(f"La sequenza di Collatz per il numero {input_list_collatz[i]} è: ", end = " ")
    for el in list_val_collatz:
        print(el, end = " ")
    print("")
print("Fine calcolo")
index = 0
index = int(input("Inserisci indice per visualizzare sequenza di Collatz per numero i-esimo nella lista specifico tra 1 {len(all_list_val_collatz)}:"))

import matplotlib.pyplot as plt
plt.plot(passi, valori, marker='o')
plt.title("Sequenza di Collatz per n=7")
plt.xlabel("Passo")
plt.ylabel("Valore")
plt.grid(True)
plt.show()