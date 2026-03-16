lista = ["banana", "cipolla", "bambino", "finestra", "girotondo"]
for parola in lista:
    for carattere in parola:
        print(carattere, end=" ")

Es2
stringa = input("inserisci una parola:")
print("la stringa inserita è lunga:", len(stringa), "lettere")

es3

stringa = input("inserisci una parola:")
print("la parola al contrario è:", stringa[::-1])

es4
stringa = input("inserisci un parola: ")
count = 0
for carattere in stringa:
    if carattere == "a":
        count = count + 1
print(count)

es5
stringa = input("inserisci un parola: ")
count = 0
lettera = input("inserisci una lettera:")
for carattere in stringa:
    if carattere == lettera:
        count = count + 1
print(count)

stringa = input("inserisci un parola: ")
lista = []
lettera = input("inserisci una lettera:")
for i in range(len(lista)):
    if lista[i] == lettera:
        lista.append(i)
print(lista)
print(len(lista))