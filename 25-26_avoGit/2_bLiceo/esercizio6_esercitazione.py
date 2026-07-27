#dichiaro var
risultato=0
n1=int(input("inserisci il primo numero:"))
n2=int(input("inserisci il secondo numero:"))
op=input("inserici operazione:")

#condizzioni
if op=="+":
    risultato=n1+n2
elif op == "-":
    risultato = n1 - n2
elif op == "*":
    risultato = n1 * n2
elif op == "/":
    risultato = n1 / n2
else:
    print("segno non valido")

#output
print(risultato)
