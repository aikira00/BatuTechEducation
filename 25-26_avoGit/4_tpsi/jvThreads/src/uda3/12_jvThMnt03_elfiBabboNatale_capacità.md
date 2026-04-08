# Festa degli Elfi: Il Laboratorio dei Regali Magici con magazzino

Riprendendo l’esempio degli elfiTwinky eBlinky, modificare l’esercizio nel seguente modo:

- L’elfoTwinky, detto l’Elfo Creatore, deve produrre n regali, uno alla volta, e depositarli in un magazzino condiviso che ha capacità massima m.
    
- L’elfoBlinky, detto l’Elfo Corriere, deve spedire n regali, prelevandoli dal magazzino.
    
- Il magazzino deve gestire correttamente l’accesso concorrente:
    
    - Twinky deve aspettare se il magazzino è pieno.
        
    - Blinky deve aspettare se il magazzino è vuoto.
        
    
- Al termine del programma, il main deve stampare:
    
    - quanti regali haprodotto Twinky;
        
    - quanti regali haspedito Blinky;
        
    - quanti regali sonorimasti nel magazzino.

        
    
Provate il vostro codice aggiungendo unsecondo elfo creatore e unsecondo elfo corriere.