#
# Scrivi un programma che, data una frase inserita dall'utente,
# trovi e stampi la parola più lunga.
# Suggerimento: scorri la frase carattere per carattere,
#  costruisci le parole lettera per lettera e confrontale man mano.
#  NON usare il metodo split().
#
# verifica il risultato usando split() e confronta le due soluzioni.


current_word = ""
longest_word = ""

for char in phrase:
    # Se il carattere non è uno spazio, lo aggiungo alla parola corrente
    if char != " ":
        current_word += char
    # Se trovo uno spazio, la parola corrente è finita
    else:
        # Confronto la parola corrente con la più lunga trovata finora
        if len(current_word) > len(longest_word):
            longest_word = current_word
        # Resetto la parola corrente
        current_word = ""

# Controllo l'ultima parola (non seguita da spazio)
if len(current_word) > len(longest_word):
    longest_word = current_word


