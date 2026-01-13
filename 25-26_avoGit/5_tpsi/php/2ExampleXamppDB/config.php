php<?php
// config.php - Versione con socket esplicito

define('DB_HOST', 'localhost');
define('DB_USER', 'root');
define('DB_PASS', '');
define('DB_NAME', 'TPSI_5Anno');
define('DB_SOCKET', '/Applications/XAMPP/xamppfiles/var/mysql/mysql.sock');

function getConnection() {
    try {
        // Connessione con socket esplicito
        $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME, null, DB_SOCKET);

        if ($conn->connect_error) {
            throw new Exception("Connessione fallita: " . $conn->connect_error);
        }

        $conn->set_charset("utf8mb4");
        return $conn;

    } catch (Exception $e) {
        die("<div style='background:#f8d7da; padding:20px;'>
                <h2>Errore Database</h2>
                <p>" . $e->getMessage() . "</p>
            </div>");
    }
}
?>