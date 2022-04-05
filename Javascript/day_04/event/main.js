// Sử dụng HTML-attribute
function sayHello(){
    alert("Xin chào");
}

// Sử dụng DOM property
const btn2 = document.getElementById("btn-2");
// btn2.onclick = function(){
//     alert("Xin chào 1");
// }

btn2.onclick = sayHello2;
function sayHello2(){
    alert("xin chào 2");
}

// Sử dụng addEventListener

const btn3 = document.getElementById("btn-3");
btn3.addEventListener("click",function(){
    alert("xin chào 3");
})