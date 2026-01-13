<?php
// Includi la configurazione database
require_once 'config.php';

// Crea connessione al database
$conn = getConnection();

// Variabili iniziali
$autore_selezionato = '';
$libri = null;

// Se il form è stato inviato
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['autore'])) {
    $autore_selezionato = $_POST['autore'];

    // Query parametrizzata per sicurezza (prevenzione SQL injection)
    $stmt = $conn->prepare("SELECT * FROM libri WHERE autore = ? ORDER BY anno_pubblicazione");
    $stmt->bind_param("s", $autore_selezionato);
    $stmt->execute();
    $libri = $stmt->get_result();
}
?>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biblioteca - Ricerca per Autore</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    <h1>📚 Biblioteca - Ricerca per Autore</h1>

    <div class="form-container">
        <form method="POST" action="">
            <label for="autore">Seleziona un autore:</label>
            <select id="autore" name="autore" required>
                <option value="">-- Scegli un autore --</option>
                <?php
                // Ottiene la lista degli autori (senza duplicati)
                $query_autori = "SELECT DISTINCT autore FROM libri ORDER BY autore";
                $result_autori = $conn->query($query_autori);

                while ($row = $result_autori->fetch_assoc()) {
                    $selected = ($autore_selezionato == $row['autore']) ? 'selected' : '';
                    echo "<option value='" . htmlspecialchars($row['autore']) . "' $selected>" .
                            htmlspecialchars($row['autore']) . "</option>";
                }
                ?>
            </select>
            <button type="submit">🔍 Cerca Libri</button>
        </form>
    </div>

    <?php if ($libri !== null): ?>
        <?php if ($libri->num_rows > 0): ?>
            <div class="risultati">
                <h2>Libri di <?php echo htmlspecialchars($autore_selezionato); ?></h2>
                <p class="info">Trovati <?php echo $libri->num_rows; ?> libri</p>

                <table>
                    <thead>
                    <tr>
                        <th>Titolo</th>
                        <th>Anno</th>
                        <th>Genere</th>
                        <th>Prezzo</th>
                        <th>Pagine</th>
                        <th>Disponibilità</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php while ($libro = $libri->fetch_assoc()): ?>
                        <tr>
                            <td><strong><?php echo htmlspecialchars($libro['titolo']); ?></strong></td>
                            <td><?php echo $libro['anno_pubblicazione']; ?></td>
                            <td><?php echo htmlspecialchars($libro['genere']); ?></td>
                            <td>€ <?php echo number_format($libro['prezzo'], 2, ',', '.'); ?></td>
                            <td><?php echo $libro['numero_pagine']; ?></td>
                            <td>
                                <?php if ($libro['disponibile']): ?>
                                    <span class="badge disponibile">✓ Disponibile</span>
                                <?php else: ?>
                                    <span class="badge non-disponibile">✗ Non disponibile</span>
                                <?php endif; ?>
                            </td>
                        </tr>
                    <?php endwhile; ?>
                    </tbody>
                </table>
            </div>
        <?php else: ?>
            <p class="messaggio">⚠️ Nessun libro trovato per l'autore selezionato.</p>
        <?php endif; ?>
    <?php endif; ?>
</div>

<?php
// Chiudi connessione
$conn->close();
?>
</body>
</html>