const heading = document.querySelector("h1");

console.log(heading.classList);

heading.classList.add("text-uppercase","text-center");

console.log(heading.classList.contains("abc"));// false
console.log(heading.classList.contains("text-red"));// true

// Thực hiện 1 công việc sau 1 khoảng thời gian nhất định (ms)
setInterval(function(){
    heading.classList.toggle("text-red")
},1000)