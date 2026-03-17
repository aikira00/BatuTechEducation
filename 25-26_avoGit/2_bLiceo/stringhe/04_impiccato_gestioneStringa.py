# PROBLEMA STRINGHE IMMUTABILI
secret_word = "pannocchia"
user_word = ""


# supponiamo che utente abbia inserito n
# devo visualizzare __nn______
user_char = "n"

# prima strategia
# user_word è una stringa e
# uso replace che restituisce una nuova stringa
for char in secret_word:
    user_word += "_"
print(user_word)
for char in secret_word:
    if char == user_char:
x
print(user_word)

# seconda strategia - la docente vi dice che non potete usare replace
for i in range(len(secret_word)):
    if secret_word[i] == user_char:
        user_word = user_word[:i] + user_char + user_word[i+1:] #ricostruisco la stringa... costoso!
print(user_word)

#BEST: la parola con i caratteri indovinati e non da utente la tratto come lista
# questa è la soluzione migliore per efficienza computazionale e leggibilità
user_word = list()
for char in secret_word:
    user_word.append("_")
for i in range(len(secret_word)):
    if secret_word[i] == user_char:
       user_word[i] = user_char
# per trasformare la lista in stringa uso join
print("".join(user_word))