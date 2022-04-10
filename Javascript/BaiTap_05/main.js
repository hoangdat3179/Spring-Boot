const btn = document.getElementById("btn");
const image = document.getElementById("image");
const select = document.getElementById("breed-list");
const ul = document.querySelector("ul");
let arr = [];

// Vừa load trang phải gọi API để render danh sách breed
// API : https://dog.ceo/api/breeds/list/all

async function getBreedList() {
  try {
    // Gọi API để lấy danh sách giống loài
    let res = await axios.get("https://dog.ceo/api/breeds/list/all");
    renderBreed(res.data.message);
  } catch (error) {
    console.log(error.response.data.message);
  }
}

function renderBreed(breeds) {
  for (const key in breeds) {
    if (Object.hasOwnProperty.call(breeds, key)) {
      const option = document.createElement("option");
      option.innerText = key;
      select.appendChild(option);
    }
  }
}
getBreedList();

function renderSubBreed(breeds) {
  for (let i = 0; i < breeds.length; i++) {
    const li = document.createElement("li");
    li.innerHTML += breeds[i];
    li.onclick = async function(){
      try {
        let res = await axios.get(`https://dog.ceo/api/breeds/${select.value}/${li.value}/image/random`)
        image.src = res.data.message;
        } catch (error) {
            console.log(error.response.data.message);
        }
    }
    ul.appendChild(li);
  }
}

btn.addEventListener("click", async function () {
  ul.innerHTML = "";
  arr = [];
  let res = await axios.get(`https://dog.ceo/api/breeds/${select.value}/list`);
  renderSubBreed(res.data.message);
  if (ul.innerHTML == "") {
    const li = document.createElement("li");
    li.innerText = "Không có sub breed";
    ul.appendChild(li);
  }
});

