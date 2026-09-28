<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>19 Settembre 2026 - Primo esercizio php</h1>
    <?php
        echo "ma che te lo dico a fare ?!? " . "</br>"
    ?>
    <form action="process.php" method="GET">
        <label for="ftesto">Testo:</label><br>
        <input type="text"  name="text"/><br>
        <label for="fpwd">Pwd:</label><br>
        <input type="password" name="pwd"/><br>
        <label for="fdate">data:</label><br>
        <input type="date" name="data"/><br>
        <label for="ftime">time:</label><br>
        <input type="time" name="time"/><br>
        <input type="submit" value="Click me!" /><br>
    </form>
</body>
</html>