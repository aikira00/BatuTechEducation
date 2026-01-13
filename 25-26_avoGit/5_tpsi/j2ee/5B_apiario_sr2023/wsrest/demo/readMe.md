Cartella client deve chiamarsi proprio webapp per convenzione Maven standard

maven clean
maven package

ora copiare .war in target folder nella cartella tomcat webapps
Tomcat decomprime automaticamente il WAR al prossimo avvio:

L'applicazione sarà disponibile su:

http://localhost:8080/demo_war/api/apiari/all
http://localhost:8080/demo_war/index.html
# configurare progetto intelliJ Tomcat

1. Configura Tomcat in IntelliJ
   Aggiungi Tomcat come Application Server:

File → Settings → Build, Execution, Deployment → Application Servers
Clicca "+" e seleziona "Tomcat Server"
Indica la directory di installazione di Tomcat
Clicca OK

2. Configura il progetto per la build
   Verifica il pom.xml abbia:
   <packaging>war</packaging>

<build>
    <finalName>demo_war</finalName> 
<!-- questo è il nome del war generato -->
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>3.3.2</version>
        </plugin>
    </plugins>
</build>

3. Crea configurazione Run/Debug
   Configura Tomcat Deployment:

Run → Edit Configurations
Clicca "+" → Tomcat Server → Local
Nel tab "Server":

Seleziona il Tomcat configurato
Imposta la porta (default 8080)


Nel tab "Deployment":

Clicca "+" → Artifact → seleziona demo_war:war exploded
Imposta Application context: /demo_war

4.. Build e Deploy
Opzione A - Da IntelliJ (consigliata):

Maven panel (lato destro) → Lifecycle → clean
Maven panel → Lifecycle → package
Run → Run 'Tomcat' (o premi Shift+F10)

Opzione B - Build Maven manuale:

mvn clean package

5. Configura il DataSource
   Nel file <TOMCAT_HOME>/conf/context.xml, aggiungi:
   <Resource name="sr2023_eserciziows"
   auth="Container"
   type="javax.sql.DataSource"
   maxTotal="100"
   maxIdle="30"
   maxWaitMillis="10000"
   username="tuo_username"
   password="tua_password"
   driverClassName="com.mysql.cj.jdbc.Driver"
   url="jdbc:mysql://localhost:3306/sr2023_eserciziows"/>
   Avvia Tomcat da IntelliJ e verifica:

http://localhost:8080/demo_war/api/apiari/all
Apri index.html nel browser

Nota: Se usi Tomcat 10+, assicurati di usare Jakarta EE 9+ (come già fai con jakarta.ws.rs).