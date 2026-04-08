import matplotlib.pyplot as plt
import matplotlib.ticker as ticker
import csv
import json
import sys
import os

# ── CONFIGURAZIONE ──────────────────────────────────────────
FILE_INPUT  = "parole.csv"   # cambia con il tuo file
TOP_N       = 20             # None = tutte le parole
SOGLIA_MIN  = 1              # occorrenze minime da mostrare
COLORE      = "steelblue"
OUTPUT_PNG  = "istogramma.png"
# ────────────────────────────────────────────────────────────


def leggi_file(path: str) -> dict[str, int]:
    """Legge parola→occorrenza da CSV, TSV, TXT o JSON."""
    _, ext = os.path.splitext(path.lower())
    dati: dict[str, int] = {}

    if ext == ".json":
        with open(path, encoding="utf-8") as f:
            dati = json.load(f)  # es. {"ciao": 5, "mondo": 3}

    elif ext in (".csv", ".tsv"):
        sep = "\t" if ext == ".tsv" else ","
        with open(path, encoding="utf-8", newline="") as f:
            reader = csv.reader(f, delimiter=sep)
            for riga in reader:
                if len(riga) >= 2:
                    parola, count = riga[0].strip(), riga[1].strip()
                    try:
                        dati[parola] = int(count)
                    except ValueError:
                        pass  # salta l'intestazione o righe malformate

    else:  # .txt — formato: "parola 42" oppure "parola,42"
        with open(path, encoding="utf-8") as f:
            for linea in f:
                linea = linea.strip()
                if not linea:
                    continue
                # supporta separatore spazio o virgola
                parti = linea.replace(",", " ").split()
                if len(parti) >= 2:
                    try:
                        dati[parti[0]] = int(parti[-1])
                    except ValueError:
                        pass

    return dati


def filtra_e_ordina(dati: dict, top_n: int | None, soglia: int) -> tuple[list, list]:
    """Applica soglia minima e limita a top_n, ordina per frequenza."""
    filtrati = {p: c for p, c in dati.items() if c >= soglia}
    ordinati = sorted(filtrati.items(), key=lambda x: x[1], reverse=True)
    if top_n:
        ordinati = ordinati[:top_n]
    parole = [p for p, _ in ordinati]
    counts = [c for _, c in ordinati]
    return parole, counts


def plot_istogramma(parole: list, counts: list, output: str) -> None:
    fig, ax = plt.subplots(figsize=(max(8, len(parole) * 0.5), 6))

    bars = ax.bar(parole, counts, color=COLORE, edgecolor="white", linewidth=0.6)

    # etichette valore sopra ogni barra
    for bar, val in zip(bars, counts):
        ax.text(
            bar.get_x() + bar.get_width() / 2,
            bar.get_height() + max(counts) * 0.01,
            str(val),
            ha="center", va="bottom", fontsize=8
        )

    ax.set_title("Frequenza delle parole", fontsize=14, fontweight="bold", pad=14)
    ax.set_xlabel("Parola", fontsize=11)
    ax.set_ylabel("Occorrenze", fontsize=11)
    ax.yaxis.set_major_locator(ticker.MaxNLocator(integer=True))
    ax.set_xlim(-0.5, len(parole) - 0.5)
    plt.xticks(rotation=45, ha="right", fontsize=9)
    plt.tight_layout()

    plt.savefig(output, dpi=150)
    print(f"Grafico salvato in: {output}")
    plt.show()


# ── MAIN ────────────────────────────────────────────────────
if __name__ == "__main__":
    path = sys.argv[1] if len(sys.argv) > 1 else FILE_INPUT
    dati = leggi_file(path)

    if not dati:
        print("Errore: nessun dato letto dal file.")
        sys.exit(1)

    parole, counts = filtra_e_ordina(dati, TOP_N, SOGLIA_MIN)
    print(f"Parole da visualizzare: {len(parole)}")
    plot_istogramma(parole, counts, OUTPUT_PNG)