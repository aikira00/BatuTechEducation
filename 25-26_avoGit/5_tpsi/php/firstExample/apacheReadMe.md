# Xampp 
La document root di XAMPP è:
/Applications/XAMPP/xamppfiles/htdocs/

## PHP STORM E APACHE Web Server di Xampp

**1. In PHPStorm → Settings (Preferences):**
```
File → Settings → Languages & Frameworks → PHP
```

**2. Imposta PHP CLI:**
```
PHP executable: /Applications/XAMPP/xamppfiles/bin/php
```

**3. Configura deployment:**
```
Tools → Deployment → Configuration
→ + (Aggiungi nuovo)
→ Type: Local or mounted folder
→ Folder: /Applications/XAMPP/xamppfiles/htdocs/esempio-form
```


# Apache nativo - lasciar perder no php in macos12

mac apache già installato nativo ma php rimosso, bisogna usare homebrew, configurare moduli e porte...
lungo e dispersivo per gli studenti

#PHP was deprecated in macOS 11 and removed from macOS 12

/private/etc/apache2

per verificare php

php -v

sudo apachectl start/stop/restart
sudo nano /etc/apache2/httpd.conf
cd /Library/WebServer/Documents/esempio

## Avvia
sudo apachectl start

## Ferma
sudo apachectl stop

## Riavvia
sudo apachectl restart

## Verifica configurazione
sudo apachectl configtest

## Stato
sudo apachectl status

# homebrew (avanzato, vedere studenti?)

brew services start httpd

Configura PHP in Apache
Edit /opt/homebrew/etc/httpd/httpd.conf