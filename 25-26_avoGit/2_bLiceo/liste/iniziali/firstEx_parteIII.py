# ES 11 **Scrivi un programma che crei una nuova lista contenente solo i numeri positivi
# da una lista data che contiene numeri sia positivi che negativi

numeri = [3, -10, 15, -34, 23, -9, 107, -2, 134, -345, 0, -5, 42]

positivi = []
for num in numeri:
    if num > 0:
        positivi.append(num)

print(f"Lista originale: {numeri}")
print(f"Solo positivi:   {positivi}")
#list comprehension
positivi = [num for num in numeri if num > 0 ]

# ES 12 **Scrivi un programma che raddoppi tutti i valori di una lista di numeri e stampi la lista risultante.**
numeri = [3, -10, 15, -34, 23, -9, 107, -2, 134, -345, 0, -5, 42]

double = []
for num in numeri:
   double.append(num * 2)

print(f"Lista originale: {numeri}")
print(f"Solo positivi:   {double}")

#list comprehension
double = [num * 2 for num in numeri]

# ES 13 **Scrivi un programma che inverta l'ordine degli elementi di una lista
# (senza usare metodi built-in come reverse()) ma usare quest'ultimo per controllare il vostro risultato con assert**
numeri = [3, -10, 15, -34, 23, -9, 107, -2, 134, -345, 0, -5, 42]
reverse_numbers = []
check_reverse = numeri.copy()
check_reverse.reverse()
for i in range(len(numeri)-1, -1, -1):
    reverse_numbers.append(numeri[i])
print(f"Lista originale: {numeri}")
print(f"Lista Invertita:   {reverse_numbers}")
print(f"Controllo con metodo reverse: {check_reverse}")


# ES 14 **Scrivi un programma che rimuova tutti i duplicati da una lista, creando una nuova lista con elementi unici.**
numbers = [3, -10, 15, -34, 23, -9, 107, -2, 15, -345, 0, 15, 42]
unique_numbers = []
for num in numeri:
    if num not in unique_numbers:
        unique_numbers.append(num)
print(f"Lista originale: {numeri}")
print(f"Lista con duplicati rimossi:   {unique_numbers}")

# Es 15 **Scrivi un programma che controlla se due liste sono uguali**
numbers = [3, 6, 9, 27, 42]
numbers2 = [3, 6, 9, 27, 42]
if numbers == numbers2:
    print("Le liste sono uguali")

