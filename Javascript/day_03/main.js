const heading = document.getElementById("heading");

console.log(heading);

const para2 = document.querySelector(".para");

console.log(para2);

const para3 = document.querySelector("p:nth-child(4)");

console.log(para3);

const ul2 = document.querySelector("body > ul")
// const ul2 = document.querySelector(".box + ul")

console.log(ul2);

const ul1 = document.querySelector("div > ul")

console.log(ul1);

const li44 = document.querySelector(".box ul li:last-child")
console.log(li44);

// querySelectorAll
const paras = document.querySelectorAll("p");

console.log(paras);
console.log(paras[1]);
console.log(paras.length);

// Duyệt NoteList
for (let i = 0; i < paras.length; i++) {
    console.log(paras[i]); 
}
Array.from(paras).map(ele => console.log(ele));//sử dụng map

// Truy cập gián tiếp
console.log(para3.previousElementSibling);
console.log(para3.nextElementSibling);
console.log(para3.parentElement);

heading.style.color = "red";
heading.style.backgroundColor = "black";

for (let i = 0; i < paras.length; i++) {
    paras[i].style.color = "white"; 
}

paras.forEach(ele => {
    ele.style.backgroundColor = "black";
})

Array.from(paras).map(ele => {
    ele.style.fontSize = "30px";
});

// Lấy ra nội dung
console.log(heading.innerHTML);
console.log(heading.innerText);
console.log(heading.textContent);

console.log(ul1.innerHTML);
console.log(ul1.innerText);
console.log(ul1.textContent);


// Thay đổi nội dung
heading.innerHTML = "Xin chào"
heading.innerHTML = "<span>Các bạn</span>"

heading.innerText = "Các bạn có khỏe không?"
heading.innerText = "<span>Rất yếu</span>"

const link = document.createElement("a");
link.innerText = "Google";
link.href = "https://www.google.com.vn/?hl=vi"

console.log(link);
// document.body.prepend(link);
// document.body.appendChild(link);

const boxEl = document.querySelector(".box");

document.body.insertBefore(link, boxEl);

// Tạo thẻ li có nội dung "li new" nằm trước thẻ li "Thẻ li 3"
const li_new = document.createElement("li");
li_new.innerText = "li new";
const li3 = document.querySelector(".box + ul li:nth-child(3)");
console.log(li3);
ul2.insertBefore(li_new,li3);

para2.insertAdjacentHTML("beforebegin","<button>login</button>");

const para1 = document.querySelector("p");
para1.insertAdjacentHTML("afterend","<button>Logout</button>");

// Tạo thẻ
const li = document.createElement("li")
li.innerText = "li new";

ul1.insertAdjacentElement("afterbegin",li);

// Xóa
// document.body.removeChild(para2);

para2.parentElement.removeChild(para2);

// Thay thế
const h2 = document.createElement("h2")
h2.innerText = "h2 new";

// document.body.replaceChild(h2,heading)

heading.parentElement.replaceChild(h2,heading);

// Copy
const boxCopy1 = boxEl.cloneNode(true);
const boxCopy2 = boxEl.cloneNode(false);
console.log(boxCopy1);
console.log(boxCopy2);

h2.insertAdjacentElement("afterend",boxCopy1)