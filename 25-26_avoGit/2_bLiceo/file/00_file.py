with open("esempio.txt", "r", encoding="utf-8") as f:
    lines = f.readlines()
    for riga in lines:
        print(riga.strip()) # strip() rimuove lo spazio vuoto e il comando "a

with open("esempio.txt", "w") as f:
    new_line = input("Inserisci una nuova riga: ")
    f.write(new_line)

with open("esempio.txt", "r", encoding="utf-8") as f:
    for riga in f:
        print(riga.strip())