Ecco l'esercizio rivisto e la soluzione completa per il docente:

 Risposte alle Domande

**1. Perché il Figlio B è diventato zombie?**

**Risposta corretta:** Perché il padre non ha fatto `wait`/`waitpid` per lui.

Un processo diventa zombie quando termina ma il padre non ha ancora raccolto il suo exit status con `wait()` o `waitpid()`. L'exit status (42) o il tempo di sleep non c'entrano.

---

**2. Quale macro per verificare terminazione normale?**

```c
if (WIFEXITED(status)) { ... }
```

---

**3. Quale macro per verificare terminazione da segnale?**

```c
if (WIFSIGNALED(status)) { ... }
```

---

**4. Cosa succede al Figlio B zombie quando il padre termina?**

Quando il padre termina, il Figlio B zombie diventa **orfano** e viene **adottato da init** (PID 1) o systemd. Init esegue periodicamente `wait()` su tutti i suoi figli adottivi, raccogliendo il loro exit status e rimuovendoli dalla tabella dei processi. Lo zombie viene quindi "pulito".

---

**5. Come evitare che Figlio B diventi zombie?**

Aggiungere prima del riepilogo finale:
```c
waitpid(pid_b, &status_b, 0);
```

Oppure usare un loop per aspettare tutti:
```c
for (int i = 0; i < 3; i++) {
    pid_t p = wait(&status);
    printf("Aspettato figlio: %d\n", p);
}
```

