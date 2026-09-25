# Lezione 2 — «Calamite sul frigo»: soluzione

Soluzione dell'esercizio. I due `.java` sono in questa cartella: `javac *.java && java DistributoreTestDrive`. Pagina studenti: `docs/htmlPages/4_info/05_calamite_sul_frigo.html`.
Codice compilato ed eseguito con `javac`/`java` il 21/09/2026: l'output è quello atteso.

## Distributore.java

```java
public class Distributore {

    // variabili d'istanza: cosa sa
    private int lattine = 2;
    private boolean acceso = true;

    // metodi: cosa fa
    public void eroga() {
        if (acceso) {
            System.out.println("clunk! ecco la tua lattina");
            lattine = lattine - 1;
        } else {
            System.out.println("distributore spento: niente lattina");
        }
    }

    public int getLattine() {
        return lattine;
    }

    public boolean isAcceso() {
        return acceso;
    }

    public void setAcceso(boolean a) {
        acceso = a;
    }
}
```

Dentro ciascuno dei due scomparti l'ordine è libero.

## DistributoreTestDrive.java

```java
public class DistributoreTestDrive {
    public static void main(String[] args) {
        Distributore d = new Distributore();
        d.eroga();
        d.setAcceso(false);
        System.out.println("acceso: " + d.isAcceso());
        d.eroga();
        System.out.println("lattine rimaste: " + d.getLattine());
    }
}
```

Nel `main` l'ordine è obbligato. Le due `d.eroga();` sono calamite identiche: si possono scambiare.

## Output

```
clunk! ecco la tua lattina
acceso: false
distributore spento: niente lattina
lattine rimaste: 1
```

## Le due intruse

Errori verificati con `javac`:

| Calamita | Errore di `javac` | Perché |
|---|---|---|
| `d.acceso = false;` | `acceso has private access in Distributore` | La variabile è `private`: da fuori si passa dal setter. È il punto dell'incapsulamento. |
| `Distributore.eroga();` | `non-static method eroga() cannot be referenced from a static context` | Si chiede alla classe di erogare. Serve un oggetto: `d.eroga()`. |

## Errori tipici e cosa producono

- **`setAcceso(false)` dopo la seconda `eroga()`**: esce `clunk!`, `acceso: true`, `clunk!`, `lattine rimaste: 0`.
- **`setAcceso(false)` prima della prima `eroga()`**: esce due volte `distributore spento`, con 2 lattine rimaste.
- **Stampa di `isAcceso()` prima di spegnere**: esce `acceso: true`.
- **Stampa delle scorte prima di erogare**: esce `lattine rimaste: 2`.
- **Una variabile d'istanza nel `main`**: `illegal start of expression`, perché `private` non si può usare su una variabile locale.
- **Un'istruzione messa nella classe**: `<identifier> expected`.

## Domanda 5: setLattine

```java
public void setLattine(int n) {
    if (n >= 0) {
        lattine = n;
    }
}
```
