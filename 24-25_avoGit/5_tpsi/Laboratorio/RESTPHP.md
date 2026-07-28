
https://www.html.it/articoli/rest-api-e-php-8/

Esempio

<?php

header("Content-Type: application/json");

// File di archiviazione dati (simuliamo un database JSON)
const DB_FILE = "users.json";

// Carica i dati esistenti
function loadUsers() {
    if (!file_exists(DB_FILE)) return [];
    return json_decode(file_get_contents(DB_FILE), true) ?: [];
}

// Salva i dati nel file JSON
function saveUsers($users) {
    file_put_contents(DB_FILE, json_encode($users, JSON_PRETTY_PRINT));
}

// Ottieni il metodo HTTP
$method = $_SERVER['REQUEST_METHOD'];

// Gestione delle richieste
switch ($method) {
    case 'GET':
        $users = loadUsers();
        echo json_encode($users);
        break;

    case 'POST':
        $data = json_decode(file_get_contents("php://input"), true);
        if (!$data || !isset($data['name']) || !isset($data['email'])) {
            http_response_code(400);
            echo json_encode(["error" => "Invalid input"]);
            exit;
        }
        
        $users = loadUsers();
        $id = count($users) + 1;
        $data['id'] = $id;
        $users[] = $data;
        saveUsers($users);
        echo json_encode($data);
        break;

    case 'PUT':
        $data = json_decode(file_get_contents("php://input"), true);
        if (!$data || !isset($data['id'])) {
            http_response_code(400);
            echo json_encode(["error" => "Invalid input"]);
            exit;
        }
        
        $users = loadUsers();
        foreach ($users as &$user) {
            if ($user['id'] == $data['id']) {
                $user = array_merge($user, $data);
                saveUsers($users);
                echo json_encode($user);
                exit;
            }
        }
        http_response_code(404);
        echo json_encode(["error" => "User not found"]);
        break;

    case 'DELETE':
        $data = json_decode(file_get_contents("php://input"), true);
        if (!$data || !isset($data['id'])) {
            http_response_code(400);
            echo json_encode(["error" => "Invalid input"]);
            exit;
        }
        
        $users = loadUsers();
        $filteredUsers = array_filter($users, fn($user) => $user['id'] != $data['id']);
        if (count($users) == count($filteredUsers)) {
            http_response_code(404);
            echo json_encode(["error" => "User not found"]);
            exit;
        }
        saveUsers(array_values($filteredUsers));
        echo json_encode(["message" => "User deleted"]);
        break;

    default:
        http_response_code(405);
        echo json_encode(["error" => "Method not allowed"]);
        break;
}
