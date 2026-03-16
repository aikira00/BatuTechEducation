lista = []

num = int(input("Inserisci un numero per calcolare la sequenza di Collatz "))
steps = 0

n_collatz = num
while n_collatz != 1:
    if n_collatz % 2 == 0:
        n_collatz = n_collatz // 2
    else:
        n_collatz= 3 * n_collatz + 1
    lista.append(n_collatz)
    steps += 1

print(f"{num}: {steps} passi")
print(f"La sequenza di Collatz per il numero {num} è")
for el in lista:
    print(el, end = " ")