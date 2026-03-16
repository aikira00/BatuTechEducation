import random
secret_words=["banana", "pantofola", "pistacchio", "cilindro", "panna", "aquilone", "arcobaleno"]

# genero parola segreta
num = random.randint(0, len(secret_words)-1)
secret_word = secret_words[num]
secret_word = "banana"
tempts = 5
user_tempts = []
# creo lista per tenere traccia di cosa indovina utente
# user_word = ["_" for i in range(len(secret_word))]
user_word = list()
for i in range(len(secret_word)):
    user_word.append("_")

print(f"Ciao, sei pronto a giocare? La parola è compsota da {len(secret_word)} lettere.")
print(f"Hai {tempts} tentativi.")
str_user_word = "".join(user_word)
print(f"{str_user_word}")

while tempts > 0 and str_user_word != secret_word:
    user_char = input("Inserisci una lettera: ")
    user_tempts.append(user_char)
    if user_char in secret_word:
        print(f"Bravo! Hai indovinato la lettera {user_char}.")
        for i in range(len(secret_word)):
            if secret_word[i] == user_char:
                user_word[i] = user_char
    else:
        tempts -= 1
        print(f"Lettera non presente nella parola.")
        print(f"Hai {tempts} tentativi rimanenti.")

    str_user_word = "".join(user_word)
    print(f"Progresso: {str_user_word}")
    print(f"Le lettere che hai provato sono: {user_tempts}")

if str_user_word == secret_word:
    print(f"Hai vinto! La parola era {secret_word}. Avevi ancora {tempts} tentativi.")
else:
    print(f"Hai perso! La parola era {secret_word}")