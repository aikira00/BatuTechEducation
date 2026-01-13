<?php
// Verifica che il form sia stato inviato con metodo POST
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // Recupera e sanitizza i dati del form
    $nome = htmlspecialchars($_POST['nome'] ?? '');
    $cognome = htmlspecialchars($_POST['cognome'] ?? '');
    $email = htmlspecialchars($_POST['email'] ?? '');
    $eta = htmlspecialchars($_POST['eta'] ?? '');
    $corso = htmlspecialchars($_POST['corso'] ?? '');
    $messaggio = htmlspecialchars($_POST['messaggio'] ?? '');

    ?>
    <!DOCTYPE html>
    <html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dati Ricevuti</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                max-width: 600px;
                margin: 50px auto;
                padding: 20px;
                background-color: #f4f4f4;
            }
            .container {
                background-color: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            }
            h1 {
                color: #4CAF50;
            }
            .dato {
                margin: 15px 0;
                padding: 10px;
                background-color: #f9f9f9;
                border-left: 4px solid #4CAF50;
            }
            .etichetta {
                font-weight: bold;
                color: #333;
            }
            .valore {
                color: #666;
                margin-top: 5px;
            }
            a {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #4CAF50;
                color: white;
                text-decoration: none;
                border-radius: 4px;
            }
            a:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
    <div class="container">
        <h1>Dati Ricevuti dal Form</h1>

        <div class="dato">
            <div class="etichetta">Nome:</div>
            <div class="valore"><?php echo $nome; ?></div>
        </div>

        <div class="dato">
            <div class="etichetta">Cognome:</div>
            <div class="valore"><?php echo $cognome; ?></div>
        </div>

        <div class="dato">
            <div class="etichetta">Email:</div>
            <div class="valore"><?php echo $email; ?></div>
        </div>

        <div class="dato">
            <div class="etichetta">Età:</div>
            <div class="valore"><?php echo $eta; ?></div>
        </div>

        <div class="dato">
            <div class="etichetta">Corso:</div>
            <div class="valore"><?php echo $corso; ?></div>
        </div>

        <div class="dato">
            <div class="etichetta">Messaggio:</div>
            <div class="valore"><?php echo nl2br($messaggio); ?></div>
        </div>

        <a href="index.html">Torna al form</a>
    </div>
    </body>
    </html>
    <?php
} else {
    // Se si accede direttamente senza POST, redirect al form
    header("Location: index.html");
    exit();
}
?>