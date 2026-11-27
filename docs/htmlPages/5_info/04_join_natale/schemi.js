/* Schemi delle JOIN sul database natale.
   Ogni <figure class="schema" data-modo="..."> riceve un disegno SVG:
   a sinistra le righe di una tabella, a destra quelle dell'altra, in verde i legami. */
(function () {
  "use strict";

  var NS = "http://www.w3.org/2000/svg";
  var W = 460;        // larghezza del disegno
  var H = 40;         // altezza di una riga
  var PASSO = 48;     // distanza fra le righe
  var TOP = 56;       // y della prima riga
  var MARGINE = 10;
  var CN = 64;        // cella "nome"
  var CR = 112;       // cella "regalo" / "colore"
  var CNULL = 52;     // cella NULL

  function crea(tag, attr, testo) {
    var e = document.createElementNS(NS, tag);
    Object.keys(attr).forEach(function (k) { e.setAttribute(k, attr[k]); });
    if (testo !== undefined) { e.textContent = testo; }
    return e;
  }

  function yRiga(posto) { return TOP + posto * PASSO; }

  // una fila di celle affiancate, a partire da x
  function fila(svg, x, y, celle, classe, h) {
    h = h || H;
    var g = crea("g", { "class": classe || "" });
    celle.forEach(function (c) {
      var extra = c.cls ? " " + c.cls : "";
      g.appendChild(crea("rect", { x: x, y: y, width: c.w, height: h, "class": "cella" + extra }));
      g.appendChild(crea("text", {
        x: x + c.w / 2, y: y + h / 2, dy: "0.35em",
        "text-anchor": "middle", "class": "valore" + extra
      }, c.t));
      x += c.w;
    });
    svg.appendChild(g);
  }

  function nomeTabella(svg, x, testo, ancora) {
    svg.appendChild(crea("text", { x: x, y: 16, "text-anchor": ancora, "class": "nome-tab" }, testo));
  }

  function legame(gruppo, x1, y1, x2, y2) {
    gruppo.appendChild(crea("line", { x1: x1, y1: y1, x2: x2, y2: y2, "class": "legame" }));
  }

  // PERSONA a sinistra, REGALO a destra: i regali senza coppia stanno più in basso,
  // così le celle NULL della LEFT e della RIGHT non si sovrappongono
  var PERSONE = [["Anna", "calzini", 0], ["Bruno", "calzini", 1], ["Carla", "monopattino", 2]];
  var REGALI = [["calzini", 0.5], ["felpa", 3], ["sciarpa", 4]];

  // 1 = la riga entra nel risultato, 0 = viene scartata
  var MODI = {
    base:  { p: null, r: null },
    inner: { p: [1, 1, 0], r: [1, 0, 0] },
    left:  { p: [1, 1, 1], r: [1, 0, 0], nullP: true },
    right: { p: [1, 1, 0], r: [1, 1, 1], nullR: true },
    full:  { p: [1, 1, 1], r: [1, 1, 1], nullP: true, nullR: true }
  };

  function stato(lista, i) {
    if (!lista) { return ""; }
    return lista[i] ? "tenuta" : "scartata";
  }

  function schemaPersonaRegalo(svg, modo) {
    var m = MODI[modo] || MODI.base;
    var xR = W - MARGINE - CR;
    svg.setAttribute("viewBox", "0 0 " + W + " " + (yRiga(4) + H + MARGINE));

    nomeTabella(svg, MARGINE, "PERSONA", "start");
    nomeTabella(svg, W - MARGINE, "REGALO", "end");
    fila(svg, MARGINE, 24, [{ w: CN, t: "nome" }, { w: CR, t: "regalo" }], "intest", 26);
    fila(svg, xR, 24, [{ w: CR, t: "regalo" }], "intest", 26);

    var legami = crea("g", {});
    var yCalzini = yRiga(REGALI[0][1]) + H / 2;
    legame(legami, MARGINE + CN + CR, yRiga(0) + H / 2, xR, yCalzini);
    legame(legami, MARGINE + CN + CR, yRiga(1) + H / 2, xR, yCalzini);
    svg.appendChild(legami);

    PERSONE.forEach(function (p, i) {
      var celle = [{ w: CN, t: p[0] }, { w: CR, t: p[1] }];
      if (m.nullP && i === 2) {
        celle.push({ w: CNULL, t: "NULL", cls: "null" });
      }
      fila(svg, MARGINE, yRiga(p[2]), celle, stato(m.p, i));
    });

    REGALI.forEach(function (r, i) {
      var celle = [{ w: CR, t: r[0] }];
      var x = xR;
      if (m.nullR && i > 0) {
        celle.unshift({ w: CNULL, t: "NULL", cls: "null" }, { w: CNULL, t: "NULL", cls: "null" });
        x -= 2 * CNULL;
      }
      fila(svg, x, yRiga(r[1]), celle, stato(m.r, i));
    });
  }

  // REGALO a sinistra, COLORE a destra: ogni regalo con ogni colore
  function schemaCross(svg) {
    var regali = ["calzini", "felpa", "sciarpa"];
    var colori = ["bianco", "oro", "rosso", "verde"];
    var xC = W - MARGINE - CR;
    svg.setAttribute("viewBox", "0 0 " + W + " " + (yRiga(3) + H + MARGINE));

    nomeTabella(svg, MARGINE, "REGALO", "start");
    nomeTabella(svg, W - MARGINE, "COLORE", "end");
    fila(svg, MARGINE, 24, [{ w: CR, t: "regalo" }], "intest", 26);
    fila(svg, xC, 24, [{ w: CR, t: "colore" }], "intest", 26);

    var legami = crea("g", { "class": "fitto" });
    regali.forEach(function (r, i) {
      colori.forEach(function (c, j) {
        legame(legami, MARGINE + CR, yRiga(i + 0.5) + H / 2, xC, yRiga(j) + H / 2);
      });
    });
    svg.appendChild(legami);

    regali.forEach(function (r, i) { fila(svg, MARGINE, yRiga(i + 0.5), [{ w: CR, t: r }], ""); });
    colori.forEach(function (c, j) { fila(svg, xC, yRiga(j), [{ w: CR, t: c }], ""); });
  }

  var figure = document.querySelectorAll("figure.schema[data-modo]");
  Array.prototype.forEach.call(figure, function (fig) {
    var modo = fig.getAttribute("data-modo");
    var svg = crea("svg", { "aria-hidden": "true", focusable: "false" });
    if (modo === "cross") {
      schemaCross(svg);
    } else {
      schemaPersonaRegalo(svg, modo);
    }
    fig.insertBefore(svg, fig.firstChild);
  });
})();
