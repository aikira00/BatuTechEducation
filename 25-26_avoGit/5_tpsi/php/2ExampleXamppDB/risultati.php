<?php
// Includi la configurazione database
require_once 'config.php';

// Crea connessione al database
$conn = getConnection();

// Recupera l'autore selezionato
$autore_selezionato = isset($_GET['autore']) ? $_GET['autore'] : '';

// Query per i libri
$libri = null;
if ($autore_selezionato != '') {
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
    <title>Risultati Ricerca</title>
    <link rel="stylesheet" href="style2.css">
</head>
<body>
<div class="container">
    <a href="index_static.html" class="back-button">← Torna alla ricerca</a>

    <?php if ($autore_selezionato != ''): ?>
        <h1>Libri di <?php echo htmlspecialchars($autore_selezionato); ?></h1>

        <?php if ($libri && $libri->num_rows > 0): ?>
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
                <?php while($libro = $libri->fetch_assoc()): ?>
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
        <?php else: ?>
            <p class="messaggio">⚠️ Nessun libro trovato per questo autore.</p>
        <?php endif; ?>
    <?php else: ?>
        <p class="messaggio">⚠️ Nessun autore selezionato.</p>
    <?php endif; ?>
</div>

<?php
$conn->close();
?>
</body>
</html>
