// 1
const heading = document.getElementById("heading");
heading.style.color = "red";
heading.style.textTransform = "uppercase";
console.log(heading)

// 2
const paras = document.getElementsByClassName("para");
for (let i = 0; i < paras.length; i++) {
    paras[i].style.color = "blue"; 
    paras[i].style.fontSize = "20px";
}

// 3
const ele = document.querySelector(".content");
const link = document.createElement("a");
link.innerText = "facebook";
link.href="https://www.facebook.com/";
document.body.insertBefore(link,ele)

// 4
const title = document.getElementById("title");
title.innerText = "Đây là thẻ tiêu đề";

// 5
const des = document.getElementsByClassName("description");

// 6

