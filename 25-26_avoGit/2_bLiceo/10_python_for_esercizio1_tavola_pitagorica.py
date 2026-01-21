
print("x", end="\t")
for i in range(1, 13):
    print(str(i) + "\t")
print()

# Linea separatrice
print("-" * 60)

# Righe della tavola
for riga in range(1, 13):
    print(riga, end="\t")
    for colonna in range(1, 13):
        print(riga * colonna, end="\t")
    print()