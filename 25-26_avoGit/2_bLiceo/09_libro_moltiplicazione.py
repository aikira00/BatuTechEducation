moltiplicando = int(input("Inserisci il numero da moltiplicare: "))
moltiplicatore = int(input("Inserisci il numero con cui moltiplicare: "))

mul = 0
for i in range(1, moltiplicatore+1):
    mul += moltiplicando

assert(mul == moltiplicando * moltiplicatore, "IL TUO PROGRAMMA HA UN BUG")
print(mul)
print(moltiplicando * moltiplicatore)
