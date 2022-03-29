// Bài 1
function calculateFactorial(a) {
  let factorial = 1;
  if (a == 0 || a == 1) {
    return factorial;
  } else {
    for (let i = 2; i <= a; i++) {
      factorial *= i;
    }
    return factorial;
  }
}

console.log(calculateFactorial(5))

//Bài 2
function reverseString(s) {
    let result = '';
  for (let i = s.length; i > -1; i--) {
        result += s.charAt(i);
  }
  return result;
}

console.log(reverseString('hello'))

//Bài 3
function translate(message) {
  switch (message) {
    case "VN":
      return "Xin chào";
    case "EN":
      return "Hello";
    case "ESP":
      return "Hola";
    case "FR":
      return "Salut";
    case "DE":
      return "Hallo";
    case "RU":
      return "Привет";
    case "KR":
      return "안녕";
    default:
      return "";
  }
}

console.log(translate("VN"));

//Bài 4
function subString(s){
    let temp = '';
    for(let i = 0; i < 10 ;i++){
        temp += s.charAt(i);
    }
    return temp + '...';
}

console.log(subString('xinchaocacbandenvoiTechmaster'))
