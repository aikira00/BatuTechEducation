

# Esempio input: a3b2c1 senza eval
input_string = input("Inserisci la stringa compressa (es. a3b2c1): ")
output_word = ""

# Scorro la stringa a coppie (carattere, numero)
for i in range(0, len(input_string), 2):
    char = input_string[i]       # carattere
    num = int(input_string[i+1]) # numero di ripetizioni
    for j in range(num):
        output_word += char

print(f"La tua stringa decompressa è: {output_word}")

