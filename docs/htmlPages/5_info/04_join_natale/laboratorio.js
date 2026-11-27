/* Laboratorio delle JOIN: PERSONA e REGALO modificabili, la JOIN scelta
   eseguita un passo alla volta (prima le coppie del prodotto cartesiano,
   poi le righe senza coppia), con schema, risultato e query aggiornati. */
(function () {
  "use strict";

  var lab = document.getElementById("laboratorio");
  if (!lab) { return; }

  var NS = "http://www.w3.org/2000/svg";
  var W = 460;
  var H = 40;
  var PASSO = 48;
  var TOP = 56;
  var MARGINE = 10;
  var CN = 80;          // cella "nome"
  var CR = 112;         // cella "regalo"
  var CNULL = 52;       // cella NULL
  var CARATTERE = 8.4;  // larghezza di un carattere mono a 14px
  var MAX_RIGHE = 8;

  var INIZIALI = {
    persone: [
      { nome: "Anna", regalo: "calzini" },
      { nome: "Bruno", regalo: "calzini" },
      { nome: "Carla", regalo: "monopattino" }
    ],
    regali: ["calzini", "felpa", "sciarpa"]
  };

  var PROVE = {
    iniziali: function () {},
    dario: function (d) { d.persone.push({ nome: "Dario", regalo: "felpa" }); },
    monopattino: function (d) { d.regali.push("monopattino"); },
    elena: function (d) { d.persone.push({ nome: "Elena", regalo: null }); },
    vuota: function (d) { d.regali = []; }
  };

  var NOMI = { cross: "CROSS", inner: "INNER", left: "LEFT", right: "RIGHT", full: "FULL" };

  function k(s) { return '<span class="k">' + s + "</span>"; }
  function queryJoin(t, fine) {
    return k("SELECT") + " *\n" + k("FROM") + " PERSONA " + k(t + " JOIN") + " REGALO\n  " +
      k("ON") + " PERSONA.regalo = REGALO.regalo" + fine;
  }
  var QUERY = {
    cross: k("SELECT") + " *\n" + k("FROM") + " PERSONA " + k("CROSS JOIN") + " REGALO;",
    inner: queryJoin("INNER", ";"),
    left: queryJoin("LEFT", ";"),
    right: queryJoin("RIGHT", ";"),
    full: queryJoin("LEFT", "") + "\n" + k("UNION") + "\n" + queryJoin("RIGHT", ";")
  };

  var dati, tipo, calc;
  var aPassi = false;
  var passo = 0;
  var timer = null;     // l'animazione in corso
  var mq = window.matchMedia ? window.matchMedia("(prefers-reduced-motion: reduce)") : null;

  /* utilità */

  function $(id) { return document.getElementById(id); }

  // animazioni ridotte nelle impostazioni del computer: si va solo a passi
  function calmo() { return !!(mq && mq.matches); }

  // confronto come la collation utf8mb4_unicode_ci: niente maiuscole, accenti, spazi finali
  function chiave(s) {
    return s.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase().replace(/\s+$/, "");
  }

  function esc(s) {
    return String(s).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");
  }

  function quante(n, sing, plur) { return n + " " + (n === 1 ? sing : plur); }

  function copia(o) { return JSON.parse(JSON.stringify(o)); }

  function sqlValore(v) {
    if (v === null) { return "NULL"; }
    return "'" + v.replace(/\\/g, "\\\\").replace(/'/g, "''") + "'";
  }

  function el(tag, cls, testo) {
    var e = document.createElement(tag);
    if (cls) { e.className = cls; }
    if (testo !== undefined) { e.textContent = testo; }
    return e;
  }

  /* controllo delle chiavi primarie */

  function controllaPK(valori, tab, campo, T) {
    var visti = Object.create(null);
    for (var i = 0; i < valori.length; i++) {
      var v = valori[i];
      if (v === null) {
        return {
          tab: tab, i: i, campo: campo,
          testo: "Alla riga " + (i + 1) + " di <code>" + T + "</code> manca <code>" + campo +
            "</code>: è la chiave primaria e non può essere <code>NULL</code>.<br>" +
            "<code>ERROR 1048 (23000): Column '" + campo + "' cannot be null</code>"
        };
      }
      var c = chiave(v);
      if (c in visti) {
        var a = valori[visti[c]];
        return {
          tab: tab, i: i, campo: campo,
          testo: "«" + esc(v) + "» in <code>" + T + "</code> c'è già: la chiave primaria non si può ripetere." +
            (a !== v ? " Per MySQL «" + esc(a) + "» e «" + esc(v) + "» sono lo stesso valore." : "") +
            "<br><code>ERROR 1062 (23000): Duplicate entry '" + esc(v) + "' for key '" + T + ".PRIMARY'</code>"
        };
      }
      visti[c] = i;
    }
    return null;
  }

  function controlla() {
    var nomi = dati.persone.map(function (p) { return p.nome; });
    return controllaPK(nomi, "p", "nome", "PERSONA") || controllaPK(dati.regali, "r", "regalo", "REGALO");
  }

  /* la JOIN, passo per passo */

  function calcola() {
    var P = dati.persone;
    var R = dati.regali;
    var chiaviR = R.map(chiave);
    var match = P.map(function (p) {
      return p.regalo === null ? -1 : chiaviR.indexOf(chiave(p.regalo));
    });
    var passi = [];
    P.forEach(function (p, i) {
      R.forEach(function (r, j) {
        passi.push({ k: "coppia", p: i, r: j, entra: tipo === "cross" || match[i] === j });
      });
    });
    if (tipo === "left" || tipo === "full") {
      P.forEach(function (p, i) {
        if (match[i] === -1) { passi.push({ k: "soloP", p: i, r: -1, entra: true }); }
      });
    }
    if (tipo === "right" || tipo === "full") {
      R.forEach(function (r, j) {
        if (match.indexOf(j) === -1) { passi.push({ k: "soloR", p: -1, r: j, entra: true }); }
      });
    }
    return { match: match, passi: passi };
  }

  function scriptDati() {
    var righe = ["USE natale;", "DELETE FROM PERSONA;", "DELETE FROM REGALO;"];
    if (dati.regali.length) {
      righe.push("INSERT INTO REGALO (regalo) VALUES " +
        dati.regali.map(function (r) { return "(" + sqlValore(r) + ")"; }).join(", ") + ";");
    }
    if (dati.persone.length) {
      righe.push("INSERT INTO PERSONA (nome, regalo) VALUES\n" +
        dati.persone.map(function (p) {
          return "  (" + sqlValore(p.nome) + ", " + sqlValore(p.regalo) + ")";
        }).join(",\n") + ";");
    }
    return righe.join("\n");
  }

  /* testi */

  function prodotto() {
    var N = dati.persone.length;
    var M = dati.regali.length;
    return quante(N, "persona", "persone") + " × " + quante(M, "regalo", "regali") +
      " = " + quante(N * M, "coppia", "coppie");
  }

  function testoInizio() {
    var avanti = !calc.passi.length ? " Non c'è niente da controllare." : timer ? "" : calmo() ? " Premi «Avanti»." : " Premi «Avvia» o «Avanti».";
    if (tipo === "cross") {
      return "La CROSS JOIN prende tutte le coppie del prodotto cartesiano: " + prodotto() + "." + avanti;
    }
    var fine = {
      inner: "",
      left: " Alla fine si aggiungono, con <code>NULL</code>, le persone rimaste senza coppia.",
      right: " Alla fine si aggiungono, con <code>NULL</code>, i regali rimasti senza coppia.",
      full: " Alla fine si aggiungono, con <code>NULL</code>, le persone e i regali rimasti senza coppia."
    };
    return "Si parte dal prodotto cartesiano: " + prodotto() +
      " da controllare con la condizione <code>PERSONA.regalo = REGALO.regalo</code>." + fine[tipo] + avanti;
  }

  function testoPasso(s) {
    var nome = NOMI[tipo];
    if (s.k === "soloP") {
      return "<strong>" + esc(dati.persone[s.p].nome) + "</strong> non ha trovato nessuna coppia: con la " +
        nome + " JOIN entra lo stesso, con <code>NULL</code> nella colonna di <code>REGALO</code>.";
    }
    if (s.k === "soloR") {
      return "Nessuno vuole <strong>" + esc(dati.regali[s.r]) + "</strong>: con la " + nome +
        " JOIN entra lo stesso, con <code>NULL</code> nelle colonne di <code>PERSONA</code>.";
    }
    var p = dati.persone[s.p];
    var r = dati.regali[s.r];
    var t = "<strong>" + esc(p.nome) + "</strong> con <strong>" + esc(r) + "</strong>: ";
    if (tipo === "cross") {
      return t + "la CROSS JOIN non ha condizione, la coppia entra nel risultato.";
    }
    if (p.regalo === null) {
      return t + "<code>NULL = '" + esc(r) + "'</code> non è vera: un confronto con <code>NULL</code> non dà mai vero. La coppia è scartata.";
    }
    var confronto = "<code>'" + esc(p.regalo) + "' = '" + esc(r) + "'</code>";
    if (s.entra) {
      return t + confronto + " è vera" +
        (p.regalo !== r ? " (maiuscole, accenti e spazi finali non contano)" : "") +
        ", la riga entra nel risultato.";
    }
    return t + confronto + " è falsa, la coppia è scartata.";
  }

  function testoConto(n) {
    var N = dati.persone.length;
    var M = dati.regali.length;
    var righe = quante(n, "riga", "righe");
    if (tipo === "cross") {
      return quante(N, "persona", "persone") + " × " + quante(M, "regalo", "regali") + " = " + righe;
    }
    var c = 0, sp = 0, sr = 0;
    calc.passi.forEach(function (s) {
      if (s.k === "coppia" && s.entra) { c++; }
      if (s.k === "soloP") { sp++; }
      if (s.k === "soloR") { sr++; }
    });
    if (tipo === "inner") {
      return "condizione vera in " + quante(c, "coppia", "coppie") + " su " + (N * M) + ": " + righe;
    }
    var parti = [quante(c, "coppia", "coppie")];
    if (tipo === "left" || tipo === "full") {
      parti.push(quante(sp, "persona senza coppia", "persone senza coppia"));
    }
    if (tipo === "right" || tipo === "full") {
      parti.push(quante(sr, "regalo senza coppia", "regali senza coppia"));
    }
    return parti.join(" + ") + " = " + righe;
  }

  /* schema SVG */

  function crea(tag, attr, testo) {
    var e = document.createElementNS(NS, tag);
    Object.keys(attr).forEach(function (a) { e.setAttribute(a, attr[a]); });
    if (testo !== undefined) { e.textContent = testo; }
    return e;
  }

  function yRiga(posto) { return TOP + posto * PASSO; }

  function corto(t, w) {
    var max = Math.floor((w - 8) / CARATTERE);
    return t.length > max ? t.slice(0, max - 1) + "…" : t;
  }

  function fila(padre, x, y, celle, attr, h) {
    h = h || H;
    var g = crea("g", attr || {});
    celle.forEach(function (c) {
      var extra = c.cls ? " " + c.cls : "";
      var extraT = c.tcls ? " " + c.tcls : extra;
      g.appendChild(crea("rect", { x: x, y: y, width: c.w, height: h, "class": "cella" + extra }));
      g.appendChild(crea("text", {
        x: x + c.w / 2, y: y + h / 2, dy: "0.35em",
        "text-anchor": "middle", "class": "valore" + extraT
      }, corto(c.t, c.w)));
      x += c.w;
    });
    padre.appendChild(g);
  }

  function nomeTabella(svg, x, testo, ancora) {
    svg.appendChild(crea("text", { x: x, y: 16, "text-anchor": ancora, "class": "nome-tab" }, testo));
  }

  // dove sta ogni riga: per la CROSS due elenchi affiancati; per le altre ogni regalo
  // accanto alle persone che lo vogliono, poi chi è senza coppia, così i NULL non si sovrappongono
  function posti() {
    var P = dati.persone;
    var R = dati.regali;
    var postoP = [];
    var postoR = [];
    var righe;
    if (tipo === "cross") {
      var scarto = (P.length - R.length) / 2;
      P.forEach(function (p, i) { postoP[i] = i + Math.max(0, -scarto); });
      R.forEach(function (r, j) { postoR[j] = j + Math.max(0, scarto); });
      righe = Math.max(P.length, R.length);
    } else {
      var n = 0;
      R.forEach(function (r, j) {
        var mie = [];
        P.forEach(function (p, i) { if (calc.match[i] === j) { mie.push(i); } });
        if (!mie.length) { return; }
        mie.forEach(function (i) { postoP[i] = n++; });
        postoR[j] = (postoP[mie[0]] + postoP[mie[mie.length - 1]]) / 2;
      });
      P.forEach(function (p, i) { if (calc.match[i] === -1) { postoP[i] = n++; } });
      R.forEach(function (r, j) { if (postoR[j] === undefined) { postoR[j] = n++; } });
      righe = n;
    }
    return { p: postoP, r: postoR, righe: righe };
  }

  function disegnaSchema(fatti, corrente, finito, chiude) {
    var fig = $("lab-schema");
    var vecchio = fig.querySelector("svg");
    if (vecchio) { fig.removeChild(vecchio); }

    var P = dati.persone;
    var R = dati.regali;
    var pos = posti();
    var xR = W - MARGINE - CR;
    var xL = MARGINE + CN + CR;
    var svg = crea("svg", {
      "aria-hidden": "true", focusable: "false",
      viewBox: "0 0 " + W + " " + (yRiga(Math.max(pos.righe, 1) - 1) + H + MARGINE)
    });
    if (chiude) { svg.setAttribute("class", "chiude"); }

    // nelle JOIN con condizione il passo confronta due celle, non due righe intere
    var confr = !!corrente && corrente.k === "coppia" && tipo !== "cross";

    nomeTabella(svg, MARGINE, "PERSONA", "start");
    nomeTabella(svg, W - MARGINE, "REGALO", "end");
    fila(svg, MARGINE, 24, [{ w: CN, t: "nome" }, { w: CR, t: "regalo" }], { "class": "intest" }, 26);
    fila(svg, xR, 24, [{ w: CR, t: "regalo" }], { "class": "intest" }, 26);

    // che cosa è già successo a ogni riga
    var entrataP = [], entrataR = [], nullP = [], nullR = [];
    fatti.forEach(function (s) {
      if (!s.entra) { return; }
      if (s.p >= 0) { entrataP[s.p] = true; }
      if (s.r >= 0) { entrataR[s.r] = true; }
      if (s.k === "soloP") { nullP[s.p] = true; }
      if (s.k === "soloR") { nullR[s.r] = true; }
    });

    var legami = crea("g", tipo === "cross" ? { "class": "fitto" } : {});
    function linea(s, cls) {
      var a = {
        x1: xL, y1: yRiga(pos.p[s.p]) + H / 2, x2: xR, y2: yRiga(pos.r[s.r]) + H / 2,
        "class": cls, "data-coppia": s.p + "-" + s.r
      };
      // lunghezza della linea, per disegnarla da un capo all'altro
      if (s === corrente && s.entra) {
        a.style = "--lungo:" + Math.ceil(Math.sqrt(Math.pow(a.x2 - a.x1, 2) + Math.pow(a.y2 - a.y1, 2)));
      }
      legami.appendChild(crea("line", a));
      return a;
    }
    fatti.forEach(function (s) {
      if (s.k === "coppia" && s.entra && s !== corrente) { linea(s, "legame"); }
    });
    var segno = null;
    if (corrente && corrente.k === "coppia") {
      var l = linea(corrente, "legame corrente" + (corrente.entra ? " traccia" : " falsa"));
      if (confr) {
        // a metà linea, l'esito del confronto: uguali, diversi o NULL
        var sx = (l.x1 + l.x2) / 2;
        var sy = (l.y1 + l.y2) / 2;
        var t = P[corrente.p].regalo === null ? "?" : corrente.entra ? "=" : "≠";
        segno = crea("g", { "class": "segno " + (corrente.entra ? "vero" : "falso") });
        segno.appendChild(crea("rect", { x: sx - 13, y: sy - 13, width: 26, height: 26, "class": "cella" }));
        segno.appendChild(crea("text", {
          x: sx, y: sy, dy: "0.35em", "text-anchor": "middle", "class": "valore"
        }, t));
      }
    }
    svg.appendChild(legami);

    function classi(entrata, attuale) {
      var c = [];
      if (entrata) { c.push("tenuta"); } else if (finito) { c.push("scartata"); }
      if (attuale) { c.push("corrente"); }
      return c.join(" ");
    }

    P.forEach(function (p, i) {
      var qui = !!corrente && corrente.p === i;
      var conf = confr && qui ? "confronto" : "";
      var celle = [
        { w: CN, t: p.nome },
        p.regalo === null ?
          { w: CR, t: "NULL", cls: conf, tcls: conf ? "null confronto" : "null" } :
          { w: CR, t: p.regalo, cls: conf }
      ];
      if (nullP[i]) { celle.push({ w: CNULL, t: "NULL", cls: "null aggiunta" }); }
      fila(svg, MARGINE, yRiga(pos.p[i]), celle, {
        "class": classi(entrataP[i], qui && !confr), "data-p": i
      });
    });

    R.forEach(function (r, j) {
      var qui = !!corrente && corrente.r === j;
      var celle = [{ w: CR, t: r, cls: confr && qui ? "confronto" : "" }];
      var x = xR;
      if (nullR[j]) {
        celle.unshift({ w: CNULL, t: "NULL", cls: "null aggiunta" }, { w: CNULL, t: "NULL", cls: "null aggiunta" });
        x -= 2 * CNULL;
      }
      fila(svg, x, yRiga(pos.r[j]), celle, {
        "class": classi(entrataR[j], qui && !confr), "data-r": j
      });
    });

    if (segno) { svg.appendChild(segno); }
    fig.insertBefore(svg, fig.firstChild);
  }

  /* tabella del risultato */

  function cellaValore(tr, v) {
    tr.appendChild(v === null ? el("td", "null", "NULL") : el("td", "", v));
  }

  function disegnaRisultato(fatti, corrente) {
    var tb = $("lab-risultato").tBodies[0];
    tb.textContent = "";
    var n = 0;
    fatti.forEach(function (s) {
      if (!s.entra) { return; }
      n++;
      var tr = el("tr", s === corrente ? "nuova" : "");
      tr.tabIndex = 0;
      tr.setAttribute("data-p", s.p);
      tr.setAttribute("data-r", s.r);
      var p = s.p >= 0 ? dati.persone[s.p] : null;
      cellaValore(tr, p ? p.nome : null);
      cellaValore(tr, p ? p.regalo : null);
      cellaValore(tr, s.r >= 0 ? dati.regali[s.r] : null);
      tb.appendChild(tr);
    });
    if (!n) {
      var vuota = el("tr");
      var td = el("td", "vuoto", "nessuna riga");
      td.colSpan = 3;
      vuota.appendChild(td);
      tb.appendChild(vuota);
    }
    return n;
  }

  /* tabelle dei dati */

  function righeDati(tabella, tab, lista, campi, T) {
    var tb = tabella.tBodies[0];
    tb.textContent = "";
    if (!lista.length) {
      var tr0 = el("tr");
      var td0 = el("td", "vuoto", "nessuna riga");
      td0.colSpan = campi.length;
      tr0.appendChild(td0);
      tr0.appendChild(el("td", "azione"));
      tb.appendChild(tr0);
      return;
    }
    lista.forEach(function (riga, i) {
      var tr = el("tr");
      tr.setAttribute("data-i", i);
      campi.forEach(function (campo) {
        var td = el("td", "casella");
        var inp = el("input");
        var v = tab === "p" ? riga[campo] : riga;
        inp.type = "text";
        inp.maxLength = 20;
        inp.placeholder = "NULL";
        inp.spellcheck = false;
        inp.autocomplete = "off";
        inp.value = v === null ? "" : v;
        inp.setAttribute("aria-label", T + ", " + campo + ", riga " + (i + 1));
        inp.setAttribute("data-tab", tab);
        inp.setAttribute("data-i", i);
        inp.setAttribute("data-campo", campo);
        td.appendChild(inp);
        tr.appendChild(td);
      });
      var az = el("td", "azione");
      var b = el("button", "togli", "togli");
      b.type = "button";
      b.setAttribute("data-tab", tab);
      b.setAttribute("data-i", i);
      b.setAttribute("aria-label", "togli la riga " + (i + 1) + " di " + T);
      az.appendChild(b);
      tr.appendChild(az);
      tb.appendChild(tr);
    });
  }

  function disegnaDati() {
    righeDati($("lab-persona"), "p", dati.persone, ["nome", "regalo"], "PERSONA");
    righeDati($("lab-regalo"), "r", dati.regali, ["regalo"], "REGALO");
    $("lab-aggiungi-p").disabled = dati.persone.length >= MAX_RIGHE;
    $("lab-aggiungi-r").disabled = dati.regali.length >= MAX_RIGHE;
  }

  /* animazione: i passi scorrono da soli */

  function ferma() {
    clearTimeout(timer);
    timer = null;
  }

  // con tanti passi l'animazione accelera, per non durare troppo
  function ritmo() {
    var len = calc ? calc.passi.length : 0;
    return Math.max(0.45, Math.min(1, 12 / Math.max(len, 1)));
  }

  // quanto resta sullo schermo un passo prima del successivo
  function durata(s) {
    var ms = 1700;
    if (!s) { ms = 1400; }
    else if (s.k === "coppia") { ms = tipo === "cross" ? 900 : s.entra ? 1700 : 1100; }
    return ms * ritmo();
  }

  function tic() {
    timer = null;
    if (!calc || passo >= calc.passi.length) { return; }
    passo++;
    if (passo < calc.passi.length) { timer = setTimeout(tic, durata(calc.passi[passo - 1])); }
    mostra();
  }

  function avvia() {
    ferma();
    if (!calc || !calc.passi.length || calmo()) { return; }
    if (!aPassi || passo === calc.passi.length) {
      aPassi = true;
      passo = 0;
    }
    timer = setTimeout(tic, durata(passo > 0 ? calc.passi[passo - 1] : null));
    mostra();
  }

  /* aggiornamento */

  function mostra() {
    var passi = calc.passi;
    var len = passi.length;
    var fatti = passi.slice(0, passo);
    var corrente = aPassi && passo > 0 ? passi[passo - 1] : null;
    var finito = !aPassi || passo === len;

    lab.style.setProperty("--ritmo", ritmo());
    disegnaSchema(fatti, corrente, finito, aPassi && len > 0 && passo === len);
    var n = disegnaRisultato(fatti, corrente);

    $("lab-conta").textContent = quante(n, "riga", "righe") + (finito ? "" : " finora");
    var conto = $("lab-conto");
    conto.hidden = !finito;
    conto.textContent = finito ? testoConto(n) : "";

    var msg;
    if (aPassi && passo > 0) {
      msg = testoPasso(corrente);
    } else if (aPassi || !len) {
      msg = testoInizio();
    } else if (calmo()) {
      msg = "Premi «Dall'inizio» e poi «Avanti» per vedere la JOIN lavorare un passo alla volta.";
    } else {
      msg = "Premi «Avvia», o scegli un'altra JOIN, per vedere come combina le due tabelle.";
    }
    $("lab-messaggio").innerHTML = msg;
    $("lab-contatore").textContent = aPassi && len ? "passo " + passo + " di " + len : "";

    var avv = $("lab-avvia");
    avv.hidden = calmo();
    avv.disabled = !len;
    avv.textContent = timer ? "Pausa" : aPassi && passo > 0 && passo < len ? "Continua" : "Avvia";
    $("lab-avanti").classList.toggle("principale", calmo());

    $("lab-indietro").disabled = !aPassi || passo === 0;
    $("lab-avanti").disabled = !aPassi || passo === len;
    $("lab-tutto").disabled = !aPassi;
  }

  function aggiorna() {
    ferma();
    $("lab-query").innerHTML = QUERY[tipo];
    $("lab-sql").textContent = scriptDati();

    Array.prototype.forEach.call(lab.querySelectorAll("#lab-dati [aria-invalid]"), function (e) {
      e.removeAttribute("aria-invalid");
    });

    var err = controlla();
    if (err) {
      var inp = lab.querySelector('#lab-dati input[data-tab="' + err.tab + '"][data-i="' + err.i +
        '"][data-campo="' + err.campo + '"]');
      if (inp) { inp.setAttribute("aria-invalid", "true"); }
      $("lab-errore-testo").innerHTML = err.testo;
      $("lab-errore").hidden = false;
      $("lab-uscita").hidden = true;
      calc = null;
      return;
    }

    $("lab-errore").hidden = true;
    $("lab-uscita").hidden = false;
    calc = calcola();
    passo = aPassi ? 0 : calc.passi.length;
    mostra();
  }

  /* eventi */

  var dat = $("lab-dati");

  dat.addEventListener("input", function (e) {
    var t = e.target;
    if (!t.matches("input[data-tab]")) { return; }
    var i = Number(t.getAttribute("data-i"));
    var v = t.value === "" ? null : t.value;
    if (t.getAttribute("data-tab") === "p") {
      dati.persone[i][t.getAttribute("data-campo")] = v;
    } else {
      dati.regali[i] = v;
    }
    aggiorna();
  });

  function fuocoSu(tab, i, campo) {
    var inp = lab.querySelector('#lab-dati input[data-tab="' + tab + '"][data-i="' + i +
      '"][data-campo="' + campo + '"]');
    if (inp) { inp.focus(); }
  }

  dat.addEventListener("click", function (e) {
    var b = e.target.closest("button");
    if (!b) { return; }
    if (b.classList.contains("togli")) {
      var i = Number(b.getAttribute("data-i"));
      var p = b.getAttribute("data-tab") === "p";
      if (p) { dati.persone.splice(i, 1); } else { dati.regali.splice(i, 1); }
      disegnaDati();
      aggiorna();
      $(p ? "lab-aggiungi-p" : "lab-aggiungi-r").focus();
    } else if (b.id === "lab-aggiungi-p" && dati.persone.length < MAX_RIGHE) {
      dati.persone.push({ nome: null, regalo: null });
      disegnaDati();
      aggiorna();
      fuocoSu("p", dati.persone.length - 1, "nome");
    } else if (b.id === "lab-aggiungi-r" && dati.regali.length < MAX_RIGHE) {
      dati.regali.push(null);
      disegnaDati();
      aggiorna();
      fuocoSu("r", dati.regali.length - 1, "regalo");
    }
  });

  lab.addEventListener("change", function (e) {
    if (e.target.name === "lab-tipo") {
      tipo = e.target.value;
      if (!calmo()) { aPassi = true; }
      aggiorna();
      avvia();
    }
  });

  // se il bottone premuto si disattiva, il fuoco passa a quello accanto
  function dopo(spento, altro) {
    if ($(spento).disabled && document.activeElement === $(spento)) { $(altro).focus(); }
  }

  $("lab-avvia").addEventListener("click", function () {
    if (timer) {
      ferma();
      if (calc) { mostra(); }
    } else {
      avvia();
    }
  });
  $("lab-dacapo").addEventListener("click", function () {
    ferma();
    if (!calc) { return; }
    aPassi = true;
    passo = 0;
    mostra();
  });
  $("lab-indietro").addEventListener("click", function () {
    ferma();
    if (!calc || !aPassi || passo === 0) { return; }
    passo--;
    mostra();
    dopo("lab-indietro", "lab-avanti");
  });
  $("lab-avanti").addEventListener("click", function () {
    ferma();
    if (!calc || !aPassi || passo === calc.passi.length) { return; }
    passo++;
    mostra();
    dopo("lab-avanti", "lab-dacapo");
  });
  $("lab-tutto").addEventListener("click", function () {
    ferma();
    if (!calc) { return; }
    aPassi = false;
    passo = calc.passi.length;
    mostra();
    dopo("lab-tutto", "lab-dacapo");
  });

  // prove e collegamenti dalle sezioni della pagina
  document.addEventListener("click", function (e) {
    var b = e.target.closest("[data-prova], a[data-tipo]");
    if (!b) { return; }
    dati = copia(INIZIALI);
    if (b.hasAttribute("data-prova")) {
      var prova = PROVE[b.getAttribute("data-prova")];
      if (prova) { prova(dati); }
      if (!lab.contains(b)) {
        lab.scrollIntoView({ behavior: calmo() ? "auto" : "smooth", block: "start" });
      }
    } else {
      tipo = b.getAttribute("data-tipo");
      aPassi = true;
      var radio = lab.querySelector('input[name="lab-tipo"][value="' + tipo + '"]');
      if (radio) { radio.checked = true; }
    }
    disegnaDati();
    aggiorna();
    if (b.hasAttribute("data-tipo")) { avvia(); }
  });

  // se cambiano le impostazioni del computer mentre la pagina è aperta
  function cambiaMoto() {
    if (calmo()) { ferma(); }
    if (calc) { mostra(); }
  }
  if (mq && mq.addEventListener) {
    mq.addEventListener("change", cambiaMoto);
  } else if (mq && mq.addListener) {
    mq.addListener(cambiaMoto);
  }

  // da quali righe viene una riga del risultato
  var ris = $("lab-risultato");

  function pulisci() {
    Array.prototype.forEach.call(lab.querySelectorAll(".puntata"), function (e) {
      e.classList.remove("puntata");
    });
  }

  function punta(tr) {
    var p = tr.getAttribute("data-p");
    var r = tr.getAttribute("data-r");
    var sel = [];
    if (p !== "-1") {
      sel.push('#lab-schema g[data-p="' + p + '"]', '#lab-persona tbody tr[data-i="' + p + '"]');
    }
    if (r !== "-1") {
      sel.push('#lab-schema g[data-r="' + r + '"]', '#lab-regalo tbody tr[data-i="' + r + '"]');
    }
    if (p !== "-1" && r !== "-1") {
      sel.push('#lab-schema line[data-coppia="' + p + "-" + r + '"]');
    }
    if (sel.length) {
      Array.prototype.forEach.call(lab.querySelectorAll(sel.join(",")), function (e) {
        e.classList.add("puntata");
      });
    }
    tr.classList.add("puntata");
  }

  function sopra(e) {
    var tr = e.target.closest("tbody tr[data-p]");
    if (!tr) { return; }
    pulisci();
    punta(tr);
  }

  ris.addEventListener("mouseover", sopra);
  ris.addEventListener("focusin", sopra);
  ris.addEventListener("mouseleave", pulisci);
  ris.addEventListener("focusout", pulisci);

  /* avvio */

  var scelto = lab.querySelector('input[name="lab-tipo"]:checked');
  tipo = scelto ? scelto.value : "inner";
  dati = copia(INIZIALI);
  disegnaDati();
  aggiorna();
})();
