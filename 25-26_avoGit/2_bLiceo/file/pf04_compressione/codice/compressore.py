import os

# Cartella dello script (codice/), indipendente da dove lo lanci
script_dir = os.path.dirname(os.path.abspath(__file__))
# Radice del progetto: un livello sopra
radice_dir = os.path.dirname(script_dir)

file_input      = os.path.join(radice_dir, "dati", "sequenza.txt")
file_compresso  = os.path.join(radice_dir, "dati", "compresso.txt")
file_decomp     = os.path.join(radice_dir, "dati", "decompresso.txt")
try:
    with open(file_input, "r", encoding="utf-8") as file_in:
        dati = file_in.read().replace("\n", "")

    if not dati:
        print("Il file è vuoto.")
    else:
        compresso = ""
        carattere_precedente = dati[0]
        contatore = 0

        for carattere in dati:
            if carattere == carattere_precedente:
                contatore += 1
            else:
                compresso += f"{carattere_precedente}{contatore}"
                carattere_precedente = carattere
                contatore = 1

        # Gestione dell'ultimo blocco
        compresso += f"{carattere_precedente}{contatore}"

        with open(file_compresso, "w", encoding="utf-8") as file_out:
            file_out.write(compresso)

        print("Compressione completata! Controlla compresso.txt")

except FileNotFoundError:
    print("Errore: Il file sequenza.txt non è stato trovato.")