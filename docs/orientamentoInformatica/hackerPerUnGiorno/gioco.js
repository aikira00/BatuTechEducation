// Hacker per un giorno: tre sfide.
// Tutto avviene nel browser: la pagina non invia nessun dato.
(function () {
  "use strict";

  function $(id) { return document.getElementById(id); }

  /* ---------- schede ---------- */

  var schede = document.querySelectorAll("#schede button");

  function mostra(id) {
    ["bin", "ces", "pwd"].forEach(function (k) {
      $("p-" + k).hidden = (k !== id);
    });
    schede.forEach(function (b) {
      b.setAttribute("aria-selected", b.dataset.scheda === id ? "true" : "false");
    });
    window.scrollTo(0, 0);
  }

  schede.forEach(function (b) {
    b.addEventListener("click", function () { mostra(b.dataset.scheda); });
  });

  document.querySelectorAll("[data-vai]").forEach(function (b) {
    b.addEventListener("click", function () { mostra(b.dataset.vai); });
  });

  // sfida completata: si apre il riquadro e la scheda si evidenzia
  function vinci(k) {
    $("v-" + k).hidden = false;
    document.querySelector('[data-scheda="' + k + '"]').classList.add("fatto");
  }

  /* ---------- 1 binario ---------- */

  var PESI = [128, 64, 32, 16, 8, 4, 2, 1];
  var bit = [0, 0, 0, 0, 0, 0, 0, 0];
  var bersaglio = 75;

  function otto(n) {
    var s = n.toString(2);
    while (s.length < 8) s = "0" + s;
    return s;
  }

  function scriviNome() {
    var v = $("nome").value.slice(0, 10);
    var box = $("righeNome");
    box.innerHTML = "";
    for (var i = 0; i < v.length; i++) {
      var c = v.charCodeAt(i);
      var riga = document.createElement("div");
      riga.className = "riga-nome";
      var b = document.createElement("b");
      b.textContent = v[i] === " " ? "␣" : v[i];
      var num = document.createElement("span");
      num.textContent = c;
      var cod = document.createElement("code");
      cod.textContent = c < 256 ? otto(c) : "più di 8 bit";
      riga.appendChild(b);
      riga.appendChild(num);
      riga.appendChild(cod);
      box.appendChild(riga);
    }
  }

  function valore() {
    var t = 0;
    for (var i = 0; i < 8; i++) t += bit[i] * PESI[i];
    return t;
  }

  function disegnaBit() {
    var box = $("bit");
    box.innerHTML = "";
    PESI.forEach(function (w, i) {
      var d = document.createElement("div");
      var b = document.createElement("button");
      b.type = "button";
      b.textContent = bit[i];
      b.setAttribute("aria-pressed", bit[i] ? "true" : "false");
      b.setAttribute("aria-label", "Bit da " + w);
      b.addEventListener("click", function () {
        bit[i] = bit[i] ? 0 : 1;
        disegnaBit();
      });
      var s = document.createElement("span");
      s.textContent = w;
      d.appendChild(b);
      d.appendChild(s);
      box.appendChild(d);
    });
    var v = valore();
    var car = (v >= 33 && v < 127) ? String.fromCharCode(v) : "·";
    $("letturaBit").textContent = otto(v) + " = " + v + " → " + car;
    if (v === bersaglio) vinci("bin");
  }

  function nuovaLettera() {
    var prima = bersaglio;
    while (bersaglio === prima) bersaglio = 65 + Math.floor(Math.random() * 26);
    bit = [0, 0, 0, 0, 0, 0, 0, 0];
    var l = String.fromCharCode(bersaglio);
    $("lettera").textContent = l;
    $("letteraTesto").textContent = l;
    $("numeroLettera").textContent = bersaglio;
    $("numeroLettera2").textContent = bersaglio;
    $("testoAiutino").hidden = true;
    $("v-bin").hidden = true;
    disegnaBit();
  }

  $("nome").addEventListener("input", scriviNome);
  $("nuovaLettera").addEventListener("click", nuovaLettera);
  $("aiutino").addEventListener("click", function () {
    var parti = PESI.filter(function (w) { return bersaglio & w; });
    var h = $("testoAiutino");
    h.textContent = bersaglio + " = " + parti.join(" + ") + ": accendi i bit sotto questi numeri.";
    h.hidden = false;
  });

  /* ---------- 2 cesare ---------- */

  var MESSAGGI = [
    "BENVENUTI NEL LABORATORIO",
    "PROVA A ROMPERE IL CODICE",
    "OGNI APP NASCE DA UN PROGRAMMA",
    "UN COMPUTER ESEGUE SOLO ORDINI PRECISI",
    "CHI CONOSCE LA CHIAVE LEGGE TUTTO"
  ];
  var indice = -1;
  var segreto = 3;

  function sposta(t, s) {
    return t.replace(/[A-Z]/g, function (c) {
      return String.fromCharCode((c.charCodeAt(0) - 65 + s + 26) % 26 + 65);
    });
  }

  function decifra() {
    var s = parseInt($("chiave").value, 10);
    var cifrato = $("cifrato").textContent;
    $("chiaveValore").textContent = s;
    $("chiaro").textContent = sposta(cifrato, -s);
    if (s === 0) {
      $("regola").textContent = "Con la chiave 0 ogni lettera resta com'è.";
    } else {
      var c = cifrato.charAt(0);
      var d = sposta(c, -s);
      $("regola").textContent = "Con la chiave " + s + " ogni lettera torna indietro di " + s +
        (s === 1 ? " posto" : " posti") + ": la " + c + " diventa " + d + "." +
        (d > c ? " Dopo la A si riparte dalla Z." : "");
    }
    if (s === segreto) vinci("ces");
  }

  function cambiaChiave(d) {
    var r = $("chiave");
    r.value = (parseInt(r.value, 10) + d + 26) % 26;
    decifra();
  }

  function nuovoMessaggio() {
    var prima = indice;
    while (indice === prima) indice = Math.floor(Math.random() * MESSAGGI.length);
    segreto = 1 + Math.floor(Math.random() * 25);
    $("cifrato").textContent = sposta(MESSAGGI[indice], segreto);
    $("chiave").value = 0;
    $("v-ces").hidden = true;
    decifra();
  }

  $("chiave").addEventListener("input", decifra);
  $("chiaveMeno").addEventListener("click", function () { cambiaChiave(-1); });
  $("chiavePiu").addEventListener("click", function () { cambiaChiave(1); });
  $("altroMessaggio").addEventListener("click", nuovoMessaggio);

  /* ---------- 3 password ---------- */

  var COMUNI = ["password", "123456", "12345678", "123456789", "qwerty", "ciao", "ciao123",
    "juventus", "milan", "inter", "roma", "napoli", "torino", "password1", "111111", "abc123",
    "amore", "admin", "letmein", "iloveyou", "pippo", "pluto", "password123"];

  var ANNO = 31557600;

  function durata(sec) {
    function n(v, uno, tanti) { v = Math.round(v); return v + " " + (v === 1 ? uno : tanti); }
    if (sec < 1) return "meno di un secondo";
    if (sec < 60) return n(sec, "secondo", "secondi");
    if (sec < 3600) return n(sec / 60, "minuto", "minuti");
    if (sec < 86400) return n(sec / 3600, "ora", "ore");
    if (sec < 86400 * 365) return n(sec / 86400, "giorno", "giorni");
    var a = sec / ANNO;
    if (a < 1000) return n(a, "anno", "anni");
    if (a < 1e6) return "migliaia di anni";
    if (a < 1e9) return "milioni di anni";
    return "miliardi di anni";
  }

  function provaPassword() {
    var p = $("pw").value;
    var m = $("misura");
    var barre = m.querySelectorAll("i");
    m.className = "misura";
    barre.forEach(function (b) { b.className = ""; });
    if (!p) {
      $("tempo").textContent = "Scrivi qualcosa…";
      $("notaPw").textContent = "";
      return;
    }

    var insieme = 0;
    if (/[a-z]/.test(p)) insieme += 26;
    if (/[A-Z]/.test(p)) insieme += 26;
    if (/[0-9]/.test(p)) insieme += 10;
    if (/[^A-Za-z0-9]/.test(p)) insieme += 32;

    var base = p.toLowerCase().replace(/[0-9!?.]+$/, "");
    var comune = COMUNI.indexOf(p.toLowerCase()) > -1 || COMUNI.indexOf(base) > -1;
    // tentativi possibili / 2 (in media) / 10 miliardi al secondo
    var sec = comune ? 0.0001 : Math.pow(insieme, p.length) / 2 / 1e10;

    var livello = sec < 60 ? 1 : (sec < 86400 * 30 ? 2 : (sec < ANNO * 100 ? 3 : 4));
    for (var i = 0; i < livello; i++) barre[i].className = "on";
    if (livello === 4) m.className = "misura forte";

    $("tempo").textContent = durata(sec);
    $("notaPw").textContent = comune
      ? "È tra le password più usate: i computer la provano per prima."
      : p.length < 8
        ? "Troppo corta: ogni carattere in più moltiplica il tempo."
        : insieme === 26
          ? "Solo lettere minuscole: aggiungi numeri o maiuscole, o allungala."
          : "Lunghezza e varietà aiutano, ma la lunghezza conta di più.";

    if (!comune && sec >= ANNO * 100) vinci("pwd");
  }

  $("pw").addEventListener("input", provaPassword);

  /* ---------- avvio ---------- */

  scriviNome();
  nuovaLettera();
  nuovoMessaggio();
})();
