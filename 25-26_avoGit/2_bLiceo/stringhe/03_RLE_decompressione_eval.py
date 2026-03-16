# Scrivi anche un secondo programma che faccia l’operazione inversa (**decompressione**),
# dato input la lista di liste in cui ogni sotto-lista contiene carattere/numero volte che compare,
# ricostruire la sequenza originale
# Esempio:
#    Input:  "aaabbc"
#    Output: [['a', 3], ['b', 2], ['c', 1]]
#
# Suggerimento: scorri la stringa carattere per carattere e confronta
# ogni carattere con quello precedente.

output_word = ""

input_string = input("Inserisci una stringa compressa: ")
list_decomp = eval(input_string)
print(f"Hai inserito: {input_string}, procediamo alla decompressione ")

for el in list_decomp:
    print(el)
    num = el[1] # recupero numero
    for i in range(num):
        output_word += el[0]

print(f"La tua stringa decompressa è: {output_word}")

