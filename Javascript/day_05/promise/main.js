// Promise : Lời hứa

// Hứa : Cuối năm có đủ 40 củ thì mua con Iphone 14 Pro Max

let money = 44;
function buyIphone(){
    return new Promise(function(resolve,reject){
        if(money > 40){
            resolve("Mua Iphone thôi")
        }else {
            reject("Cày tiếp để đủ tiền mua")
        }
    })
}
// Hứa tiếp :  Mua Iphone còn tiền thì mua con Airpod (4 tr)
function buyAirPod(){
    return new Promise(function(resolve,reject){
        if(money - 40 - 4 >=0){
            resolve("Mua tiếp Airpod")
        }else {
            reject("Không đủ tiền mua airpod")
        }
    })
}
// buyIphone()
//     .then(res => {
//         console.log(res);
//         return buyAirPod();
//     })
//     .then(res => {
//         console.log(res);
//     })
//     .catch(err => {
//         console.log(err);
//     })
//     .finally(() =>{
//         console.log("Đi nhậu");
//     })

// buyIphone()
//     .then(res => {
//         console.log(res);
//     })
//     .catch(err => {
//         console.log(err);
//     })
// buyAirPod()
//     .then(res => {
//         console.log(res);
//     })
//     .catch(err => {
//         console.log(err);
//     })

Promise.all([buyIphone(),buyAirPod()])
    .then(res => console.log(res))
    .catch(err => console.log(err))