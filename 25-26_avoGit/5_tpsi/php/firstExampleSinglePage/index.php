<?php
// Variabili per memorizzare i dati
$mostraDati = false;
$dati = [];

// Verifica se il form è stato inviato
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $mostraDati = true;

    // Recupera e sanitizza i dati
    $dati = [
        'nome' => htmlspecialchars(isset($_POST['nome']) ? $_POST['nome'] : ''),
        'cognome' => htmlspecialchars(isset($_POST['cognome']) ? $_POST['cognome'] : ''),
        'email' => htmlspecialchars(isset($_POST['email']) ? $_POST['email'] : ''),
        'eta' => htmlspecialchars(isset($_POST['eta']) ? $_POST['eta'] : ''),
        'corso' => htmlspecialchars(isset($_POST['corso']) ? $_POST['corso'] : ''),
        'messaggio' => htmlspecialchars(isset($_POST['messaggio']) ? $_POST['messaggio'] : '')
    ];
}
?>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form PHP - File Unico</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
        }
        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }
        input, select, textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
        }
        button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .risultato {
            margin-top: 30px;
            padding: 20px;
            background-color: #e8f5e9;
            border-radius: 8px;
        }
        .dato {
            margin: 10px 0;
            padding: 8px;
            background-color: white;
            border-left: 3px solid #4CAF50;
        }
    </style>
</head>
<body>
<h1>Form di Registrazione</h1>

<?php if (!$mostraDati): ?>
    <!-- Mostra il form -->
    <form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required>

        <label for="cognome">Cognome:</label>
        <input type="text" id="cognome" name="cognome" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="eta">Età:</label>
        <input type="number" id="eta" name="eta" min="1" max="120">

        <label for="corso">Corso:</label>
        <select id="corso" name="corso">
            <option value="informatica">Informatica</option>
            <option value="matematica">Matematica</option>
            <option value="fisica">Fisica</option>
        </select>

        <label for="messaggio">Messaggio:</label>
        <textarea id="messaggio" name="messaggio" rows="4"></textarea>

        <button type="submit">Invia</button>
    </form>
<?php else: ?>
    <!-- Mostra i dati ricevuti -->
    <div class="risultato">
        <h2>Dati Ricevuti:</h2>

        <?php foreach ($dati as $chiave => $valore): ?>
            <div class="dato">
                <strong><?php echo ucfirst($chiave); ?>:</strong>
                <?php echo $valore; ?>
            </div>
        <?php endforeach; ?>

        <button onclick="location.reload()">Nuovo Invio</button>
    </div>
<?php endif; ?>
</body>
</html>