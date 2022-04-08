let colors = ["#3498db", "#9b59b6", "#e74c3c", "#2c3e50", "#d35400"];
let i = 0;
let box1 = document.createElement("div");
box1.classList.add("box");
box1.style.backgroundColor = colors[i];
box1.onclick = function () {
  box1.remove();
  count--;
  showCount.innerHTML = count;
};
let box2 = document.createElement("div");
box2.classList.add("box");
box2.style.backgroundColor = colors[i + 1];
box2.onclick = function () {
  box2.remove();
  count--;
  showCount.innerHTML = count;
};
let box3 = document.createElement("div");
box3.classList.add("box");
box3.style.backgroundColor = colors[i + 2];
box3.onclick = function () {
  box3.remove();
  count--;
  showCount.innerHTML = count;
};
let box4 = document.createElement("div");
box4.classList.add("box");
box4.style.backgroundColor = colors[i + 3];
box4.onclick = function () {
  box4.remove();
  count--;
  showCount.innerHTML = count;
};
let box5 = document.createElement("div");
box5.classList.add("box");
box5.style.backgroundColor = colors[i + 4];
box5.onclick = function () {
  box5.remove();
  count--;
  showCount.innerHTML = count;
};
document.body.appendChild(box1);
document.body.appendChild(box2);
document.body.appendChild(box3);
document.body.appendChild(box4);
document.body.appendChild(box5);
const showCount = document.querySelector("span");
let count = 5;
const btn = document.getElementById("btn");

showCount.innerHTML = count;
btn.addEventListener("click", function () {
  let box = document.createElement("div");
  box.classList.add("box");
  box.style.backgroundColor = colors[i];
  document.body.appendChild(box);
  i++;
  if (i == colors.length) {
    i = 0;
  }

  box.onclick = function () {
    box.remove();
    count--;
    showCount.innerHTML = count;
  };
});
btn.onclick = function () {
  count++;
  showCount.innerHTML = count;
};
