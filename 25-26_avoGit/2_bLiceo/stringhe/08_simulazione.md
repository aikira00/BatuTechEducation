

### **Codice da analizzare:**

```
s = "python"
out = ""

for c in s:
    if c in "aeiou":
        out = c + out
    else:
        out = out + c

print(out)
```

#### **1. Simula il codice passo-passo completando la tabella:**

|**Iterazione**|**c**|**È vocale? (**c in "aeiou"**)**|**out (prima)**|**Operazione eseguita**|**out (dopo)**|
|---|---|---|---|---|---|
|1||||||
|2||||||
|3||||||
|4||||||
|5||||||
|6||||||

---

#### **2. Scrivi il risultato finale:**

print(out) → _______________________

---

#### **3. Spiega a parole cosa fa il programma:**

- Cosa succede alle **vocali**?
    
    > ---
    
- Cosa succede alle **consonanti**?
    
    > ---
    

---

💡 **Suggerimento per gli studenti:**

Osserva bene questa riga:

```
out = c + out
```

cambia completamente l’ordine dei caratteri! (non è una semplice aggiunta)
