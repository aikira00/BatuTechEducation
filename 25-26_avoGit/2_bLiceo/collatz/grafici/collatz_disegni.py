import matplotlib.pyplot as plt
import numpy as np
from matplotlib.collections import LineCollection

# ── Parametri comuni ──────────────────────────────────────────────
def collatz(n):
    return n // 2 if n % 2 == 0 else (3 * n + 1) // 2

def collatz_standard(n):
    return n // 2 if n % 2 == 0 else 3 * n + 1

# ── Grafico 1: tutorial Shiffman ────────────────────────────
def grafico_rami():
    MAX_NUM = 2000
    ANGLE   = 0.10
    LENGTH  = 10

    plt.figure(figsize=(10, 10), facecolor='black')

    for n in range(1, MAX_NUM + 1):
        seq = [n]
        while seq[-1] != 1:
            seq.append(collatz_standard(seq[-1]))
        seq.reverse()

        x, y = [0], [0]
        direzione = np.pi / 2

        for i in range(len(seq) - 1):
            direzione += ANGLE if seq[i + 1] > seq[i] else -ANGLE
            x.append(x[-1] + np.cos(direzione) * LENGTH)
            y.append(y[-1] + np.sin(direzione) * LENGTH)

        plt.plot(x, y, color='green', linewidth=0.1, alpha=0.3)

    plt.title("Collatz – Rami", color='white')
    # plt.axis('off')
    plt.show()

# ── Grafico 2:  ───────────────────────────
def grafico_albero():
    L              = 30
    W              = 15
    SCALE          = 0.7
    A              = 0.20
    W_COEFF_CHANGE = 0.01
    START_HEADING  = np.pi / 2 # perché matplotlib asse Y non è invertito come in JS
    MAX_S          = 50000

    fig, ax = plt.subplots(figsize=(10, 10))
    fig.patch.set_facecolor('#ade4ca')
    ax.set_facecolor('#ade4ca')
   # ax.set_xlim(0, 2000)
    #ax.set_ylim(0, 2000)
    # 1ax.axis('off')

    for s in range(1, MAX_S + 1):
        sequence, n = [], s
        while n != 1:
            sequence.append(n)
            n = collatz(n)
        sequence.append(1)
        sequence.reverse()

        x, y    = 1000.0, 0.0
        h       = START_HEADING
        w_coeff = 1.0
        segments, linewidths = [], []

        for value in sequence:
            h += A if value % 2 == 0 else -A
            x_new = x + L * SCALE * np.cos(h)
            y_new = y + L * SCALE * np.sin(h)
            segments.append([(x, y), (x_new, y_new)])
            linewidths.append(max(W * SCALE * w_coeff, 0.1))
            w_coeff = max(w_coeff - W_COEFF_CHANGE, 0)
            x, y = x_new, y_new

        lc = LineCollection(segments,
                            linewidths=linewidths,
                            color='#2e8b56',
                            alpha=0x30 / 255)
        ax.add_collection(lc)

    ax.set_title("Collatz – Albero (Shiffman)", color='#2e8b56')
    plt.tight_layout(pad=0)
    plt.show()

# ── Menu ──────────────────────────────────────────────────────────
def menu():
    print("╔══════════════════════════════╗")
    print("║   Visualizzatore di Collatz  ║")
    print("╠══════════════════════════════╣")
    print("║  1 → Rami (verde su nero)    ║")
    print("║  2 → Albero (stile Shiffman) ║")
    print("║  0 → Esci                    ║")
    print("╚══════════════════════════════╝")

    while True:
        scelta = input("Scelta: ").strip()
        if scelta == '1':
            grafico_rami()
        elif scelta == '2':
            grafico_albero()
        elif scelta == '0':
            print("Arrivederci!")
            break
        else:
            print("Scelta non valida. Inserisci 1, 2 o 0.")

menu()