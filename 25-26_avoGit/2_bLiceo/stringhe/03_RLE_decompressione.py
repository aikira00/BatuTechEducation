# Scrivi un programma che comprima una stringa usando il Run-Length Encoding (RLE).
#
# Il Run-Length Encoding è una tecnica di compressione che sostituisce sequenze
# di caratteri ripetuti con il carattere e il numero di ripetizioni consecutive.
#
# Il programma deve:
# 1. Leggere una stringa inserita dall'utente
# 2. Individuare i gruppi di caratteri consecutivi uguali
# 3. Costruire una lista di liste in cui ogni sotto-lista contiene:
#    - il carattere
#    - il numero di volte che compare consecutivamente
#
# Esempio:
#    Input:  "aaabbc"
#    Output: [['a', 3], ['b', 2], ['c', 1]]
#
# Suggerimento: scorri la stringa carattere per carattere e confronta
# ogni carattere con quello precedente.

output_list = []
count = 1 #inizializzo a 1 perché?
input_string = input("Inserisci una stringa con caratteri ripetuti: ")
print("Hai inserito: {input_string}, procediamo alla compressione ")
for i in range(len(input_string)-1): # perché solo fino a len(input_string) - 1 e nn len(input_string)?
    if input_string[i] == input_string[i+1]:
        count += 1
    else:
        list_tmp = [input_string[i], count]
        output_list.append(list_tmp)
        count = 1 # riprendo da uno perché?

#aggiungo ultimo elemento
list_tmp = [input_string[i], count]
output_list.append(list_tmp)
print("La tua stringa compressa è: ", end = " ")
for el in output_list:
    print(el, end = "")
