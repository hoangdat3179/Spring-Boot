const API_URL = "http://localhost:8081/api/v1";


const params = new URLSearchParams(window.location.search);
const id = params.get("id");
const nameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const passwordEl = document.getElementById("password");
const btnSave = document.getElementById("btn-save");
const btnBack = document.querySelector(".btn-back");

btnBack.addEventListener("click", function () {
  //Điều hướng trong js
  window.location.href = "/";
});

// Gọi API
const getUser = async (id) => {
    try {
        let res = await axios.get(`${API_URL}/users/${id}`)
        renderUser(res.data)
        console.log(res)
    } catch (error) {
        console.log(error)
    }
}

// Render User
const renderUser = user => {
    nameEl.value = user.name;
    emailEl.value = user.email;
    phoneEl.value = user.phone;
    addressEl.value = user.address;
}


//Lấy danh sách tỉnh thành phố
const getDistrict = async () => {
    try {
      let res = await axios.get("https://provinces.open-api.vn/api/p/");
      renderDistrict(res.data);
    } catch (error) {
      console.log(error);
    }
  };

  //Hiển thị tỉnh thành phố
  const addressEl = document.getElementById("address");
const renderDistrict = (arr) => {
  let html = "";
  for (let i = 0; i < arr.length; i++) {
    const d = arr[i];
    html += `<option value="${d.name}">${d.name}</option>`;
  }
  addressEl.innerHTML = html;
};

const init = async () => {
    await getDistrict();
    await getUser(id);
}

init();