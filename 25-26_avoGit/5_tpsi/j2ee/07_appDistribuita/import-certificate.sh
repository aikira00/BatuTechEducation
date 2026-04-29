# ora importo auth in tutti perché tutti lo chiamano
# o per autenticare o per controllare token
for DOMAIN in adddomain subdomain muldomain divdomain regdomain gatdomain; do
  echo "=== Importo aut in $DOMAIN ==="
  keytool -importcert -noprompt -alias aut \
    -file /tmp/aut.cer \
    -keystore /opt/homebrew/Cellar/glassfish/8.0.0/libexec/glassfish/domains/$DOMAIN/config/cacerts.p12 \
    -storetype PKCS12 \
    -storepass changeit
done

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