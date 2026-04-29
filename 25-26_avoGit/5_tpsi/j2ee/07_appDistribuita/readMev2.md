# Setup GlassFish — Applicazione Distribuita con 7 Domini

Per questo esercizio con 7 domini meglio installare GlassFish 4.1.1.

---

## 1. Installazione

```bash
# Con Homebrew (il più semplice)
brew install glassfish
```

Oppure manualmente da https://glassfish.org → scarica `glassfish-7.x.x.zip` ed estrai in `/opt/glassfish7`.

---

## 2. Configurazione PATH

Aggiungi a `~/.zshrc`:

```bash
export GLASSFISH_HOME=/opt/homebrew/opt/glassfish/libexec
export PATH=$GLASSFISH_HOME/bin:$PATH
```

Oppure apri il file a mano:

```bash
nano ~/.zshrc
```

Poi ricarica:

```bash
source ~/.zshrc
```

Verifica che tutto funzioni (darà un messaggio se il server non è avviato):

```bash
asadmin version
```

---

## 3. Creazione dei domini

User: `admin`, password: vuota (va bene per locale).

```bash
asadmin create-domain --portbase 2000 adddomain
asadmin create-domain --portbase 3000 subdomain
asadmin create-domain --portbase 4000 muldomain
asadmin create-domain --portbase 5000 divdomain
asadmin create-domain --portbase 6000 regdomain
asadmin create-domain --portbase 7000 autdomain
asadmin create-domain --portbase 9000 gatdomain
```

Avvia un dominio:

```bash
asadmin start-domain adddomain
```

Controlla quali domini sono attivi:

```bash
asadmin list-domains
```

### Porte per dominio

Ogni dominio ha la sua console di amministrazione. La porta admin è sempre `portbase + 48` — convenzione fissa di GlassFish:

| Offset | Servizio |
|---|---|
| portbase + 48 | Admin console |
| portbase + 80 | HTTP |
| portbase + 81 | HTTPS |
| portbase + 76 | IIOP |
| portbase + 86 | JMX |

---

## 4. Disabilita i listener HTTP

```bash
# adddomain
asadmin --port 2048 set server.network-config.network-listeners.network-listener.http-listener-1.enabled=false

# subdomain
asadmin --port 3048 set server.network-config.network-listeners.network-listener.http-listener-1.enabled=false

# muldomain
asadmin --port 4048 set server.network-config.network-listeners.network-listener.http-listener-1.enabled=false

# divdomain
asadmin --port 5048 set server.network-config.network-listeners.network-listener.http-listener-1.enabled=false

# regdomain
asadmin --port 6048 set server.network-config.network-listeners.network-listener.http-listener-1.enabled=false

# autdomain
asadmin --port 7048 set server.network-config.network-listeners.network-listener.http-listener-1.enabled=false

# gatdomain
asadmin --port 9048 set server.network-config.network-listeners.network-listener.http-listener-1.enabled=false
```

Poi riavvia ogni dominio:

```bash
asadmin restart-domain adddomain
asadmin restart-domain subdomain
asadmin restart-domain muldomain
asadmin restart-domain divdomain
asadmin restart-domain regdomain
asadmin restart-domain autdomain
asadmin restart-domain gatdomain
```

---

## 5. Certificati

Ogni domain ha già un certificato self-signed generato da GlassFish. Bisogna esportarlo e importarlo nel truststore dei domain che lo chiamano.

> ⚠️ Attenzione all'estensione dei file: `.jks` oppure `.p12` a seconda della versione di GlassFish.

Verifica il contenuto della cartella config di un dominio:

```bash
ls $GLASSFISH_HOME/glassfish/domains/adddomain/config/ | grep -E "keystore|cacerts"
```

### Per ogni dominio — procedura completa

**1. Cancella il vecchio certificato dal keystore**

```bash
keytool -delete -alias s1as \
  -keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/keystore.p12 \
  -storepass changeit
```

**2. Cancella il vecchio certificato dal truststore**

```bash
keytool -delete -alias s1as \
  -keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/cacerts.p12 \
  -storepass changeit
```

**3. Genera il nuovo certificato**

Il CN (Common Name) deve essere `localhost` per evitare problemi con NetBeans/IntelliJ.

```bash
keytool -genkeypair -alias s1as \
  -keyalg RSA -keysize 2048 -validity 365 \
  -dname "CN=localhost, OU=Glassfish, O=IntelliJ, L=Torino, ST=TO, C=IT" \
  -keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/keystore.p12 \
  -storetype PKCS12 \
  -storepass changeit -keypass changeit
```

**4. Esporta il certificato**

```bash
keytool -export -alias s1as \
  -keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/keystore.p12 \
  -storepass changeit \
  -file /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/s1as.cer
```

**5. Importa il nuovo certificato nel truststore**

```bash
keytool -importcert -alias s1as \
  -file /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/s1as.cer \
  -keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/cacerts.p12 \
  -storetype PKCS12 \
  -storepass changeit -noprompt
```

**6. Riavvia il dominio**

Tramite la console di amministrazione disabilita l'HTTP.
Collegandosi con il browser verrà segnalato un problema di sicurezza 
perché il certificato è self-signed — bisogna autorizzare esplicitamente 
    la navigazione.

---

## 6. Certificati tra domini

| Dominio   | Cosa importare                                                       | Alias da usare |
|-----------|----------------------------------------------------------------------|---|
| adddomain | certificato di autdomain                                             | `aut` |
| subdomain | certificato di autdomain                                             | `aut` |
| muldomain | certificato di autdomain                                             | `aut` |
| divdomain | certificato di autdomain                                             | `aut` |
| regdomain | certificato di autdomain                                             | `aut` |
| gatdomain | certificato di adddomain, subdomain, divdomain, muldomain, regdomain | `add`, `sub`, `mul`, `div`, `reg`  |

- **`autdomain`** deve essere esportato in tutti i domini con alias `aut` perché tutit i servizi o si autenticano o controllano il token se è valido
- **`subdomain, adddomani etc`** devono esser importati in gatdomain perché il gateway chiama questi servizi
- **regdomain** deve essere importato in gateway perché gateway lo chiaam 

### Esporto autdomain - vedere script sh con tutti
keytool -export -alias s1as \
-keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/autdomain/config/keystore.p12 \
-storepass changeit \
-file /tmp/aut.cer

per controllare 

# per controllare se tutto andato bene
for DOMAIN in adddomain subdomain muldomain divdomain regdomain gatdomain; do
echo "=== Check aut in $DOMAIN ==="
keytool -list \
-keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/$DOMAIN/config/cacerts.p12 \
-storepass changeit


# ora importo in gateway tutti cquelli che lui chiama
for DOMAIN in adddomain subdomain muldomain divdomain regdomain; do
echo "=== Esporto $DOMAIN ==="
keytool -export -alias s1as \
-keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/$DOMAIN/config/keystore.p12 \
-storepass changeit \
-file /tmp/$DOMAIN.cer

echo "=== Importo $DOMAIN in gatdomain ==="
keytool -importcert -noprompt -alias ${DOMAIN%domain} \
-file /tmp/$DOMAIN.cer \
-keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/gatdomain/config/cacerts.p12 \
-storetype PKCS12 \
-storepass changeit
done

# verifichiamo
keytool -list \
-keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/gatdomain/config/cacerts.p12 \
-storepass changeit
# si deve vedere: add, sub, mul, div, reg, aut tra le entry.


# CONFIGURATZIONE AUTH
su glassfish domain panel configurare jndi e connection pool 
vedere questo video https://drive.google.com/file/d/18AvkdqtqahiojuvdMLoH4Cgmhsb5cxI4/view

- non ho bisogno di configurare jndi nel progetto perché con glassfish ho già fatto tutto nel pannello admin
- invece Su Tomcat invece serviva web.xml con <resource-ref> e il lookup JNDI manuale.
- Questa è proprio la differenza che avevamo discusso prima.
configurare progetto etc..
  https://localhost:7081/
