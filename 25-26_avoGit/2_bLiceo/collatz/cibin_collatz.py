import matplotlib.pyplot as plt
import numpy as np

MAX_NUM = 2000
ANGLE = 0.10
LENGTH = 10

plt.figure(figsize=(10, 10), facecolor='black')

for n in range(1, MAX_NUM + 1):
    # 1. Genera la sequenza di Collatz
    seq = [n]
    while seq[-1] != 1:
        ultimo = seq[-1]
        seq.append(ultimo // 2 if ultimo % 2 == 0 else 3 * ultimo + 1)
    seq.reverse()

    x, y = [0], [0]
    direzione = np.pi / 2

    for i in range(len(seq) - 1):
        # Se cresce gira a destra, se cala gira a sinistra
        direzione += ANGLE if seq[i + 1] > seq[i] else -ANGLE
        x.append(x[-1] + np.cos(direzione) * LENGTH)
        y.append(y[-1] + np.sin(direzione) * LENGTH)

    plt.plot(x, y, color='green', linewidth=0.1, alpha=0.3)

plt.tight_layout(pad=1.5)  # era pad=0
plt.show()
print(x.get_xlim(), x.get_ylim())
print("Finito")
print(x)
print(y)

