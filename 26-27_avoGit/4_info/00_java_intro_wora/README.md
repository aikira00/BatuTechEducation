# Demo — "compilo una volta, eseguo ovunque"

Lezione 0 del modulo Java/OOP, 4ª informatica.
Appunti della lezione: `BatuEdu/INFO_4/appunti/java_oop/00_intro_timeline_jvm.md` (su pCloud).

Due programmi **identici nel comportamento**, uno in C e uno in Java. Non interessa cosa
stampano: interessa **cosa esce dal compilatore**.

## Prima della lezione

```bash
javac -version && gcc --version && java -version
```

⚠️ **Controllare la versione della JVM sulle macchine del lab.** Vedi sotto, "Il trabocchetto".

## La sequenza, comando per comando

### 1. Compilo il C e guardo cosa è uscito

```bash
gcc hello.c -o hello && file hello
```

Output reale (Mac Apple Silicon):

```
hello: Mach-O 64-bit executable arm64
```

👉 **Far leggere ad alta voce.** Dentro il file c'è scritto il nome dell'architettura:
`arm64`. Su un PC Windows uscirebbe `PE32+ executable (console) x86-64`, su Linux
`ELF 64-bit LSB executable, x86-64`.

### 2. Compilo il Java e guardo cosa è uscito

```bash
javac Hello.java && file Hello.class
```

```
Hello.class: compiled Java class data, version 61.0
```

👉 **Nessuna architettura nominata.** Non c'è `arm64`, non c'è `x86-64`, non c'è il sistema
operativo. Questo file non è codice macchina di nessun computer esistente.

### 3. Apro il bytecode e lo faccio vedere

```bash
javap -c Hello.class
```

```
  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #7    // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #13   // String Ciao 4A! Sono un programma in Java.
       5: invokevirtual #15   // Method java/io/PrintStream.println:(...)V
       ...
      16: return
```

👉 La frase da dire: **"questo è l'assembly di un computer che non esiste."**
Somiglia all'assembly che hanno visto (istruzioni, operandi, `return`), ma nessun
processore in commercio capisce `invokevirtual`. Lo capisce solo la JVM.

### 4. Faccio parlare i due programmi

Entrambi dicono su che macchina stanno girando. Output reale sul Mac:

```
$ ./hello                              $ java Hello
Ciao 4A! Sono un programma in C.       Ciao 4A! Sono un programma in Java.
  architettura : ARM64                   architettura : aarch64
  sistema      : macOS                   sistema      : Mac OS X
Lo sapevo gia' da quando                 JVM          : 23
mi hanno COMPILATO.                    L'ho chiesto alla JVM ADESSO,
                                       mentre giro.
```

👉 **Sembrano fare la stessa cosa. Non la fanno affatto**, ed è tutto il punto della lezione:

- il C lo sa perché gliel'ha **inciso dentro il preprocessore** in compilazione — le `#if defined(__aarch64__)` hanno scelto una stringa sola, e nell'eseguibile è finita solo quella;
- Java non ha niente di scritto dentro: **lo chiede alla JVM a runtime**, con `System.getProperty("os.arch")`.

⚠️ Una parola sulle `#if` va spesa: il preprocessore l'hanno visto solo come `#include` e `#define`. Basta dire *"queste righe vengono scelte prima ancora di compilare: quelle scartate non esistono proprio nell'eseguibile"*.

### 5. Il momento clou

Copio **solo `Hello.class`** — non il sorgente — su una macchina diversa e lì faccio:

```bash
java Hello
```

Parte, e stampa `x86-64` / `Windows 11`. **Stesso file, risposta diversa.** Mentre `hello` (il binario C) su quella stessa macchina non parte per niente.

⚠️ **Serve un salto vero.** Se il lab è tutto PC Windows x86-64, anche il binario C passa
tranquillamente da una macchina all'altra (Intel e AMD sono la stessa architettura!) e la
demo si sgonfia. Serve: PC → Mac della cattedra, oppure → Linux, oppure → Raspberry Pi.
**Verificare prima cosa c'è davvero in aula.**

## Checklist per il laboratorio

**Sulla chiavetta metto due file soli**: `Hello.class` e il binario C `hello`. **Niente sorgenti** — se porto il `.java` la dimostrazione perde senso, perché potrei ricompilare.

**Il giorno prima**, sul PC del lab:

```bash
java -version
```

Serve **17 o superiore** (è la versione con cui compilo, vedi sotto). Se è più vecchia, ricompilo con quel numero; se è più recente va benissimo.

**In classe**, dalla cartella dove sta il file:

```bash
java Hello
```

| Inciampo | Cosa succede | Perché |
|---|---|---|
| `java Hello.class` | `ClassNotFoundException` | il comando vuole il **nome della classe**, non il nome del file. **È l'errore che farò io davanti a loro** |
| `java hello` | non trova niente | maiuscole e minuscole contano: `Hello` |
| file in un'altra cartella | non lo trova | deve stare nella cartella corrente (o si usa `-cp`) |
| `java Hello.java` | funziona… ma compila al volo | da Java 11 si può, ma **serve il sorgente**: se ci finisco per sbaglio ho dimostrato un'altra cosa |

**E poi il confronto**: nella stessa cartella provo a lanciare anche il binario C `hello`. Su Windows non parte, con un errore brutto e chiaro. Due file uno accanto all'altro, uno parte e l'altro no — è quello il colpo.

## Il trabocchetto: la JVM del laboratorio

`file Hello.class` stampa una *version*: 61.0 = Java 17, 67.0 = Java 23. Quel numero è un
**pavimento**: un `.class` compilato con JDK 23 su una JVM 17 muore con
`UnsupportedClassVersionError`. Cioè: proprio durante la demo della portabilità.

Contromisura — compilare puntando alla versione più vecchia presente in laboratorio:

```bash
javac --release 17 Hello.java
```

Verificato: il `.class` così prodotto è version 61.0 e gira sia su JVM 17 sia su JVM 23.

**Questo non è un intoppo da nascondere, è materiale didattico.** Se capita (o farlo capitare
apposta) è l'occasione per dire che "run anywhere" ha una postilla: *ovunque ci sia una JVM,
purché non più vecchia di quella con cui ho compilato*. La compatibilità è all'indietro, non
in avanti — esattamente come per i file di Word.

## Due architetture su una macchina sola

**A cosa serve**: risponde alla domanda *"ma se l'eseguibile C funziona solo a parità di architettura, allora il compilatore cosa ha risolto?"*. E soprattutto **non richiede due macchine**: si fa sul Mac della cattedra in due minuti. Utile se in laboratorio ho solo PC uguali.

L'opzione `-S` dice al compilatore di fermarsi **prima** di produrre l'eseguibile e di mostrare l'assembly che ha generato.

```bash
clang -arch arm64  -S -O1 mini.c -o arm.s
clang -arch x86_64 -S -O1 mini.c -o x86.s
```

Poi apro i due `.s` affiancati. Output reale, la parte di `main`:

```
=== ARM64 ===                       === x86-64 ===
stp   x29, x30, [sp, #-16]!         pushq  %rbp
mov   x29, sp                       movq   %rsp, %rbp
adrp  x0, l_str@PAGE                leaq   L_str(%rip), %rdi
add   x0, x0, l_str@PAGEOFF         callq  _puts
bl    _puts                         xorl   %eax, %eax
mov   w0, #0                        popq   %rbp
ldp   x29, x30, [sp], #16           retq
ret
```

👉 **Stesso `mini.c`. Non una riga in comune.** Registri diversi (`x0` contro `%rdi` per il primo parametro), chiamata diversa (`bl` contro `callq`), modo diverso di azzerare il ritorno.

**La frase da dire**: *"se scrivessimo in assembly, dovremmo scrivere a mano tutte e due queste colonne. Il compilatore non fa sparire la differenza — la produce lui. Il lavoro non è stato eliminato, è stato tolto a noi."*

E quindi: la dipendenza dall'architettura **non sparisce mai, si sposta più tardi**. In assembly sei legato quando *scrivi*, in C quando *compili*, in Java quando *esegui* — e lì non è più un tuo problema.

### Due domande che arriveranno

**"Due processori x86 hanno lo stesso assembly?"**
La base sì: tutti gli x86-64, Intel o AMD, condividono il set di istruzioni di base. Ma il set **cresce a strati**: SSE (1999), AVX (2011), AVX2 (2013), AVX-512 (2016 su Intel, 2022 su AMD). Un programma che usa AVX-512 su un processore che non ce l'ha muore con `Illegal instruction`.
**Di nuovo la compatibilità all'indietro e non in avanti** — è la terza volta che questo schema compare nella lezione, dopo la versione della JVM e i file di Word. Vale la pena farglielo notare.
Di default `gcc` punta a una base conservativa e il binario gira ovunque; è `-march=native` che rompe.

**"Ma su internet l'assembly x86 è scritto diverso!"**
Esistono **due notazioni** per le stesse istruzioni: Intel (`mov eax, 1`) e AT&T (`movl $1, %eax`, quella con i `%`). Su Unix i compilatori usano AT&T, i manuali Intel usano la sua. Stesse istruzioni, due modi di scriverle.

## Pulizia

I prodotti della compilazione non vanno versionati:

```bash
rm -f hello Hello.class
```

## File

| File | Cosa |
|---|---|
| `hello.c` | versione C |
| `Hello.java` | versione Java, identica nel comportamento |
