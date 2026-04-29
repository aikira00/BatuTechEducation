DOMAINS=$GLASSFISH_HOME/glassfish/domains

for DOMAIN in adddomain subdomain muldomain divdomain regdomain autdomain gatdomain; do
  echo "=== Rigenero certificato per $DOMAIN ==="

  # 1. Cancella vecchi
  keytool -delete -alias s1as \
    -keystore $DOMAINS/$DOMAIN/config/keystore.p12 \
    -storepass changeit 2>/dev/null

  keytool -delete -alias s1as \
    -keystore $DOMAINS/$DOMAIN/config/cacerts.p12 \
    -storepass changeit 2>/dev/null

  # 2. Genera nuovo con CN=localhost
  keytool -genkeypair -alias s1as \
    -keyalg RSA -keysize 2048 -validity 365 \
    -dname "CN=localhost, OU=Glassfish, O=IntellJ, L=Torino, ST=TO, C=IT" \
    -keystore $DOMAINS/$DOMAIN/config/keystore.p12 \
    -storetype PKCS12 \
    -storepass changeit -keypass changeit

  # 3. Esporta
  keytool -exportcert -alias s1as \
    -keystore $DOMAINS/$DOMAIN/config/keystore.p12 \
    -storepass changeit \
    -file $DOMAINS/$DOMAIN/config/s1as.cer

  # 4. Importa nel proprio truststore
  keytool -importcert -noprompt -alias s1as \
    -file $DOMAINS/$DOMAIN/config/s1as.cer \
    -keystore $DOMAINS/$DOMAIN/config/cacerts.p12 \
    -storetype PKCS12 \
    -storepass changeit

  echo "=== $DOMAIN OK ==="
done