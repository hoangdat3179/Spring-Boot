// Câu 1

const para = document.getElementById("text");
para.style.color = "#777";
para.style.fontSize = "2rem";
para.innerHTML =
  "Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript.";

// Câu 2

const li = document.querySelectorAll("li");
for (let i = 0; i < li.length; i++) {
  li[i].style.color = "blue";
}

// Câu 3

// 1

const ul2 = document.getElementById("list");
const li8 = document.createElement("li");
const li9 = document.createElement("li");
const li10 = document.createElement("li");
li8.innerText = "Item 8";
li9.innerText = "Item 9";
li10.innerText = "Item 10";

ul2.appendChild(li8);
ul2.appendChild(li9);
ul2.appendChild(li10);

// 2

const li1 = document.querySelector("ul:nth-child(3) li:nth-child(1)");
const li3 = document.querySelector("ul:nth-child(3) li:nth-child(3)");
const li4 = document.querySelector("ul:nth-child(3) li:nth-child(4)");
li1.style.color = "red";
li3.style.backgroundColor = "blue";

// 3

li4.remove();

// 4

const li4_new = document.createElement("li");
li4_new.innerText = "Thẻ li4 new";
const li4_position = document.querySelector("ul:nth-child(3) li:nth-child(4)");
ul2.insertBefore(li4_new, li4_position);
