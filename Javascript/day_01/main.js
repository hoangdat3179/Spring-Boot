console.log("xin chào");

let name = "Đạt";
console.log(name);

const email = "dat123@gmail.com";
console.log(email);

console.log(`${name} ${email}`);

//Frist class function
// 1 function có thể gắn cho 1 biến function expresstion

//Higher order function
// 1 function có thể truyền vào như là 1 tham số cho funtion khác
// 1 function có thể return về 1 function khác

// function expresstion
let sum1 = function (a, b) {
  return a + b;
};

console.log(sum1(5, 5));

// arrow function
let sum2 = (a, b) => a + b;

console.log(sum2(5, 7));

function check(mark) {
  if (mark >= 85) {
    console.log("A");
  } else if (70 <= mark && mark < 85) {
    console.log("B");
  } else if (40 <= mark && mark < 70) {
    console.log("C");
  } else {
    console.log("D");
  }
}

function check1(a, b) {
  if (a > b) {
    console.log(a);
  } else {
    console.log(b);
  }
}

function check2(a) {
  if (a < 0) {
    console.log("Số âm");
  }
  if (a > 0) {
    console.log("Số dương");
  } else {
    console.log("Số 0");
  }
}

function check3(a) {
  if (a % 2 == 0) {
    console.log("Số chẵn");
  } else {
    console.log("Số lẻ");
  }
}

function check4(a) {
  if (a % 3 == 0 && a % 5 == 0) {
    console.log("a chia hết cho 3 và 5");
  } else {
    console.log("không chia hết");
  }
}

function check5(a, b, c) {
  if (a + b == c) {
    console.log("có");
  } else {
    console.log("không");
  }
}

check(90);
check2(5, 6);
check3(4);
check4(7);
check5(3, 5, 6);

function switchCase(day) {
  switch (day) {
    case 0: {
      console.log("Hôm nay là chủ nhật");
      break;
    }
    case 1: {
      console.log("Hôm nay là thứ 2");
      break;
    }
    case 2: {
      console.log("Hôm nay là thứ 3");
      break;
    }
    case 3: {
      console.log("Hôm nay là thứ 4");
      break;
    }
    case 4: {
      console.log("Hôm nay là thứ 5");
      break;
    }
    case 5: {
      console.log("Hôm nay là thứ 6");
      break;
    }
    case 6: {
      console.log("Hôm nay là thứ 7");
      break;
    }
    default:
      console.log("0");
  }
}

switchCase(8);

function switchCase1(month) {
  switch (month) {
    case 1:
    case 2:
    case 3:
      return "Mùa xuân";

    case 4:
    case 5:
    case 6:
      return "Mùa hạ";
    case 7:
    case 6:
    case 8:
      return "Mùa thu";
    case 10:
    case 11:
    case 12:
      return "Mùa đông";
    default:
  }
}

console.log(switchCase1(10));

function repeat(a){
    let result = "";
    for (let i = 0; i < 10; i++) {
        result += a;
       
    }
    return result;
}
console.log(repeat("a"));

function repeat1(a){
    let result = "";
    for (let i = 0; i < 10; i++) {
        result +=  "-" + a ;
    }
    return result.slice(1);
}
console.log(repeat1("a"));

function repeat2(a,n){
    let result = "";
    for (let i = 0; i <= n; i++) {
        result +=  "-" + a ;
    }
    return result.slice(1);
}
console.log(repeat2("a",5));

function sum1(){
    for (let i = 0; i <= 100; i++) {
        result ;
    }
    return i;
}
console.log(repeat2("a",5));