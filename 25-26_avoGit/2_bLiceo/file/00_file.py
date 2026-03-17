with open("esempio.txt", "r", encoding="utf-8") as f:
    lines = f.readlines()
    for riga in lines:
        print(riga.strip()) # strip() rimuove lo spazio vuoto e il comando "a

with open("esempio.txt", "a") as f:
    f.write("Nuova riga aggiunta \n")

with open("esempio.txt", "r", encoding="utf-8") as f:
    lines = f.readlines()
    for riga in lines:
        print(riga.strip())