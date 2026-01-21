
## **WAR (archive)**

- È un **file compresso** `.war` (Web Application Archive)
- Contiene tutto il progetto in un singolo file zip
- Tomcat lo deve **decomprimere** prima di usarlo
- Deployment più lento (ogni modifica richiede rebuild completo)
- Usato per la **produzione** o distribuzione finale

**Esempio**: `form-esempio.war`

## **WAR exploded** (consigliato per sviluppo)

- È una **cartella** con tutti i file già estratti
- Tomcat la usa direttamente senza decomprimere
- **Hot reload** più veloce: modifiche a HTML/JSP sono immediate
- Usato per lo **sviluppo** e debug
- IntelliJ può aggiornare i file senza riavviare Tomcat

**Esempio**: cartella `form-esempio/` con tutto dentro

## Confronto pratico:

|Caratteristica|WAR|WAR exploded|
|---|---|---|
|Velocità sviluppo|❌ Lenta|✅ Veloce|
|Hot reload|❌ No|✅ Sì (parziale)|
|Dimensione|Compressa|Normale|
|Uso tipico|Produzione|Sviluppo|
|Modifiche HTML/JSP|Rebuild totale|Immediato|
|Modifiche Java|Rebuild totale|Rebuild veloce|

## In IntelliJ - Configurazione Run:

**Per sviluppo** (consigliato):

```
Run → Edit Configurations → Deployment tab
└─ Seleziona: [progetto]:war exploded
```

**Per test produzione**:

```
Run → Edit Configurations → Deployment tab
└─ Seleziona: [progetto]:war
```

## Cosa succede dietro le quinte:

### WAR exploded:

```
webapps/
└── form-esempio/
    ├── index.html
    ├── risultato.jsp
    └── WEB-INF/
        ├── classes/
        │   └── SalutoServlet.class
        └── web.xml
```

### WAR:

```
webapps/
└── form-esempio.war (file singolo compresso)
```

## Consiglio per i tuoi studenti:

**Usa sempre WAR exploded durante lo sviluppo** per vedere le modifiche velocemente. Usa il WAR normale solo quando devi distribuire l'applicazione finale.

In IntelliJ, quando clicchi Run con WAR exploded, le modifiche a HTML e JSP sono visibili con un semplice refresh del browser (F5)! 🚀