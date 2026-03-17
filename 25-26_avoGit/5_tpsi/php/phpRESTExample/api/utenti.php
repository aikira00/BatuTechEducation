<?php
//utenti farlocchi dovrebbero essere a db
$users = [
    ["id" => 1, "name" => "Mario Rossi", "email" => "mario@example.com"],
    ["id" => 2, "name" => "Luigi Verdi", "email" => "luigi@example.com"],
    ["id" => 3, "name" => "Anna Bianchi", "email" => "anna@example.com"]
];

echo "sono in utenti.php";

$method = $_SERVER['REQUEST_METHOD'];

//qui ho /api/utenti
$requestUri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
//qui ho api/utenti
$requestUri = trim($requestUri, '/');
$uriSegments = explode('/', $requestUri);
//uriSegments[0] api
//uriSegments[1] utenti
// Verifica se stiamo lavorando con /utenti
if (empty($uriSegments[1]) || $uriSegments[1] !== "utenti") {
    http_response_code(404);
    echo json_encode(["error" => "Risorsa non trovata"], JSON_PRETTY_PRINT);
}

switch ($method) {
    case 'GET':
        //api/utenti tutti gli utenti
        if (count($uriSegments) === 2) {
            // GET /users?name=
            //echo "GET?";
            //echo $_GET['name'];
            if (isset($_GET['name'])) {
                //simulo select con where
                $data = $users[0];
            } else {// GET /users
                $data = $users;
            }
            http_response_code(200);
            echo json_encode($data, JSON_PRETTY_PRINT);
        }
        else{
            if(count($uriSegments) === 3 && is_numeric($uriSegments[2]) && $uriSegments[2]<count($users)){
                //echo "\n";
                //echo $uriSegments[1];
                $data = $users[$uriSegments[2]-1]; //nnon gestisco lo zero... un po'così...
                http_response_code(200);
                echo json_encode($data, JSON_PRETTY_PRINT);
            }
            else{
                http_response_code(404);
                echo json_encode(["error" => "Risorsa non trovata"], JSON_PRETTY_PRINT);
            }
        }
        break;

    case 'POST':
        $input = json_decode(file_get_contents("php://input"), true);
        if (isset($input['nome'])) {
            $nuovoUtente = ["id" => rand(3, 1000), "nome" => $input['nome']];
            echo json_encode(["messaggio" => "Utente creato", "utente" => $nuovoUtente]);
        } else {
            http_response_code(400);
            echo json_encode(["errore" => "Dati mancanti"]);
        }
        break;

    default:
        http_response_code(405);
        echo json_encode(["errore" => "Metodo non supportato"]);
        break;
}