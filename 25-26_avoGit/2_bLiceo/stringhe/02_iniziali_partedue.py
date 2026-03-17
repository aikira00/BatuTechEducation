# **Scrivi un programma che trasformi tutti i caratteri di una stringa in maiuscolo, senza usare il metodo `.
# upper()` (suggerimento: usa le tabelle ASCII e `ord()` / `chr()`).**
print("Esercizio 17")
word = input("inserisci una parola: ")
upper_word = ""

#la funzione ORD() restituisce rappresentazione decimale
#la funzione chr restituisce il carattere data la rappresentazione decimale
# Scorro ogni carattere della parola
for char in word:
    # Se il carattere è una lettera minuscola (tra 'a' e 'z')
    if ord('a') <= ord(char) <= ord('z'):
        # Sottraggo 32 per ottenere il corrispondente maiuscolo
        upper_word += chr(ord(char) - 32)
    else:
        # Altrimenti lascio il carattere invariato (maiuscolo, numero, spazio...)
        upper_word += char

print("Parola in maiuscolo: " + upper_word)
print("####")

# Scrivi un programma che conti quante lettere maiuscole e quante minuscole sono presenti in una stringa.
# Maiuscole ASCII: da 65 ('A') a 90 ('Z')
# Minuscole ASCII: da 97 ('a') a 122 ('z')
print("Esercizio 18")

word = input("Inserisci una parola: ")
upper_counter = 0
lower_counter = 0

# Scorro ogni carattere della stringa
for char in word:
    # Se il carattere è una lettera maiuscola
    if ord('A') <= ord(char) <= ord('Z'):
        upper_counter += 1
    # Se il carattere è una lettera minuscola
    elif ord('a') <= ord(char) <= ord('z'):
        lower_counter += 1
    # Altrimenti è un numero, spazio o simbolo: lo ignoro

print("Lettere maiuscole:", upper_counter)
print("Lettere minuscole:", lower_counter)
print("####")

print("Esercizio 18 - con isupper e islower")

word = input("Inserisci una parola: ")
upper_counter = 0
lower_counter = 0

# Scorro ogni carattere della stringa
for char in word:
    # Se il carattere è una lettera maiuscola
    if char.isupper():
        upper_counter += 1
    # Se il carattere è una lettera minuscola
    elif char.islower():
        lower_counter += 1
    # Altrimenti è un numero, spazio o simbolo: lo ignoro

print("Lettere maiuscole:", upper_counter)
print("Lettere minuscole:", lower_counter)
print("####")

# Esercizio 19 - Rimuovi tutti gli spazi da una stringa
print("Esercizio 19")

phrase = input("Inserisci una frase: ")
result = ""

# Scorro ogni carattere della stringa
for char in phrase:
    # Se il carattere non è uno spazio, lo aggiungo al risultato
    if char != " ":
        result += char

print("Stringa senza spazi:", result)
print("####")

# Esercizio 20 - Sostituisci tutte le vocali di una stringa con '*'
print("Esercizio 20")

phrase = input("Inserisci una frase: ")
result = ""
vowels = "aeiouAEIOU"

# Scorro ogni carattere della stringa
for char in phrase:
    # Se il carattere è una vocale, lo sostituisco con '*'
    if char in vowels:
        result += "*"
    # Altrimenti lo aggiungo invariato
    else:
        result += char

print("Stringa con vocali sostituite:", result)
print("####")

# Esercizio 20 - Sostituisci tutte le vocali di una stringa con '*'
# Usando il metodo built-in replace()
print("Esercizio 20")

phrase = input("Inserisci una frase: ")
vowels = "aeiouAEIOU"

# Scorro ogni vocale e la sostituisco con '*'
for vowel in vowels:
    phrase = phrase.replace(vowel, "*")

print("Stringa con vocali sostituite:", phrase)
print("####")


# Scrivi un programma che analizzi una frase e stampi:**
#     - il numero totale di caratteri (inclusi gli spazi)
#     - il numero di parole
#     - il numero di vocali
#     - il numero di consonanti
#     - la parola più lunga
# Esercizio 21 - Analizza una frase e stampa statistiche
# - Numero totale di caratteri (inclusi spazi)
# - Numero di parole
# - Numero di vocali
# - Numero di consonanti
# - Parola più lunga
print("Esercizio 21")

phrase = input("Inserisci una frase: ")
vowels = "aeiouAEIOU"
total_chars = 0
word_count = 0
vowel_count = 0
consonant_count = 0

# Conto i caratteri totali
total_chars = len(phrase)

# Conto le parole (spazi + 1)
for char in phrase:
    if char == " ":
        word_count += 1
word_count += 1

# Conto vocali e consonanti
for char in phrase:
    if char in vowels:
        vowel_count += 1
    # Conto solo le lettere che non sono vocali (escludo spazi e numeri)
    elif char.isalpha():
        consonant_count += 1

# Trovo la parola più lunga
words = phrase.split()
longest_word = words[0]
for word in words:
    if len(word) > len(longest_word):
        longest_word = word



print("Numero totale di caratteri:", total_chars)
print("Numero di parole:", word_count)
print("Numero di vocali:", vowel_count)
print("Numero di consonanti:", consonant_count)
print("Parola più lunga:", longest_word)
print("####")

