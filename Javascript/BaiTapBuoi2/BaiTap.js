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
];

// 6. Thêm 1 sản phẩm bất kỳ vào trong mảng product
function addProduct() {
  return products.push({
    name: "Samsung S8+",
    price: 15000000,
    brand: "Samsung",
    count: 3,
  });
}
addProduct(products);
console.log(products);
// 7. Xóa tất cả sản phẩm của thương hiệu "Samsung" trong giỏ hàng
function removeProduct(arr) {
  let valueRemove = "Samsung";
  return arr.filter((products) => !valueRemove.includes(products.brand));
}
console.log(removeProduct(products));

// 8. Sắp xếp giỏ hàng theo price tăng dần
function sortPrice(arr) {
    return arr.sort((a , b) => a.price-b.price);
  }
console.log(sortPrice(products));

// 9. Sắp xếp giỏ hàng theo count giảm dần
function sortCount(arr) {
    return arr.sort((a , b) => b.count-a.count);
  }
console.log(sortCount(products));

// 10. Lấy ra 2 sản phẩm bất kỳ trong giỏ hàng
function randomProduct(arr){
    return arr[Math.floor(Math.random()*arr.length)];
}
console.log(randomProduct(products));
