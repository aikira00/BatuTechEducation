
# Tomcat installato
## **1️ Configurare Tomcat in IntelliJ**

1. Apri IntelliJ IDEA.
    
2. Vai su **Preferences → Build, Execution, Deployment → Application Servers**.
    
3. Clicca **+ → Tomcat Server**.
    
4. Seleziona la cartella dove hai Tomcat (/Applications/apache-tomcat-10.1.34).
    
5. IntelliJ riconosce la versione e configura tutto.
    

---

## **2️ Creare un progetto Web (Jakarta EE / Servlet)**

1. **File → New → Project**.
    
2. Scegli **Java Enterprise → Web Application**.
    
3. Seleziona **Java 17/21** come SDK.
    
4. Seleziona **Web Application (Servlet)** come framework.
    
5. Nella sezione **Application server**, scegli il Tomcat configurato.
    
6. IntelliJ crea automaticamente la struttura:
    

```
mioProgetto/
 ├── src/main/java/
 ├── src/main/webapp/
 │    ├── WEB-INF/
 │    └── index.jsp
 └── pom.xml   (se progetto Maven)
```

---

## **3️ Provare l’app senza copiare il** 

# **.war**

  

IntelliJ gestisce automaticamente il **deployment** su Tomcat:

1. Vai su **Run → Edit Configurations**.
    
2. Clicca **+ → Tomcat Server → Local**.
    
3. Scegli **Deployment → Artifact → Exploded**.
    
    - L’**Exploded** è la versione “smontata” della tua webapp, utile perché **ogni modifica viene aggiornata senza ricreare il** **.war**.
        
    
4. Salva la configurazione.
    

  

Ora puoi premere **Run** (o Debug). IntelliJ:

- Compila i file
    
- Copia i classi aggiornati in Tomcat
    
- Avvia il server
    
- Apre il browser su http://localhost:8080/mioProgetto
    

---

## **4️ Struttura consigliata per sviluppo**

- **src/main/java** → classi Java (Servlet, Listener ecc.)
    
- **src/main/webapp** → file JSP, HTML, CSS, JS
    
- **WEB-INF/web.xml** → configurazione webapp
    

  

**Nota:** Non serve mai più copiare il .war. IntelliJ gestisce tutto in **Exploded Deployment**.

---

## **5️ Aggiornamenti “live” dell’app**

- Modifica un **JSP** → basta salvare e fare refresh in browser.
    
- Modifica un **Servlet** → IntelliJ ricompila e aggiorna Tomcat (a volte richiede restart del server).
    

---

## **6️ Debug in IntelliJ**

- Puoi mettere **breakpoint** nei Servlet.
    
- Run → Debug → il server parte in modalità debug.
    
- Il browser si collega e puoi **fermare l’esecuzione** nei punti desiderati.
    


# Tomcat homebrew

## **1️ Dove si trova Tomcat Homebrew**

  

Se hai installato con:

```
brew install tomcat
```

Tomcat normalmente è in:

```
/usr/local/opt/tomcat/libexec/
```

- bin/ → startup/shutdown
    
- webapps/ → dove vengono deployate le applicazioni
    
- conf/ → configurazioni (server.xml)
    
- logs/ → log
    

  

Homebrew di solito usa la **porta 8080**, ma se è occupata può cambiare automaticamente.

guardare file 
/usr/local/opt/tomcat/libexec/conf/server.xml

```
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" />
           
```

lsof -i :8080
lsof -i :8081

controllare log dopo start
/usr/local/opt/tomcat/libexec/logs/catalina.out

all'avvio tomcat scrive INFO: Starting ProtocolHandler ["http-nio-8080"]

## **2️ Configurare IntelliJ con Tomcat Homebrew**

1. Apri IntelliJ → **Preferences → Build, Execution, Deployment → Application Servers**.
    
2. Clicca **+ → Tomcat Server** → scegli la cartella:
    

```
/usr/local/opt/tomcat/libexec
```

3. IntelliJ rileva la versione e configura tutto.
    

## **3️ Creare un progetto Web (Servlet / Jakarta EE)**

1. **File → New → Project → Java Enterprise → Web Application**
    
2. SDK → Java 17 o 21
    
3. Framework → **Web Application (Servlet)**
    
4. Application Server → scegli Tomcat Homebrew configurato
    

  

IntelliJ creerà la struttura:

```
src/main/java/           # classi Java
src/main/webapp/         # HTML, JSP, CSS, JS
WEB-INF/web.xml           # configurazione
```

---

##  **4️ Deployment “Exploded” (senza copiare .war)**

1. **Run → Edit Configurations → + → Tomcat Server → Local**
    
2. Deployment → **Artifact → Exploded**
    
    - “Exploded” = progetto non compresso, IntelliJ aggiorna automaticamente le classi modificate
        
    
3. Run → Tomcat parte → browser apre:
    

```
http://localhost:8080/tuoprogetto
```

Ogni modifica salvata viene aggiornata automaticamente nel server.

---

## **5️  Debug**

- Breakpoint nei Servlet / Listener / JSP
    
- **Debug → Run → Debug**
    
- Il server parte in modalità debug, puoi fermare l’esecuzione nei punti desiderati
    

---

## **6  Vantaggi del Tomcat Homebrew**

- Indipendente dal Tomcat preinstallato in macOS
    
- Aggiornabile facilmente con brew upgrade tomcat
    
- Porta e configurazioni separate
    
- Perfetto per sviluppo e IntelliJ
    

---
