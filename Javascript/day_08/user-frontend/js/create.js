const API_URL = "http://localhost:8081/api/v1";

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

//Lấy danh sách tỉnh thành phố
const getDistrict = async () => {
  try {
    let res = await axios.get("https://provinces.open-api.vn/api/p/");
    renderDistrict(res.data);
  } catch (error) {
    console.log(error);
  }
};

const addressEl = document.getElementById("address");
const renderDistrict = (arr) => {
  let html = "";
  for (let i = 0; i < arr.length; i++) {
    const d = arr[i];
    html += `<option value="${d.name}">${d.name}</option>`;
  }
  addressEl.innerHTML = html;
};

//Tạo user mới
btnSave.addEventListener("click", async function () {
  try {
    let res = await axios.post(`${API_URL}/users`, {
      name: nameEl.value,
      email: emailEl.value,
      phone: phoneEl.value,
      address: addressEl.value,
      password: passwordEl.value,
    });
    if (res.data) {
      window.location.href = "/";
    }
  } catch (error) {
    alert(error.response.data.message);
  }
});
getDistrict();
