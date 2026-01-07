
# Apache web server (no tomcat)

## **1. Usare Apache già incluso in macOS**

sudo apachectl start
http://localhost
sudo apachectl stop
sudo apachectl restart

cartella sito per update: /Library/WebServer/Documents/index.html

dalla home
http://localhost/~cristina/
mkdir ~/Sites

abilitare moduli userdir
sudo a2enmod userdir
sudo apachectl restart

creare file configurazione utente
/etc/apache2/users/tuonome.conf con questo contenuto
<Directory "/Users/tuonome/Sites/">
    Options Indexes MultiViews FollowSymLinks
    AllowOverride All
    Require all granted
</Directory>

**2. Usare Homebrew**
brew install httpd
brew services start httpd

http://localhost:8080

cartella sito 
/usr/local/var/www/


**3. Installare Tomcat**
brew install tomcat
brew services start tomcat
brew services stop tomcat

http://localhost:8080

La cartella webapps è in:
/usr/local/opt/tomcat/libexec/webapps/

mioProgetto/
 ├── index.jsp
 └── WEB-INF/
      ├── web.xml
      └── classes/
  ```
  <web-app>
    <display-name>PrimaWebApp</display-name>
</web-app>
```

va messa in
/usr/local/opt/tomcat/libexec/webapps/mioProgetto

Le cartelle chiave:

- webapps/ → dove metti i tuoi progetti web (cartelle o file .war)
    
- conf/server.xml → configurazione server
    
- logs/ → log di Tomcat
    
- bin/ → script startup/shutdown

http://localhost:8080/mioProgetto

Esempio servlet
```import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Ciao dal Servlet!</h1>");
    }
}
```

aggiorno web.xml
```


<servlet-mapping>
    <servlet-name>HelloServlet</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>`
```

Compila e copia il .class in WEB-INF/classes.

Aprire
http://localhost:8080/mioProgetto/hello


**4.  Tomcat scaricato**


cd /path/to/apache-tomcat/bin
cd /Applications/apache-tomcat-10.1.34/bin
./startup.sh
./shutdown

