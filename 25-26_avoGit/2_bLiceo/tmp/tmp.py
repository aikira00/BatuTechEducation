parole = input("inserisci una frase: ")
piu_lunga = ""
for p in parole:
    print(p)
    if len(p) > len(piu_lunga):
        print(len(p), len(piu_lunga))
        piu_lunga = p
print("La più lunga è:", piu_lunga)
# n 2
# parole = input("inserisci una frase: ")
# piu_lunga = ""
# parola_di_ora = ""
# for p in parole:
#     if p != " ":
#         parola_di_ora = parola_di_ora + p
#     else:
#         if len(parola_di_ora) > len(piu_lunga):
#             piu_lunga = parola_di_ora
#         parola_di_ora = ""
# print("La più lunga è:", piu_lunga)
