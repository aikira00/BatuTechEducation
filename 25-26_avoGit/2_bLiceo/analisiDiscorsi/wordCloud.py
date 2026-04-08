# pip install wordcloud matplotlib
from wordcloud import WordCloud # da installare in laboratorio a scuola ogni volta
import matplotlib.pyplot as plt

dati = {
    "python": 42, "java": 35, "algoritmo": 28, "ciclo": 25,
    "funzione": 22, "classe": 19, "variabile": 17, "lista": 15,
    "dizionario": 13, "stringa": 11, "thread": 9, "rete": 7,
    "modulo": 6, "errore": 5, "debug": 4
}

wc = WordCloud(
    width=1200,
    height=700,
    background_color="white",
    colormap="tab20",          # palette colori
    max_font_size=160,
    min_font_size=18,
    prefer_horizontal=0.8,     # 80% parole orizzontali
    random_state=42
).generate_from_frequencies(dati)

plt.figure(figsize=(14, 8))
plt.imshow(wc, interpolation="bilinear")
plt.axis("off")
plt.title("Word Cloud — Frequenza delle parole", fontsize=14, fontweight="bold", pad=12)
plt.tight_layout()
plt.savefig("wordcloud.png", dpi=150, bbox_inches="tight")
plt.show()