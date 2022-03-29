// // Object

// let user = {
//     name: "Nguyễn Văn A",
//     age: 23,
//     email: "abc@gmail.com",

//     sayHello() {
//         console.log("Xin chào các bạn");
//     },

//     eat(food){
//         console.log(`Ăn món ${food}`);
//     }
// }

// console.log(user.name);
// console.log(user["age"]);
// user.sayHello();
// user.eat("phở");

// // Set lại giá trị
// user.name = "Ngô B";
// console.log(user);

// // Lặp Object
// let keys = Object.keys(user) // Trả về mảng các keys

// for (let i = 0; i < keys.length; i++) {
//     console.log(user[keys[i]]);
    
// }

// for (const key in user) {
//     console.log(user[key]);
// }

let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]

// 1. In ra thông tin các sản phẩm trong giỏ hàng theo cấu trúc sau
// Tên - Giá - Thương Hiệu - Số lượng
// Ví dụ : OPPO Find X3 Pro - 19500000 - OPPO - 3

function displayInfo(arr){
    arr.forEach(products => {
        console.log(`${products.name} - ${products.price} - ${products.brand} - ${products.count}`)
    });
}
displayInfo(products);


// 2. Tính tổng tiền tất cả sản phẩm trong giỏ hàng
// Tổng tiền mỗi sản phẩm = price * count

function calculatorTotalMoney(arr){
    let sum = 0;
    arr.forEach(products =>{
        sum += products.price *products.count
    })
    return sum;
}
console.log(calculatorTotalMoney(products));

// 3. Tìm các sản phẩm của thuơng hiệu "Apple"

function findByBrand(arr,brandName){
    return arr.filter(products => products.brand.toLowerCase() == brandName.toLowerCase())
}
console.log(findByBrand(products, "Apple"));

// 4. Tìm các sản phẩm có giá > 20000000

function findByPrice(arr,price){
    return arr.filter(products => products.price > price)
}

console.log(findByPrice(products,20000000))

// 5. Tìm các sản phẩm có chữ "pro" trong tên (Không phân biệt hoa thường)

function findByName(arr,name){
    return arr.filter(products => products.name.toLowerCase().includes(name.toLowerCase()))
}
console.log(findByName(products, "PRO"));

// 6. Thêm 1 sản phẩm bất kỳ vào trong mảng product

// 7. Xóa tất cả sản phẩm của thương hiệu "Samsung" trong giỏ hàng

// 8. Sắp xếp giỏ hàng theo price tăng dần

// 9. Sắp xếp giỏ hàng theo count giảm dần

// 10. Lấy ra 2 sản phẩm bất kỳ trong giỏ hàng
function displayInfo(arr){
    arr.forEach(products => {
        console.log(`${products.name} - ${products.price} - ${products.brand} - ${products.count}`)
    });
}
displayInfo(products);