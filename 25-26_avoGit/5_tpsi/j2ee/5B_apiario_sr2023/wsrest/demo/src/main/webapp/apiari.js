console.log("siamoqui");
window.onload = function (){
    document.getElementById("apAll").addEventListener("click", getAllApiari);
    //document.getElementById("apByP").addEventListener("click", getApiariByProvincia);
    document.getElementById("provinceDropdown").addEventListener("change", getApiariByProvincia);


    //popolo dropdown list
    const dropdown = document.getElementById("provinceDropdown");
    const url = "http://localhost:8080/demo_war/api/apiari/province"
    // await necessita di async function, vado in load window con fetch then
    fetch(url)
        //dopo che fecth ottiene response allora
        .then(response=>response.json())
        //dopo che ho estratto json e lo metto in province allora
        .then(province => {

            // Aggiunge ogni provincia al dropdown
            //province array costrutto foreach array,forEach(elemento, indice, array) indiece e array opzionali
            province.forEach(provincia => {
                const option = document.createElement('option');
                option.value = provincia;
                option.textContent = provincia;
                dropdown.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Errore nel caricamento delle province:', error);
            // Aggiunge un'opzione di errore
            const errorOption = document.createElement('option');
            errorOption.disabled = true;
            errorOption.textContent = 'Errore nel caricamento delle province';
            dropdown.appendChild(errorOption);
        });
}

async function getAllApiari(){
    const url = "http://localhost:8080/demo_war/api/apiari/all"
    const tbody = document.getElementById('apiariBody');
    tbody.innerText ="";
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }
        const json = await response.json();
        console.log(json);
        const tbody = document.getElementById('apiariBody');

        json.forEach(apiario => {
            const row = document.createElement('tr');

            // Creare e aggiungere celle
            const idCell = document.createElement('td');
            idCell.textContent = apiario.id;
            row.appendChild(idCell);

            const localitaCell = document.createElement('td');
            localitaCell.textContent = apiario.località;
            row.appendChild(localitaCell);

            const apicoltoreCell = document.createElement('td');
            apicoltoreCell.textContent = apiario.apicultore;
            row.appendChild(apicoltoreCell);

            const provinciaCell = document.createElement('td');
            provinciaCell.textContent = apiario.provincia;
            row.appendChild(provinciaCell);

            const linkCell = document.createElement('td');
            const linkElem = document.createElement('a');
            linkElem.href = 'https://' + apiario.link;
            linkElem.textContent = apiario.link;
            linkElem.target = '_blank';
            linkCell.appendChild(linkElem);
            row.appendChild(linkCell);

            // Aggiungere la riga alla tabella
            tbody.appendChild(row);
        });
    }
    catch(error){
        console.log("problema " + error);
    }
}

async function getApiariByProvincia(){
    const provinciaSelezionata = document.getElementById('provinceDropdown').value;
    console.log("per provincia " + provinciaSelezionata);
    const tbody = document.getElementById('apiariBody');
    tbody.innerText ="";
    if (provinciaSelezionata != ""){
        const url = "http://localhost:8080/demo_war/api/apiari/search?provincia="+provinciaSelezionata;
        console.log(url);
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }
        const json = await response.json();
        console.log(json);
        console.log(json.length);
        if(json.length>0) {
            json.forEach(apiario => {
                const row = document.createElement('tr');

                // Creare e aggiungere celle
                const idCell = document.createElement('td');
                idCell.textContent = apiario.id;
                row.appendChild(idCell);

                const localitaCell = document.createElement('td');
                localitaCell.textContent = apiario.località;
                row.appendChild(localitaCell);

                const apicoltoreCell = document.createElement('td');
                apicoltoreCell.textContent = apiario.apicultore;
                row.appendChild(apicoltoreCell);

                const provinciaCell = document.createElement('td');
                provinciaCell.textContent = apiario.provincia;
                row.appendChild(provinciaCell);

                const linkCell = document.createElement('td');
                const linkElem = document.createElement('a');
                linkElem.href = 'https://' + apiario.link;
                linkElem.textContent = apiario.link;
                linkElem.target = '_blank';
                linkCell.appendChild(linkElem);
                row.appendChild(linkCell);

                // Aggiungere la riga alla tabella
                tbody.appendChild(row);
            });
        }
        else{
            const row = document.createElement('tr');
            const cell = document.createElement('td');
            cell.colSpan = 5;
            cell.textContent = "Non ci sono apiari in questa provincia";
            cell.style.textAlign = "center";
            cell.style.fontStyle = "italic";
            row.appendChild(cell);
            tbody.appendChild(row);
        }

    }
    else{
        const row = document.createElement('tr');
        const cell = document.createElement('td');
        cell.colSpan = 5;
        cell.textContent = "Seleziona una provincia per visualizzare gli apiari.";
        cell.style.textAlign = "center";
        cell.style.fontStyle = "italic";
        row.appendChild(cell);
        tbody.appendChild(row);
    }

}