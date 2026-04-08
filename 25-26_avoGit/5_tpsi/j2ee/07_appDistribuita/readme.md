Sì, esattamente. Ma c'è un modo più pulito — su Mac Tomcat è già installato in una cartella, e puoi **copiare solo la cartella `conf` e `webapps`**, non tutto.

---

## Opzione 1 — Copia completa (semplice ma pesante)

```bash
cp -r /Library/Tomcat tomcat-add
cp -r /Library/Tomcat tomcat-sub
# ... x7
```

Ogni copia pesa ~15MB — gestibile.

---

## Opzione 2 — Una sola installazione, più configurazioni (più pulita)

Tomcat supporta la variabile `CATALINA_BASE` separata da `CATALINA_HOME`:

```
CATALINA_HOME = installazione Tomcat (binari, lib) → una sola
CATALINA_BASE = configurazione + webapps → una per istanza
```

```bash
# Crea 7 "istanze leggere"
mkdir -p ~/tomcat-instances/add/{conf,webapps,logs,temp,work}
mkdir -p ~/tomcat-instances/sub/{conf,webapps,logs,temp,work}
# ... x7

# Copia solo server.xml da personalizzare
cp $CATALINA_HOME/conf/server.xml ~/tomcat-instances/add/conf/
cp $CATALINA_HOME/conf/web.xml    ~/tomcat-instances/add/conf/
# ... per ognuna
```

Avvio:
```bash
CATALINA_HOME=/Library/Tomcat \
CATALINA_BASE=~/tomcat-instances/add \
/Library/Tomcat/bin/catalina.sh start
```

---

## Onestamente però...

Per questo esercizio specifico con 7 servizi, 
GlassFish è molto più pratico
— `asadmin create-domain` fa tutto in un comando. 

Sì, esattamente. Ma c'è un modo più pulito — su Mac Tomcat è già installato in una cartella, e puoi **copiare solo la cartella `conf` e `webapps`**, non tutto.

---

## Opzione 1 — Copia completa (semplice ma pesante)

```bash
cp -r /Library/Tomcat tomcat-add
cp -r /Library/Tomcat tomcat-sub
# ... x7
```

Ogni copia pesa ~15MB — gestibile.

---

## Opzione 2 — Una sola installazione, più configurazioni (più pulita)

Tomcat supporta la variabile `CATALINA_BASE` separata da `CATALINA_HOME`:

```
CATALINA_HOME = installazione Tomcat (binari, lib) → una sola
CATALINA_BASE = configurazione + webapps → una per istanza
```

```bash
# Crea 7 "istanze leggere"
mkdir -p ~/tomcat-instances/add/{conf,webapps,logs,temp,work}
mkdir -p ~/tomcat-instances/sub/{conf,webapps,logs,temp,work}
# ... x7

# Copia solo server.xml da personalizzare
cp $CATALINA_HOME/conf/server.xml ~/tomcat-instances/add/conf/
cp $CATALINA_HOME/conf/web.xml    ~/tomcat-instances/add/conf/
# ... per ognuna
```

Avvio:
```bash
CATALINA_HOME=/Library/Tomcat \
CATALINA_BASE=~/tomcat-instances/add \
/Library/Tomcat/bin/catalina.sh start
```

---

## Onestamente però...

Per questo esercizio specifico con 7 servizi, GlassFish è molto più pratico — `asadmin create-domain` fa tutto in un comando. 

## Installa GlassFish su Mac

---

## 1. Scarica GlassFish 7

```bash
# Con Homebrew (il più semplice)
brew install glassfish
```

Oppure manualmente da https://glassfish.org → scarica `glassfish-7.x.x.zip` ed estrai in `/opt/glassfish7`.

---

## 2. Aggiungi al PATH

```bash
# Aggiungi a ~/.zshrc (Mac usa zsh di default)
echo 'export GLASSFISH_HOME=/opt/glassfish7' >> ~/.zshrc
echo 'export PATH=$GLASSFISH_HOME/bin:$PATH' >> ~/.zshrc
source ~/.zshrc
```

Verifica:
```bash
asadmin version
# GlassFish Server Open Source Edition 7.x.x
```

---

## 3. Crea i 7 domain

```bash
cd /opt/glassfish7/bin

asadmin create-domain --portbase 2000 adddomain
asadmin create-domain --portbase 3000 subdomain
asadmin create-domain --portbase 4000 muldomain
asadmin create-domain --portbase 5000 divdomain
asadmin create-domain --portbase 6000 regdomain
asadmin create-domain --portbase 7000 autdomain
asadmin create-domain --portbase 9000 gatdomain
```

Al prompt password premi semplicemente **Invio** (password vuota per sviluppo).

Le porte risultanti:

| Domain | HTTP | Admin | HTTPS |
|---|---|---|---|
| adddomain | 2080 | 2048 | 2081 |
| subdomain | 3080 | 3048 | 3081 |
| muldomain | 4080 | 4048 | 4081 |
| divdomain | 5080 | 5048 | 5081 |
| regdomain | 6080 | 6048 | 6081 |
| autdomain | 7080 | 7048 | 7081 |
| gatdomain | 9080 | 9048 | 9081 |

---

## 4. Avvia/ferma i domain

```bash
# Avvia
asadmin start-domain adddomain
asadmin start-domain subdomain
# ...

# Ferma
asadmin stop-domain adddomain

# Admin console di adddomain
open http://localhost:2048
```

---

## 5. Aggiungi GlassFish in IntelliJ

```
IntelliJ → Settings → Build, Execution, Deployment
  → Application Servers → "+"
  → GlassFish Server
  → GlassFish Home: /opt/glassfish7
  → OK
```

---

## 6. Crea configurazione di Run per ogni domain

```
Run → Edit Configurations → "+" → GlassFish Server → Local

  Name:           ADD Service
  Server:         GlassFish 7
  Domain:         adddomain
  Admin Port:     2048
  HTTP Port:      2080

  Deployment tab:
    "+" → Artifact → add-service:war
    Context: /add
```

Ripeti per ogni domain/servizio.

---

## 7. Copia driver MySQL in ogni domain

```bash
cp mysql-connector-j-9.2.0.jar \
  /opt/glassfish7/glassfish/domains/adddomain/lib/
# ripeti per gli altri domain che usano il DB
```

> 💡 Su Mac con Homebrew GlassFish potrebbe essere in `/usr/local/opt/glassfish` — verifica con `brew info glassfish`.