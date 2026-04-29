# Setup

per questo esercizio con 7 domini meglio installare GlassFish 4.1.1


# Con Homebrew (il più semplice)
brew install glassfish
Oppure manualmente da https://glassfish.org → scarica glassfish-7.x.x.zip ed estrai in /opt/glassfish7.

# Aggiungi a ~/.zshrc (Mac usa zsh di default)
export GLASSFISH_HOME=/opt/homebrew/opt/glassfish/libexec
export PATH=$GLASSFISH_HOME/bin:$PATH

oppure a mano
nano ~/.zshrc
e aggiungo a mano sclerando con i comandi 


source ~/.zshrc

asadmin version per controllare se tutto va bene (darà un messaggio se server non è avviato)

Ora creo sette domini con user admin e pwd vuota (va bene per locale! )

asadmin create-domain --portbase 2000 adddomain
asadmin create-domain --portbase 3000 subdomain
asadmin create-domain --portbase 4000 muldomain
asadmin create-domain --portbase 5000 divdomain
asadmin create-domain --portbase 6000 regdomain
asadmin create-domain --portbase 7000 autdomain
asadmin create-domain --portbase 9000 gatdomain


poi esegui asadmin start-domain adddomain

per vedere quali domini sono attivi e quali non
asadmin list-domains

ogni dominio ha la sua console di amministrazione

La porta admin è sempre portbase + 48. È una convenzione fissa di GlassFish:

GlassFish assegna automaticamente:

portbase + 48 → Admin console
portbase + 80 → HTTP
portbase + 81 → HTTPS
portbase + 76 → IIOP
portbase + 86 → JMX


disabilito i listener http

## adddomain
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

poi per ogni dominio 

asadmin restart-domain adddomain

etc.

# certificati 

Ogni domain ha già un certificato self-signed generato da GlassFish. Devi esportarlo e importarlo nel truststore dei domain che lo chiamano.

Attenzione estensione dei files se .jks o p12

verificare contenuto domini
ls <GLASSFISH_HOME>/adddomain/config/ | grep -E "keystore|cacerts"

Per ogni dominio

1. Canccella vecchio certificato. Cancellare il preesistente alias (identificativo del certificato). Nel caso di glassfish s1as con
password changeit
```
keytool -delete -alias s1as \
-keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/keystore.p12 \
-storepass changeit
```

e anche dal truststore  

```
keytool -delete -alias s1as \
-keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/cacerts.p12 \ 
-storepass changeit
```

2. Generare nuovo certificato
   Verranno richieste una serie di informazioni durante l’esecuzione del comando. In particolare la
   prima richiesta CN (common name) permette di superare il problema di Netbeans impostando
   come nome localhost.
   ```
    keytool -genkeypair -alias s1as \
   -keyalg RSA -keysize 2048 -validity 365 \
   -dname "CN=localhost, OU=Glassfish, O=IntellJ, L=Torino, ST=TO, C=IT" \
   -keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/keystore.p12 \
   -storetype PKCS12 \
   -storepass changeit -keypass changeit
   ```
  
3. Esporta il certificato
   ``` 
   keytool -export -alias s1as \
   -keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/keystore.p12 \
   -storepass changeit -file /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/s1as.cer
   ```

4. Importare il nuovo certificato nel truststore

```
keytool -importcert  -alias s1as -file /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/s1as.cer -keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/adddomain/config/cacerts.p12 -storetype PKCS12 -storepass changeit -noprompt
```


Riavviare Glasfish. Tramite la console di amministrazione disabilitare l’http. Collegandosi con il
browser verrà segnalato un problema di sicurezza perché ci siamo autocertificati. Bisognerà
autorizzare esplicitamente la navigazione


# Certificati tra domini
Authentication lo devo esportare in tutti i domini con alias diverso, e.g, aut
in Add, sub, mul, div, auth e registri devo esportare quello di gateway (sempre con alisas diverso) perché è lui che chiama

