const btn1 =document.getElementById("Get");
const btn2 =document.getElementById("Post");
const bmi = document.getElementById("bmiResult");

const btns = document.querySelectorAll("button");
btns.forEach((btn) => {
    btn.addEventListener("click", async function(){
        try {
            let height = btn.dataset.height;
            let weight = btn.dataset.weight;
            //Gọi API
            let res = await axios.get(`http://localhost:8080/bmi?height=${height}&weight=${weight}`);
            
            bmi.innerText = res.data;           
        } catch (error) {
            alert(error.response.data.message);
        }
    })
})


