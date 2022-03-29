//Array

// let arr = [];
// arr[0] = "hello";
// arr[1] = 10;
// arr[2] = true;

// console.log(arr);

// let numbers = [1,2,3,4,5];

// // in ra các phần tử array
// for (let i = 0; i < numbers.length; i++) {
//     console.log(numbers[i]);  
// }

// // in ra phần tử của array
// numbers.forEach(function(value,index){
//     console.log(`${value}-${index}`);
// });

// // in ra phần tử của array sử dụng arrow function
// numbers.forEach((value,index) => {
//     console.log(`${value}-${index}`);
// });


// //Tính tổng
// let total = 0;
// for (let i = 0; i < numbers.length; i++) {
//     total += numbers[i];
// }
// console.log(total);


// let arr1 = [1,2,3,10,12];
// let arr2 = [1,2,3];
// let text = "1,2,3";

// console.log(arr1 == arr2);// false
// console.log(arr1 != arr2);// true
// console.log(arr1 == text);// true
// console.log(arr1.sort() == arr1)// true

// console.log(arr1.sort());
// console.log(arr1.sort((a,b) => a-b));

// map
let numbers = [1,2,3,4];

// let newArr =[];
// for (let i = 0; i < numbers.length; i++) {
//     newArr[i] = numbers[i] * 2;
    
// }

let newArr2 = numbers.map(ele => ele * 2);

console.log(newArr2);

// filter =  for + if
// let isOdd = ele => ele % 2 == 1;
// let arrOdd = numbers.filter(isOdd);

let arrOdd = numbers.filter(ele => ele % 2 == 1);
console.log(arrOdd);

// find : for + if + break
// Giá trị đầu tiên tìm thấy
let firstOddNumber =  numbers.find(ele => ele % 2 == 1)
console.log(firstOddNumber);

//findIndex
// Chỉ số của giá trị đầu tiền tìm thấy
let indexFirstOddNumber =  numbers.findIndex(ele => ele % 2 == 1)
console.log(indexFirstOddNumber);

// some()
// Chỉ 1 phần tử t/m điều kiện =>true
// Không có phần tử nào t/m => false
console.log(numbers.some(ele => ele > 5));
console.log(numbers.some(ele => ele >= 4));

// every()
// Chỉ 1 phần tử không t/m điều kiện => false
// Tất cả phần tử t/m => true
console.log(numbers.some(ele => ele > 1));
console.log(numbers.some(ele => ele > 0));

// Bài 1
function findMax(arr){
    arr.sort((a , b) => a-b);
    for (let i = arr.length; i > -1; i--) {
        return arr[arr.length-1];
    }
}

console.log(findMax([3,2,6,1,4,5,2]))

// Bài 2
function findMin(arr){
    arr.sort((a , b) => b-a);
    for (let i = arr.length-1; i > 0; i--) {
        return arr[arr.length-1];
    }
}
console.log(findMin([3,2,1,4,5,2,1]))

// Bài 3
function newArr3(arr){
    let oldArr = arr.map(ele => ele % 2);
    let newArr = [];
    for (let i = 0; i < arr.length; i++) {
        newArr[i] = oldArr[i];   
    }
    console.log(newArr);
}
newArr3([4,2,5,6,2,7]);

// Bài 4
// function repeatString(s){
// }
// repeatString('a');

