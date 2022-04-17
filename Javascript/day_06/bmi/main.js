const btn1 = document.getElementById("Get");
const btn2 = document.getElementById("Post");
const bmi = document.getElementById("bmi-index");
const height = document.getElementById("height");
const weight = document.getElementById("weight");

btn1.addEventListener("click", async function () {
  try {
    //Gọi API
    let res = await axios.get(
      `http://localhost:9000/bmi?height=${height}&weight=${weight}`
    );
    bmi.innerText = res.data;
  } catch (error) {
    alert(error.response.data.message);
  }
});

btn2.addEventListener("click", async function () {
  try {
    //Gọi API
    let res = await axios.post("http://localhost:9000/bmi");
    bmi.innerText = res.data;
  } catch (error) {
    alert(error.response.data.message);
  }
});
