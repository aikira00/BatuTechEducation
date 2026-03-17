# Scrivi un programma che, data una lista di 10 numeri, estragga e stampi i primi 3 elementi,
# gli ultimi 3 elementi e gli elementi dalla posizione 3 alla posizione 7.**

numbers = [3,10,15,34,23,9, 107, 2, 134]
print(numbers)
print(f"Primi 3 elementi {numbers[0:3]}")
print(f"Ultimi 3 elementi {numbers[-3:]}")
print(f"Elementi da 3 a 7 {numbers[3:7]}")

numbers.insert(3,120)
numbers.append(100)
numbers.append(250)
numbers.insert(4,35)

print(numbers)
print(f"Primi 3 elementi {numbers[0:3]}")
print(f"Ultimi 3 elementi {numbers[-3:]}")
print(f"Elementi da 3 a 7 {numbers[3:7]}")

# es 10 Scrivi un programma che, data una lista di numeri, crei tre nuove liste: una contenente gli elementi
# in posizione pari (indice pari), una con gli elementi in posizione dispari, e una con gli elementi al contrario.
numbers = [3,10,15,34,23,9, 107, 2, 134, 345]
even_numbers = numbers[::2]
odd_numbers = numbers[1::2]
reverse_numbers = numbers[::-1]
print(even_numbers)
print(odd_numbers)
print(reverse_numbers)

# Es 3. Riscrivi il ciclo for esercizio 3 per trovare il massimo usando
# lo slicing (attenzione al ciclo for da utilizzare)
numbers = [3,10,15,34,23,9, 107, 56, 134]
max_num = numbers[0]
for i in numbers[1:]: # attenzione al ciclo for non accedo con indice ma ho già elemento
    if i > max_num:
        max_num = i
print(f"Il mio massimo è {max_num}, check con funzione built-in {max(numbers)}")
assert max_num == max(numbers), "Il mio codice ha un errore, il massimo non corrisponde"