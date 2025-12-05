console.log('Happy developing ✨')

let bimbiBuoni = [];
let bimbiCattivelli = [];

function trovaNome(nome) {
    if(bimbiBuoni.includes(nome)) {
        console.log('Il bimbo ' + nome + ' è buono');
    }
    else if(bimbiCattivelli.includes(nome)) {
        console.log('Il bimbo ' + nome + ' è cattivello');
    }
    else{
        console.log('Il bimbo ' + nome + ' non è in una lista');
    }
}

function trovaCattivo(nome){
    return bimbiCattivelli.includes(nome) ? bimbiCattivelli.indexOf(nome) : null;
}

function elimina(nome){
    const indiceBuono = bimbiBuoni.indexOf(nome);
    const indiceCattivo = bimbiCattivelli.indexOf(nome);

    if (indiceBuono !== -1) {
        bimbiBuoni.splice(indiceBuono, 1);
    } else if (indiceCattivo !== -1) {
        bimbiCattivelli.splice(indiceCattivo, 1);
    }
}

function aggiungiBuono(nome){
    if(!bimbiBuoni.includes(nome)){
        bimbiBuoni.push(nome);
    }
}

function aggiungiCattivo(nome){
    if(!bimbiCattivelli.includes(nome)){
        bimbiCattivelli.push(nome);
    }
}

function modificaBambino(oldName, newName){
    /*if(bimbiBuoni.includes(oldName)){
        //cancello da indice oldName un solo elemento e aggiungo newName
        bimbiBuoni.splice(bimbiBuoni.indexOf(oldName), 1, newName);
        // o meglio bimbiBuoni.replace(oldName, newName); ?
    }
    else if(bimbiCattivelli.includes(oldName)){
        bimbiCattivelli.splice(bimbiCattivelli.indexOf(oldName), 1, newName);
    }*/
    const indiceBuono = bimbiBuoni.indexOf(oldName);
    const indiceCattivo = bimbiCattivelli.indexOf(oldName);

    if (indiceBuono !== -1) {
        bimbiBuoni[indiceBuono] = newName;
    } else if (indiceCattivo !== -1) {
        bimbiCattivelli[indiceCattivo] = newName;
    }
}

function spostaBambino(nome){
    const indiceBuono = bimbiBuoni.indexOf(nome);
    const indiceCattivo = bimbiCattivelli.indexOf(nome);

    if (indiceBuono !== -1) {
        bimbiBuoni.splice(indiceBuono, 1);
        bimbiCattivelli.push(nome);
    } else if (indiceCattivo !== -1) {
        bimbiCattivelli.splice(indiceCattivo, 1);
        bimbiBuoni.push(nome);
    }
}

function stampaLista(){
    const listAll = (bimbiBuoni.concat(bimbiCattivelli)).sort();
    let strList = '';
    for(let i = 0; i < listAll.length; i++){
        if(bimbiCattivelli.includes(listAll[i])){
            strList += listAll[i] + ' CATTIVELLO, ';
        }
        else if(bimbiBuoni.includes(listAll[i])){
            strList += listAll[i] + ' BUONO, ';
        }
    }
    console.log(strList);
}

aggiungiBuono('Bimbo1');
aggiungiBuono('Bimbo2');
aggiungiBuono('Zeno');
aggiungiCattivo('Bimbo3');
aggiungiCattivo('Bimbo4');
aggiungiCattivo('Aiki');
console.log(bimbiBuoni);
console.log(bimbiCattivelli);
stampaLista();
spostaBambino('Bimbo1');
stampaLista();
spostaBambino('Bimbo5');
stampaLista();
spostaBambino('Aiki');
stampaLista();
modificaBambino('Bimbo1', 'BimboX1');
stampaLista();
trovaNome('Bimbo2');
console.log(trovaCattivo('Bimbo2'))
console.log(trovaCattivo('Bimbo3'))
let nome = prompt('Inserisci bimbo');
aggiungiBuono(nome);
stampaLista();