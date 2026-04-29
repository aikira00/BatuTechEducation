import os

# Cartella dello script (codice/), indipendente da dove lo lanci
script_dir = os.path.dirname(os.path.abspath(__file__))
# Radice del progetto: un livello sopra
radice_dir = os.path.dirname(script_dir)

file_input      = os.path.join(radice_dir, "dati", "sequenza.txt")
file_compresso  = os.path.join(radice_dir, "dati", "compresso.txt")
file_decomp     = os.path.join(radice_dir, "dati", "decompresso.txt")

compresso = ""
carattere_precedente = ""
contatore = 0

try:
    with open(file_input, "r", encoding="utf-8") as file_in:
        for line in file_in:
            line = line.replace("\n", "")
            for carattere in line:
                if carattere == carattere_precedente:
                    contatore += 1
                else:
                    if carattere_precedente != "":
                        compresso += f"{carattere_precedente}{contatore}"
                    carattere_precedente = carattere
                    contatore = 1
        
        if carattere_precedente == "":
            print("Il file è vuoto.")
        else:
            compresso += f"{carattere_precedente}{contatore}"
            print(compresso)

    with open(file_compresso, "w", encoding="utf-8") as file_out:
        file_out.write(compresso)

    print("Compressione completata! Controlla compresso.txt")

except FileNotFoundError:
    print("Errore: Il file sequenza.txt non è stato trovato.")