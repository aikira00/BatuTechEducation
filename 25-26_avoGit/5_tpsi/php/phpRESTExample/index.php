<?php
//header("Content-Type: application/json");
echo "ciao\n";
$uri = trim(parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH), '/'); // Estrai l'URI
echo $uri;
$method = $_SERVER['REQUEST_METHOD']; // Metodo HTTP

// Suddividi l'URI in segmenti
$uriSegments = explode('/', $uri);
// Router semplice
if ($uriSegments[0] === 'api' && $uriSegments[1] === 'utenti') {
    include 'api/utenti.php'; // Reindirizza alla gestione degli utenti
} else {
    http_response_code(404);
    echo json_encode(["errore" => "Risorsa non trovata"]);
}