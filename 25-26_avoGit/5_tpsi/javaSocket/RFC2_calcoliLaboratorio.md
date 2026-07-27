
# Bellucci laboratorio
Scrivere un programma che svolge la funzione di server matematico (non multithread). Il
programma si pone in ascolto sulla porta 60000 dell’indirizzo di loopback.
Il server accetta stringhe nella forma
OP N1 N2
OP può valere ADD SUB MUL DIV e indica la corrispondente operazione matematica da
svolgere
Esempio:
ADD 123 56 SUB 34 87 MUL 34 12 DIV 78 56
Si suppone che la stringa non contenga mai errori
Il server restituirà una stringa composta dalla stringa ricevuta seguita dal risultato
OP N1 N2 R dove R è il risultato dell’operazione
Esempio:
ADD 123 56 179 SUB 34 879 -845 MUL 34 12 408 DIV 78 56 1
Notare che le operazioni si svolgono fra numeri interi ed il risultato della divisione è la sola
parte intera
Dopo aver inviato la stringa si attende per un nuovo invio. La comunicazione termina con
l’invio del comando QUIT
Realizzare poi un client che permetta all’utente di scegliere operazione e operandi da
inviare al server. I due valori numerici e l’operazione da svolgere devono essere letti con
tre diverse richieste all’utente. Nella lettura per scegliere l’operazione si utilizzano i
consueti simboli matematici. Nella stampa del risultato dell’operazione deve essere
mostrato nella forma N1 SIMBOLO N2 = RISULTATO
Esempio:
123 + 65 = 179 34 – 879 = -845 34 * 12 = 408 78 / 56 = 1
Nella realizzazione del server e del client seguire gli UML allegati

# Nesi laboratorio
Realizzare un servizio di calcolatrice (su di un server) attraverso il quale il client dopo essersi connesso ha a disposizione una serie di comandi per modificare la variabile risultato:  
'C = azzera la variabile RISULTATO (condizione di default in partenza)  
'+ NUM' = somma a  RISULTATO  il numero NUM  
'-  NUM' = sottrae a RISULTATO  il numero NUM  
'/ NUM' = divide RISULTATO  per il numero NUM  
'* NUM' = moltiplica RISULTATO  per il numero NUM  
'=' = ottengo il RISULTATO dal server  
solo quando viene chiesto con '=' il numero RISULTATO viene inviato a client per evidenziarne il contenuto.     
  
RISULTATO e NUM non è detto che siano numeri interi.  
  
Realizzare gli input attraverso una interfaccia grafica.   
Il client può ciclare all'infinito oppure si può prevedere un comando EXIT per terminare il lavoro.  
  
Aggiungere anche un documento di descrizione del protocollo che si realizza, secondo il modello allegato.


# Soluzione

simile a RFC1, cambiano comandi client/server 