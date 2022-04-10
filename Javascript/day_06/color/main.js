// const btnRandomName = document.getElementById("btn-random-name");
// const btnRandomHex = document.getElementById("btn-random-hex");
// const btnRandomRgb = document.getElementById("btn-random-rgb");

// Cách 1:
// btnRandomName.addEventListener("click", async function(){
//     try {
//         //Gọi API
//         let res = await axios.get("http://localhost:8081/random-color?type=1");
//         console.log(res);
//         // Thay đổi màu body
//         document.body.style.backgroundColor = res.data;

//     } catch (error) {
//         alert(error.response.data.message);
//     }
// })
// btnRandomHex.addEventListener("click", async function(){
//     try {
//         //Gọi API
//         let res = await axios.get("http://localhost:8081/random-color?type=2");
//         console.log(res);
//         // Thay đổi màu body
//         document.body.style.backgroundColor = res.data;

//     } catch (error) {
//         alert(error.response.data.message);
//     }
// })
// btnRandomRgb.addEventListener("click", async function(){
//     try {
//         //Gọi API
//         let res = await axios.get("http://localhost:8081/random-color?type=3");
//         console.log(res);
//         // Thay đổi màu body
//         document.body.style.backgroundColor = res.data;

//     } catch (error) {
//         alert(error.response.data.message);
//     }
// })

// Cách 2:
// const btns = document.querySelectorAll("button");
// const colorNameEl = document.getElementById("color-name");
// btns.forEach((btn , index) => {
//     btn.addEventListener("click", async function(){
//         try {
//             //Gọi API
//             let res = await axios.get(`http://localhost:8081/random-color?type=${index + 1}`);
            
//             //Hiển thị tên color
//             colorNameEl.innerText = res.data;
//             // Thay đổi màu body
//             document.body.style.backgroundColor = res.data;
            
//         } catch (error) {
//             alert(error.response.data.message);
//         }
//     })
// })

// Cách 3:
const btns = document.querySelectorAll("button");
const colorNameEl = document.getElementById("color-name");
btns.forEach((btn) => {
    btn.addEventListener("click", async function(){
        try {
            //Đọc type của phần tử
            let type = btn.dataset.type;

            //Gọi API
            let res = await axios.get(`http://localhost:8080/random-color?type=${type}`);
            
            //Hiển thị tên color
            colorNameEl.innerText = res.data;
            // Thay đổi màu body
            document.body.style.backgroundColor = res.data;
            
        } catch (error) {
            alert(error.response.data.message);
        }
    })
})
