function countRabbits() {
    for (let i = 1; i <=3; i++) {
        alert(`Rabbit ${i}`);
    }
}

let hello = document.getElementById("hello");
hello.onclick = () => {
    alert("Hello World!");
}

let who = document.getElementById("who");
who.onclick = () => {
    alert("Superman");
}

// 3개의 이벤트가 발생
let food = document.getElementById("food");
food.onclick = () => {
    alert("Pasta");
}
// addEventListener
food.addEventListener("click", () => {
    alert("Pizza");
});

const handler = () => {
    alert("sushi");
}

food.addEventListener("click", handler);

// removeEventListener
setTimeout(() => {
    // removeEventListener
    food.removeEventListener("click", handler);
}, 3000);

// 객체 형태 핸들러
let obj = {
    handleEvent(event) {
        alert(`${event.type} 이벤트가 ${event.currentTarget} + "에서 발생했습니다."`);
    }
}

document.getElementById("objInput").addEventListener("click", obj);

// innerHTML
let spanTag = document.getElementById("spanTag");

let changeTxt = {
    handleEvent(event) {
        switch(event.type) {
            case 'mousedown':
                spanTag.innerHTML = "Mouse Down";
                break;
            case 'mouseup':
                spanTag.innerHTML = "Mouse Up";
                break;
        }
    }
}

document.getElementById("textInput").addEventListener("mousedown", changeTxt);
document.getElementById("textInput").addEventListener("mouseup", changeTxt);