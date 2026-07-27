import os


# Cartella dello script (codice/), indipendente da dove lo lanci
script_dir = os.path.dirname(os.path.abspath(__file__))
print("cosa stampa?", script_dir)
# Radice del progetto: un livello sopra
radice_dir = os.path.dirname(script_dir)
print(f"Lo script viene si trova qui: {script_dir}")
radice_dir = os.path.dirname(script_dir)
print(radice_dir)

# 2. Contenuto della cartella radice (mostra 'codice' e 'dati')
print("\nContenuto della cartella principale:")
#print(os.listdir(radice_dir))

for el in os.listdir(radice_dir):
    print(el)
    el_path = os.path.join(radice_dir, el)
    if os.path.isdir(el_path):
        print(f"E' una cartella: {el_path}")
        for subel in os.listdir(el_path):
            print(f"    {subel}")
            subel_path = os.path.join(el_path, subel)
            if os.path.isfile(subel_path):
                with open(subel_path, "r", encoding="utf-8") as f:
                    print(f.read())
    else:
        print(f"E' un file: {el_path}")
# # 3. Percorso assoluto verso 'dati'
# cartella_dati = os.path.join(radice_dir, "dati")
# print(f"\nPercorso della cartella dati: {cartella_dati}")
#
# if os.path.isdir(cartella_dati):
#     print("Cartella 'dati' trovata!")
# else:
#     print("Errore: la cartella 'dati' non esiste. Creala a mano!")
#     exit(1)
#
# # 4. Creazione dei file dentro 'dati'
# file_da_generare = ["test_1.txt", "test_2.txt", "log_sistema.txt"]
#
# for nome in file_da_generare:
#     percorso_file = os.path.join(cartella_dati, nome)
#     with open(percorso_file, "w", encoding="utf-8") as f:
#         f.write(f"File generato automaticamente: {nome}\n")
#         f.write("Pronto per l'algoritmo di compressione.")
#
# print(f"\nOperazione completata. Creati {len(file_da_generare)} file.")