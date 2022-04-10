let money = 43;
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
async function buy(){
    try {
        let res = await buyIphone();
        console.log(res);
        let res1 = await buyAirPod();
        console.log(res1);
        
    } catch (error) {
        console.log(error);
    }
    return "Đi nhậu";
    
}

buy()
    .then(res => console.log(res))
    .catch(err => console.log(err))