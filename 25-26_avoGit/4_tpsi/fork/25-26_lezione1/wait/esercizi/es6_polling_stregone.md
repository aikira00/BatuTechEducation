Soluzioni Consegna Esercizio 6
1. Cosa restituisce waitpid() quando nessun golem è ancora tornato e si usa WNOHANG?
   Restituisce 0. Questo indica che ci sono ancora figli in esecuzione, ma nessuno è terminato in quel momento. Lo stregone può quindi continuare a fare altro (studiare il grimorio) e riprovare più tardi.

2. Cosa succederebbe se lo stregone usasse wait() invece di waitpid() con WNOHANG?
   Lo stregone resterebbe bloccato in attesa del primo golem che termina. Non potrebbe studiare il grimorio perché wait() è una chiamata bloccante: il processo padre si ferma finché un figlio non termina. Con waitpid() e WNOHANG invece il controllo ritorna subito al padre, che può fare altro.

3. Perché il primo parametro di waitpid() deve essere -1 e non il PID di un golem specifico?
   Usando -1 lo stregone attende un golem qualsiasi, cioè il primo che termina. Se usasse il PID di un golem specifico (es. Golem 3), resterebbe in attesa di quel golem anche se altri sono già tornati. Poiché i golem terminano in ordini diversi (in base alla durata della missione), -1 permette di raccoglierli nell'ordine in cui effettivamente terminano.

