<?php
//header("Content-Type: application/json");
// Simuliamo un database di utenti

$users = [
    ["id" => 1, "name" => "Mario Rossi", "email" => "mario@example.com"],
    ["id" => 2, "name" => "Luigi Verdi", "email" => "luigi@example.com"],
    ["id" => 3, "name" => "Anna Bianchi", "email" => "anna@example.com"]
];
// Ottieni il metodo della richiesta
$requestMethod = $_SERVER["REQUEST_METHOD"];
if ($requestMethod !== "GET") {
    sendResponse(405, ["error" => "Metodo non permesso"]);
    exit();
}

// ottento phpREST/users.php
$requestUri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
// Rimuovi il nome dello script dall'URI
// ottengo /phpREST
$scriptName = dirname($_SERVER['SCRIPT_NAME']);

if ($scriptName != '/') {
    //ottengo /users.php
    $requestUri = str_replace($scriptName, '', $requestUri);
}
echo $requestUri; // ottengo /users.php
echo "\n";
$requestUri = trim($requestUri, '/');
$uriSegments = explode('/', $requestUri);
//urisegments[0] /
echo $uriSegments[0]; // ottengo users.php
echo $uriSegments[2];
echo "\n";
// Verifica se stiamo lavorando con /users
if (empty($uriSegments[0]) || $uriSegments[0] !== "users.php") {
    http_response_code(404);
    echo json_encode(["error" => "Risorsa non trovata"], JSON_PRETTY_PRINT);
}

// Ottieni il metodo HTTP
$method = $_SERVER['REQUEST_METHOD'];
// Gestione delle richieste
switch ($method) {
    case 'GET':
        echo "gestisco GET\n";
        break;
    case 'POST':
        echo "gestisco POST\n";
        break;
    default:
        echo "gestisco metodo http non supportato\n";
        break;
}

//GET users.php tutti gli utenti
if (count($uriSegments) === 1) {
    // GET /users?name=
    //echo "GET?";
    //echo $_GET['name'];
    if (isset($_GET['name'])) {
        //simulo select con where
        $data = $users[0];
    }
    else{// GET /users
        $data = $users;
    }

    http_response_code(200);
    echo json_encode($data, JSON_PRETTY_PRINT);
}
else{// GET /users/123

    if(count($uriSegments) === 2 && is_numeric($uriSegments[1]) && $uriSegments[1]<count($users)){
        //echo "\n";
        //echo $uriSegments[1];
        $data = $users[$uriSegments[1]-1]; //nnon gestisco lo zero... un po'così...
        http_response_code(200);
        echo json_encode($data, JSON_PRETTY_PRINT);
    }
    else{
        http_response_code(404);
        echo json_encode(["error" => "Risorsa non trovata"], JSON_PRETTY_PRINT);
    }
}
?>