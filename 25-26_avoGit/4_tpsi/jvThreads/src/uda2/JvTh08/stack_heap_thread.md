# Stack e Heap con i Thread — Esercizio Corridore

## Situazione in memoria dopo `primoCorridore.start()` e `secondoCorridore.start()`

In questo momento ci sono **3 thread in esecuzione** e quindi **3 stack separati**, mentre l'**heap è unico e condiviso**.

---

## Area Stack — uno per ogni thread

Ogni thread ha il proprio stack **privato** che contiene le variabili locali e i frame dei metodi in esecuzione.

```
┌─────────────────────┐  ┌──────────────────┐  ┌──────────────────┐
│   STACK "main"      │  │ STACK "PRIMO"    │  │ STACK "SECONDO"  │
│   (Thread main)     │  │ (Thread PRIMO)   │  │ (Thread SECONDO) │
├─────────────────────┤  ├──────────────────┤  ├──────────────────┤
│ main()              │  │ run()            │  │ run()            │
│                     │  │                  │  │                  │
│ reader         ─┐   │  │ Nessuna var.    │  │ Nessuna var.    │
│ distTotPrimo=100│   │  │ locale!         │  │ locale!         │
│ distTotSec  = 80│   │  │                  │  │                  │
│ secPrimaInt = 5 │   │  │ Tutto è accesso │  │ Tutto è accesso │
│                 │   │  │ tramite this ───┼──┼─► oggetto heap   │
│ primoCorridore ─┼───┼──┼──► ogg. heap    │  │                  │
│                 │   │  │                  │  │                  │
│ secondoCorridore┼───┼──┼──────────────────┼──┼─► ogg. heap     │
│                 │   │  │                  │  │                  │
│ corsaCompl1     │   │  └──────────────────┘  └──────────────────┘
│ corsaCompl2     │   │
└─────────────────────┘
          │                     │                          │
          │ (riferimenti)       │ (this)                   │ (this)
          ▼                     ▼                          ▼
```

**Osservazione fondamentale**: gli stack di PRIMO e SECONDO sono quasi vuoti! Il metodo `run()` non dichiara nessuna variabile locale. Espressioni come `distanzaPercorsa`, `corri`, `generatore` sono tutte accessi a campi dell'oggetto tramite il riferimento implicito `this`. Quindi **tutti i dati del corridore vivono nell'heap**, non nello stack del thread.

---

## Area Heap — condivisa tra tutti i thread

Gli oggetti creati con `new` vivono nell'heap. **Tutti i thread possono accedere** agli stessi oggetti se ne hanno un riferimento.

```
╔══════════════════════════════════════════════════════════════════════════╗
║                          H E A P                                       ║
║                   (area condivisa tra tutti i thread)                   ║
║                                                                        ║
║  ┌──────────────────────────────┐   ┌──────────────────────────────┐   ║
║  │ Es2CorridoreThreadV2         │   │ Es2CorridoreThreadV2         │   ║
║  │ (oggetto "PRIMO")            │   │ (oggetto "SECONDO")          │   ║
║  │                              │   │                              │   ║
║  │  distanzaTotale  = 100       │   │  distanzaTotale  = 80        │   ║
║  │  distanzaPercorsa = 37       │   │  distanzaPercorsa = 12       │   ║
║  │  corsaCompletata = false     │   │  corsaCompletata = false     │   ║
║  │  corri           = true      │   │  corri           = true      │   ║
║  │  name            = "PRIMO"   │   │  name            = "SECONDO" │   ║
║  │  generatore ──→ [Random]     │   │  generatore ──→ [Random]     │   ║
║  │                              │   │                              │   ║
║  └──────────────────────────────┘   └──────────────────────────────┘   ║
║         ▲              ▲                    ▲              ▲            ║
║         │              │                    │              │            ║
║   primoCorridore    this di             secondoCorridore  this di      ║
║   (stack main)    PRIMO stack           (stack main)    SECONDO stack  ║
║                                                                        ║
╚══════════════════════════════════════════════════════════════════════════╝
```

---

## Chi punta dove — riepilogo dei riferimenti

```
Stack main                              Heap
─────────────                           ─────────────────────────
primoCorridore  ──────────────────────► Oggetto "PRIMO"
secondoCorridore ─────────────────────► Oggetto "SECONDO"

Stack "PRIMO"                           Heap
─────────────                           ─────────────────────────
this            ──────────────────────► Oggetto "PRIMO"  (stesso!)

Stack "SECONDO"                         Heap
─────────────                           ─────────────────────────
this            ──────────────────────► Oggetto "SECONDO" (stesso!)
```

**Due riferimenti allo stesso oggetto**: `primoCorridore` (nello stack del main) e `this` (nello stack di PRIMO) puntano allo **stesso identico oggetto** nell'heap. Non sono copie.

---

## Cosa succede quando `run()` accede ai campi

Nel codice di `run()`:

```java
while (corri && distanzaPercorsa < distanzaTotale) {
    distanzaPercorsa += generatore.nextInt(1, 20);
```

Nessuna di queste è una variabile locale. Il compilatore le traduce tutte come `this.corri`, `this.distanzaPercorsa`, `this.distanzaTotale`, `this.generatore`. Quindi ogni accesso **esce dallo stack del thread e va nell'heap**.

```
  Stack "PRIMO"                        Heap (oggetto "PRIMO")
 ┌──────────────┐                    ┌────────────────────────────┐
 │ run()        │                    │                            │
 │              │   this.corri       │  corri = true ◄────────────┼─── main può
 │  this ───────┼──────────────────► │  distanzaPercorsa = 37     │    modificare
 │              │   this.distPerc    │  distanzaTotale = 100      │    da fuori!
 │              │                    │  generatore → [Random]     │
 └──────────────┘                    └────────────────────────────┘
```

---

## Perché è importante?

1. **La variabile di osservazione `corri` funziona** proprio perché è un campo nell'heap, non una variabile locale: quando il main chiama `primoCorridore.stopCorridore()`, modifica `corri` nell'heap. Il thread PRIMO legge lo **stesso campo** tramite `this` e esce dal ciclo `while`.

2. **Se `corri` fosse stata una variabile locale** di `run()`, il main non avrebbe avuto modo di modificarla — sarebbe stata privata nello stack del thread, invisibile dall'esterno.

3. **Rischio visibilità**: poiché i thread possono avere cache locali dei valori letti dall'heap, in scenari più complessi servirebbe la keyword `volatile` su `corri` per garantire che la modifica fatta dal main sia immediatamente visibile al thread PRIMO.

```
     main chiama stopCorridore()
              │
              ▼
    ┌─────────────────────┐
    │  corri = false       │  ◄── campo nell'heap
    └─────────────────────┘
              ▲
              │
     PRIMO legge this.corri nel while()
     → vede false → esce dal ciclo
```
