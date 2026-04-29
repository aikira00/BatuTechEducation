@echo off
setlocal enabledelayedexpansion

set PCNAME=MULTI01
set BASE_DOMAINS=c:\Users\%PCNAME%\GlassFish_Server2\glassfish\domains
set ASADMIN=c:\Users\%PCNAME%\GlassFish_Server2\bin\asadmin
set STOREPASS=changeit
set KEYPASS=changeit
set KEYTOOL="C:\Program Files\Java\jdk-21\bin\keytool"

echo ========================================
echo FASE 0: Creazione dei 7 domini
echo ========================================

echo Creazione authdomain con portbase 2000...
echo. | "%ASADMIN%" create-domain --portbase 2000 --user admin --nopassword=true authdomain

echo Creazione adddomain con portbase 3000...
echo. | "%ASADMIN%" create-domain --portbase 3000 --user admin --nopassword=true adddomain

echo Creazione subdomain con portbase 4000...
echo. | "%ASADMIN%" create-domain --portbase 4000 --user admin --nopassword=true subdomain

echo Creazione muldomain con portbase 5000...
echo. | "%ASADMIN%" create-domain --portbase 5000 --user admin --nopassword=true muldomain

echo Creazione divdomain con portbase 6000...
echo. | "%ASADMIN%" create-domain --portbase 6000 --user admin --nopassword=true divdomain

echo Creazione regdomain con portbase 7000...
echo. | "%ASADMIN%" create-domain --portbase 7000 --user admin --nopassword=true regdomain

echo Creazione gatdomain con portbase 9000...
echo. | "%ASADMIN%" create-domain --portbase 9000 --user admin --nopassword=true gatdomain


echo ========================================
echo FASE 1: Pulizia totale e rigenerazione certificati per tutti i 7 domini
echo ========================================

for %%d in (authdomain adddomain subdomain muldomain divdomain regdomain gatdomain) do (
    echo.
    echo [%%d] 1. Cancellazione s1as dal keystore...
    %KEYTOOL% -delete -alias s1as -keystore "%BASE_DOMAINS%\%%d\config\keystore.jks" -storepass %STOREPASS% -noprompt
    
    echo [%%d] 2. Cancellazione s1as dal truststore...
    %KEYTOOL% -delete -alias s1as -keystore "%BASE_DOMAINS%\%%d\config\cacerts.jks" -storepass %STOREPASS% -noprompt
    
    echo [%%d] 3. Generazione nuovo certificato con CN=localhost nel keystore...
    (
        echo localhost
        echo.
        echo.
        echo.
        echo.
        echo.
        echo yes
    ) | %KEYTOOL% -genkeypair -alias s1as -keyalg RSA -keysize 2048 -validity 365 -keystore "%BASE_DOMAINS%\%%d\config\keystore.jks" -storepass %STOREPASS% -keypass %KEYPASS%
    
    echo [%%d] 4. Esportazione certificato in s1as.cer...
    %KEYTOOL% -export -alias s1as -keystore "%BASE_DOMAINS%\%%d\config\keystore.jks" -storepass %STOREPASS% -file "%BASE_DOMAINS%\%%d\config\s1as.cer" -noprompt
    
    echo [%%d] 5. Importazione s1as nel proprio truststore...
    %KEYTOOL% -import -alias s1as -keystore "%BASE_DOMAINS%\%%d\config\cacerts.jks" -storepass %STOREPASS% -file "%BASE_DOMAINS%\%%d\config\s1as.cer" -noprompt
)

echo.
echo ========================================
echo FASE 2: Import del certificato authdomain (alias "aut") nei truststore degli altri 6 domini
echo ========================================

%KEYTOOL% -importcert -alias aut -file "%BASE_DOMAINS%\authdomain\config\s1as.cer" -keystore "%BASE_DOMAINS%\adddomain\config\cacerts.jks" -storepass changeit -noprompt

%KEYTOOL% -importcert -alias aut -file "%BASE_DOMAINS%\authdomain\config\s1as.cer" -keystore "%BASE_DOMAINS%\subdomain\config\cacerts.jks" -storepass changeit -noprompt

%KEYTOOL% -importcert -alias aut -file "%BASE_DOMAINS%\authdomain\config\s1as.cer" -keystore "%BASE_DOMAINS%\muldomain\config\cacerts.jks" -storepass changeit -noprompt

%KEYTOOL% -importcert -alias aut -file "%BASE_DOMAINS%\authdomain\config\s1as.cer" -keystore "%BASE_DOMAINS%\divdomain\config\cacerts.jks" -storepass changeit -noprompt

%KEYTOOL% -importcert -alias aut -file "%BASE_DOMAINS%\authdomain\config\s1as.cer" -keystore "%BASE_DOMAINS%\regdomain\config\cacerts.jks" -storepass changeit -noprompt

%KEYTOOL% -importcert -alias aut -file "%BASE_DOMAINS%\authdomain\config\s1as.cer" -keystore "%BASE_DOMAINS%\gatdomain\config\cacerts.jks" -storepass changeit -noprompt

echo.
echo ========================================
echo FASE 3: Import in gatdomain dei certificati di add, sub, mul, div, reg con alias specifici
echo ========================================

%KEYTOOL% -importcert -alias adddomain -file "%BASE_DOMAINS%\adddomain\config\s1as.cer" -keystore "%BASE_DOMAINS%\gatdomain\config\cacerts.jks" -storepass changeit -noprompt

%KEYTOOL% -importcert -alias subdomain -file "%BASE_DOMAINS%\subdomain\config\s1as.cer" -keystore "%BASE_DOMAINS%\gatdomain\config\cacerts.jks" -storepass changeit -noprompt

%KEYTOOL% -importcert -alias muldomain -file "%BASE_DOMAINS%\muldomain\config\s1as.cer" -keystore "%BASE_DOMAINS%\gatdomain\config\cacerts.jks" -storepass changeit -noprompt

%KEYTOOL% -importcert -alias divdomain -file "%BASE_DOMAINS%\divdomain\config\s1as.cer" -keystore "%BASE_DOMAINS%\gatdomain\config\cacerts.jks" -storepass changeit -noprompt

%KEYTOOL% -importcert -alias regdomain -file "%BASE_DOMAINS%\regdomain\config\s1as.cer" -keystore "%BASE_DOMAINS%\gatdomain\config\cacerts.jks" -storepass changeit -noprompt

echo.
echo ========================================
echo OPERAZIONE COMPLETATA!
echo ========================================