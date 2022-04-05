// // Lắng nghe sự kiện keydown
// document.addEventListener("keydown", function () {
//     console.log("keydown");
// });

// // Lắng nghe sự kiện keyup
// document.addEventListener("keyup", function () {
//     console.log("keyup");
// });

// // Lắng nghe sự kiện keypress
// document.addEventListener("keypress", function () {
//     console.log("keypress");
// });
// Lắng nghe sự kiện keydown

// document.addEventListener("keydown", function (event) {
//     console.log(event);
// });

// document.addEventListener("keydown", function (event) {
//     if (event.keyCode == 13) {
//         console.log("Bạn thật đẹp trai!");
//     } else {
//         console.log("Bấm phím Enter đi, có điều bất ngờ đó!")
//     }
// });

// const inputEl = document.querySelector("input");
// inputEl.addEventListener("keydown", function(event){
//     if(event.keyCode == 13){
//         let value = inputEl.value;
//         // let value = this.value;
//         // let value = event.target.value;
//         alert(`từ khóa bạn vừa nhập là : ${value}`);
//     }
// })

// inputEl.addEventListener("change", function(event){
//         let value = event.target.value;
//         inputEl.value = value.toUpperCase();
// })

// inputEl.addEventListener("focus", function(){
//     inputEl.value = inputEl.value.toLowerCase();
// })

const input = document.querySelector("input");
const btnshow = document.getElementById("show");
btnshow.addEventListener("click", function () {
  const currentType = input.getAttribute("type");
  input.setAttribute("type", currentType === "password" ? "text" : "password");
})
