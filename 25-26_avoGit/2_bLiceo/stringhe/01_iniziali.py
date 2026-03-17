# Esercizio 1
#  **Scrivi un programma che analizzi le seguenti stringhe e per ognuna stampi le lettere una ad una:
#  banana, cipolla, bambino, finestra, girotondo.**
print("Esercizio 1")
words = ["banana", "cipolla", "bambino", "finestra", "girotondo"]
for word in words:
    for char in words:
        print(char, end=" ")
print("##########")

#Scrivi un programma che stampi la lunghezza di una stringa data (es. "programmazione").
print("Esercizio 2")
word = input("inserisci una parola:")
print("la stringa inserita è lunga:", len(word), "lettere")
print("##########")

# Scrivi un programma che stampi il primo e l'ultimo carattere di una stringa
print("Esercizio 3")
word = input("inserisci una parola:")
print("primo carattere:", word[0], "ultimo carattere:", word[-1])
print("##########")

# Scrivi un programma che stampi i caratteri di una stringa al contrario, uno per riga
print("Esercizio 4")
word = input("inserisci una parola:")
print("la parola al contrario è:", word[::-1])
print("##########")


# Scrivi un programma che conta il numero di volte in cui la lettera 'a' compare in una stringa, usando un contato
print("Esercizio 5")
word = input("inserisci un parola: ")
letter = "a" # si può leggere input utente per contare generico carattere
# lettera = input("inserisci una lettera:")
count = 0
for char in word:
    if char == letter:
        count = count + 1
print(f"la lettera {letter} appare {count} volte")
print("###########")

# Esercizio 6 Scrivi un programma che conti quante vocali (a, e, i, o, u) sono presenti in una stringa data.*
print("Esercizio 6")
word = input("inserisci una parola: ")
vowels = "aeiou"
for char in word:
    if char in vowels:
        print(char)
print("###########")

# Scrivi un programma che conti quante consonanti sono presenti in una stringa data
print("Esercizio 7")
word = input("inserisci una parola: ")
vowels = "aeiou"
count = 0
for char in word:
    if char not in vowels:
        count += 1
print(f"la parola {word} contiene {count} consonanti")
print("###########")

# Scrivi un programma che conti quanti spazi sono presenti in una frase
print("Esercizio 8")

phrase = input("Inserisci una frase: ")
counter = 0

# Scorro ogni carattere della frase
for character in phrase:
    # Se il carattere è uno spazio, incremento il contatore
    if character == " ":
        counter += 1

print("Numero di spazi:", counter)
print("###########")

# Scrivi un programma che verifichi se una stringa contiene la lettera 'z' e stampi un messaggio appropriato
print("Esercizio 9")
word = input("inserisci una parola: ")
letter = "z"
if letter in word:
    print(f"la parola {word} contiene la lettera {letter}")
else:
    print(f"la parola {word} non contiene la lettera {letter}")
print("###########")

# Scrivi un programma che trovi e stampi tutte le posizioni (indici) in cui compare la lettera 'o' in una stringa
# si deve lavorare con ciclo for con posizione e non per elemento
print("Esercizio 10")
word = input("inserisci una parola: ")
letter = "o"
positions = []
for i in range(len(word)):
    if word[i] == letter:
        positions.append(i) # non posso usare index perché?
print("La lettera {letter} compare nelle seguenti posizioni: ", end="")
for pos in positions:
    print(pos, end=" ")
print("###########")

# Scrivi un programma che verifichi se una stringa è palindroma (si legge uguale da sinistra a destra e viceversa).
# Esempio: "anna", "radar", "osso".
print("Esercizio 11 - versione meno pythonica - preferita perché si pensa un algoritmo e "
      "si sviluppa pensiero computazionale"
      "e in linea con altri linguaggi di programmazione come C o Java che non hanno slicing")
word = input("inserisci un parola: ").lower() # per gestire maiuscole e minuscole
half_len = len(word) // 2
palindrom = True
for i in range(half_len):
    if word[i] != word[len(word)-1-i]:
        palindrom = False
        break # così si esce dal ciclo appena una lettera è diversa
print("la stringa è palindroma:", palindrom)
print("###########")

print("Esercizio 11 - versione pythonica")
word = input("inserisci un parola: ").lower() # per gestire maiuscole e minuscole
print("la stringa è palindroma:", word == word[::-1])

# Scrivi un programma che conti quante parole ci sono in una frase letta in input da utente
# (suggerimento: conta gli spazi e aggiungi 1).
# Esercizio - Conta le parole in una frase
print("Esercizio 9- Conta parole")

phrase = input("Inserisci una frase: ")
counter = 0

# Scorro ogni carattere della frase
for character in phrase:
    # Se il carattere è uno spazio, incremento il contatore
    if character == " ":
        counter += 1

# Le parole sono gli spazi + 1
word_count = counter + 1

print("Numero di parole:", word_count)
print("Meglio usare il metodo split()")
# split() divide la frase in una lista di parole
word_count = len(phrase.split())
print("Numero di parole con split", word_count)
print("###########")

# 13 Conta i nomi con lunghezza maggiore di 5 caratteri
print("Esercizio 13")

names = ["Andrea", "Luca", "Valentina", "Sara", "Matteo", "Al"]
counter = 0

# Scorro ogni nome nella lista
for name in names:
    # Se il nome ha più di 5 caratteri, incremento il contatore
    if len(name) > 5:
        counter += 1

print("Nomi con più di 5 caratteri:", counter)
print("####")


# 14- Stampa solo le parole che iniziano con la lettera 'a'
print("Esercizio 14")

words = ["arancia", "banana", "ananas", "mela", "albicocca", "pera"]

# Scorro ogni parola nella lista
for word in words:
    # Se la parola inizia con 'a', la stampo
    if word[0] == "a":
        print(word)

print("####")

# 15 Trova e stampa la parola più lunga
print("Esercizio 15")

words = ["arancia", "banana", "ananas", "mela", "albicocca", "pera"]
longest_word = words[0]  # Parto dal primo elemento come riferimento

# Scorro ogni parola nella lista
for word in words:
    # Se la parola corrente è più lunga della più lunga trovata finora, aggiorno
    if len(word) > len(longest_word):
        longest_word = word

print("Parola più lunga:", longest_word)
print("####")


# Esercizio 15 - Crea una nuova lista con la lunghezza di ogni parola
print("Esercizio 15")

words = ["arancia", "banana", "ananas", "mela", "albicocca", "pera"]
lengths = []

# Scorro ogni parola nella lista
for word in words:
    # Aggiungo la lunghezza della parola alla nuova lista
    lengths.append(len(word))

print("Parole:", words)
print("Lunghezze:", lengths)
print("####")