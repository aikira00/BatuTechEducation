console.log('Happy developing ✨')
document.body.style.backgroundColor = 'black';
const ghost = document.getElementById('ghost')
ghost.hidden = true;
//ghost.innerText = "";

const button = document.getElementById('ghostButton');
button.addEventListener('click', () => {
    ghost.hidden = false;
   // ghost.innerText = "👻";
    ghost.style.fontSize = "20px";
});


const lamp = document.getElementById('lamp');
lamp.classList.add("my_lamp");
lamp.addEventListener('click', () => {
    document.body.style.backgroundColor = 'white';
});

const buttonSum = document.getElementById('buttonSum');
const buttonSub = document.getElementById('buttonSub');
let counter = 0;
const counterEl = document.querySelector('.counter');
buttonSub.addEventListener('click', () => {
    counter--;
    counterEl.innerText = counter;
})

buttonSum.addEventListener('click', () => {
    counter++;
    counterEl.innerText = counter;
})