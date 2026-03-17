lista = ["a", "b", "c", "d", "e"]
print(lista[::])
print(lista[:])
print(lista[1])
print(lista[1:])
print(lista[:1])
print(lista[::2])

numeri = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

print(numeri[3])      # Output: 3
print(numeri[3:])     # Output: [3, 4, 5, 6, 7, 8, 9]
print(numeri[:3])     # Output: [0, 1, 2]
print(numeri[::2])    # Output: [0, 2, 4, 6, 8]

print(numeri[:5:2])  # Output: [1, 3, 5]  (da 1 a 6, passo 2)
print(numeri[2::3])  # Output: [0, 3, 6]  (da 0 a 7, passo 3)

print(numeri[1:7:2])  # Output: [1, 3, 5]  (da 1 a 6, passo 2)
print(numeri[0:8:3])  # Output: [0, 3, 6]  (da 0 a 7, passo 3)

print(numeri[-3:])