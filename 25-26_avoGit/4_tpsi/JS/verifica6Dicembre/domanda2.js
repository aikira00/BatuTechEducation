let bEl = document.querySelector("body");
let olEl = document.createElement("ol");
let liEl = document.createElement("li");
liEl.innerText = "Gargamella"
bEl.appendChild(olEl);
olEl.appendChild(liEl);

// aggiungo Birba
let nuovoLi = document.createElement("li");
nuovoLi.innerText = "Birba";
olEl.appendChild(nuovoLi);