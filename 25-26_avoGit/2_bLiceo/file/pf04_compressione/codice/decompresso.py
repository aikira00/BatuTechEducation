import os

# Cartella dello script (codice/), indipendente da dove lo lanci
script_dir = os.path.dirname(os.path.abspath(__file__))
# Radice del progetto: un livello sopra
radice_dir = os.path.dirname(script_dir)


file_input  = os.path.join(radice_dir, "dati", "compresso.txt")
file_decomp     = os.path.join(radice_dir, "dati", "decompresso.txt")

output_list = []
with open(file_input, "r", encoding="utf-8") as file_in:
    output = ""
    for line in file_in:
        print("ELaboro " + line)
        i = 0
        cur_char=""

        while i < len(line):
            # Prima legge il carattere (qualunque esso sia, non cifra)
            cur_char = line[i]
            i += 1

            # Poi raccoglie tutte le cifre che seguono
            times = ""
            while i < len(line) and line[i].isdigit():
                times += line[i]
                i += 1

            if times != "":
                output += cur_char * int(times)
        output += "\n"




with open(file_decomp, "w", encoding="utf-8") as file_out:
    file_out.write(output)