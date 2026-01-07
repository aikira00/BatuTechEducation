const goodPeople = [];
const badPeople = [];



document.querySelector("#btn").addEventListener("click", function(){
    const name = document.getElementById("nome").value;
    const li = document.createElement("li");
    li.innerHTML = name;

    //TODO fare controllo lista se nome già presente
    document.getElementById("msg").innerText  = "";
    if(name){
        if(document.getElementById("good").checked){
            goodPeople.push(name);
            document.getElementById("lg").appendChild(li);
        }
        else{//se check box nn selezionata.. sei di default un brutto cattivo
            badPeople.push(name);
            document.getElementById("lb").appendChild(li);
        }
    }
    else{
        document.getElementById("msg").innerText  = "Nome non valorizzato";
    }
    document.getElementById("good").checked = false;
    document.getElementById("bad").checked = false;
})