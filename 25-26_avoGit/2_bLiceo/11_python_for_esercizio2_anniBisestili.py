anno_inizio = int(input("Anno iniziale: "))
anno_fine = int(input("Anno finale: "))

# Trova il primo anno bisestile >= anno_inizio
primo_bisestile = anno_inizio + (4 - anno_inizio % 4) % 4

print("Anni bisestili:")
for anno in range(primo_bisestile, anno_fine + 1, 4):
    # Verifica regola completa: divisibile per 4, ma non per 100 (tranne se per 400)
    if (anno % 100 != 0) or (anno % 400 == 0):
        print(anno)
