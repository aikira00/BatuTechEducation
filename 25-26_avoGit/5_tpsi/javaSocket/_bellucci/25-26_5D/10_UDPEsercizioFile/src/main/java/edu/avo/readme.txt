Scrivere un' applicazione server che utilizza il protocollo UDP per:
riceve una stringa contenente "INFO TRASERIMENTO!nomefile@num";
ricevere una serie di num pacchetti composto ognuno da una stringa nel seguento formato "DATI!n@testo"  con n che va da 1 a num
Il server risponde ad ogni pacchetto reinviandone il numero
Salva la sequenza di stringhe arrivat in num file ognuno denominato nomefile_n (con n che anch'esso va da 1 a num)
Al termine della sequenza di 'arrivi' costruisce il file nomefile assemblando tutti i singoli frammenti e poi cancella i frammenti