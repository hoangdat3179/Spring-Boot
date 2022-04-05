const p = document.getElementById("text");

function quote() {
  let quote = ["January", "February", "March", "April", "May", "June", "July"];
  const randomElement = quote[Math.floor(Math.random() * quote.length)];
  console.log(randomElement);
  p.innerHTML = randomElement;
}

const btn2 = document.getElementById("btn-2");
btn2.onclick = function () {
    let randomColor = "#000000".replace(/0/g, function () {
    return (~~(Math.random() * 16)).toString(16);
  });
  console.log(randomColor);
  p.style.color = randomColor;
};

const btn3 = document.getElementById("btn-3");
btn3.addEventListener("click", function () {
  let x = Math.floor(Math.random() * 256);
  let y = Math.floor(Math.random() * 256);
  let z = Math.floor(Math.random() * 256);
  let bgColor = "rgb(" + x + "," + y + "," + z + ")";
  console.log(bgColor);
  p.style.background = bgColor;
});
