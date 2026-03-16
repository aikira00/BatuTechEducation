# Es 1. Scrivi un programma che stampi tutti gli elementi di una lista di nomi, uno per riga.
list = ["apple", "banana", "cherry", "cholate"]
for i in range(len(list)):
    print(list[i])
# oppure
for el in list:
    print(el)

# Es 2. Scrivi un programma che stampi gli elementi di una lista di numeri insieme al loro indice (posizione).
# qui meglio usare range per stampare anche indice
for i in range(len(list)):
    print(f"Elemento {i}: {list[i]}")

# Es 3. Scrivi un programma che trovi e stampi il valore massimo in una lista di numeri.
# Devi trovare il massimo iterando sulla lista.
# Paragona il tuo risultato con la funzione built-in **max** e **assert**
numbers = [3,10,15,34,23,9, 107, 56, 134]
max_num = numbers[0]
for i in range(1, len(numbers)):
    if numbers[i] > max_num:
        max_num = numbers[i]
print(f"Il mio massimo è {max_num}, check con funzione built-in {max(numbers)}")
assert max_num == max(numbers), "Il mio codice ha un errore, il massimo non corrisponde"

# Es. 4 min
numbers = [3,10,15,34,23,9, 107, 2, 134]
min_num = numbers[0]
for i in range(1, len(numbers)):
    if numbers[i] < min_num:
        min_num = numbers[i]
print(f"Il mio minimo è {min_num}, check con funzione built-in {min(numbers)}")
assert min_num == min(numbers), "Il mio codice ha un errore, il minimo non corrisponde"

# Es 5. **Scrivi un programma che conti quanti numeri pari sono presenti in una lista di numeri interi.**
numbers = [3,10,15,34,23,9, 107, 2, 134]
count = 0
for i in range(len(numbers)):
    if numbers[i] % 2 == 0:
        count += 1
print(f"Ci sono {count} numeri pari in questa lista")

# Es. 6 **Scrivi un programma che verifichi se un determinato valore è presente in una lista e stampi
# la sua posizione (indice), oppure un messaggio se non è presente.**
numbers = [3,10,15,34,23,9, 107, 2, 134]
el_to_find = 107
index_found = -1
for i in numbers:
    if i == el_to_find:
       index_found = numbers.index(i)
if index_found == -1:
    print(f"NON Trovato l'elemento {el_to_find} ")
else:
    print(f"Trovato {el_to_find} nella posizione {index_found}")

#versione più Python
if el_to_find in numbers:
    print(f"Trovato {el_to_find} nella posizione {numbers.index(el_to_find)}")
else:
    print(f"Valore {el_to_find} non presente nella lista")

# Es 7 **Scrivi un programma che conti quante volte un determinato valore appare in una lista.
# Verifica il risultato con il metodo built-in `count()` usando assert **
numbers = [3,10,15,34,23,9, 107, 2, 134, 34, 567, 65, 56, 34, 2, 3, 34, 34, 24, 34]
num_to_find = 34
num_times_found = 0
for i in numbers:
    if i == num_to_find:
        num_times_found += 1
print(f"Il numero {num_to_find} compare {num_times_found} volte nella lista")
assert num_times_found == numbers.count(num_to_find), "Il mio codice ha un errore, il numero non compare nella lista"

# Es 8 Scrivi un programma che conti quante volte un determinato valore appare in una lista e stampi
# le posizioni in cui è stato trovato
numbers = [3,10,15,34,23,9, 107, 2, 134, 34, 567, 65, 56, 34, 2, 3, 34, 34, 24, 34]
num_to_find = 34
positions = []
for i in range(len(numbers)): # oppure enumerate(numbers)
    if numbers[i] == num_to_find:
        positions.append(i) # attenzine a non usare index che ritorna solo la prima occorrenza
print(f"Il numero {num_to_find} compare {len(positions)} volte nella lista nelle seguenti posizioni: {positions}")

# Es 9 **Scrivi un programma che calcoli la media dei valori in una lista di numeri.**
numbers = [3,10,15,34,23,9, 107, 2, 134, 34, 567, 65, 56, 34, 2, 3, 34, 34, 24, 34]
somma = 0
for el in numbers:
    somma += el
print(f"la media è {somma/len(numbers)}")