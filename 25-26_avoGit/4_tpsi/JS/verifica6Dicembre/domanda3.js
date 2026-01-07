let butEl = document.querySelector("button");

butEl.addEventListener("click", ()=>{
    butEl.innerText += 1;
    //giusto
    //butEl.innerText = parseInt(butEl.innerText) + 1;

    console.log(butEl.innerText);
})
