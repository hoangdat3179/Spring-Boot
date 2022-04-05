let colors = ["#3498db", "#9b59b6", "#e74c3c", "#2c3e50", "#d35400"];
const showCount = document.querySelector("span");
let count = 0;

const btn = document.getElementById("btn");
showCount.innerHTML = count;
btn.addEventListener("click", function () {
  const randomElement = colors[Math.floor(Math.random() * colors.length)];
  let box = document.createElement("div");
  box.classList.add("box");
  box.style.backgroundColor = randomElement;
  document.body.appendChild(box);

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
