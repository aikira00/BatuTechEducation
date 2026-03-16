for numero in range(1, 101):
    n = numero
    passi = 0

    while n != 1:
        if n % 2 == 0:
            n = n // 2
        else:
            n = 3 * n + 1
        passi += 1

    print(f"{numero}: {passi} passi")