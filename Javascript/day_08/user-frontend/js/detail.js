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
      console.log(error); 
    }
}

// Render User
const renderUser = user => {
    nameEl.value = user.name;
    emailEl.value = user.email;
    phoneEl.value = user.phone;
  addressEl.value = user.address;
  
  // Avatar 
  if (!user.avatar) {
    avatarPreviewEl.src = 'https://via.placeholder.com/200';
  }
  else {
    avatarPreviewEl.src = `http://localhost:8081${user.avatar}`;
  }
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
// Doi mat khau 
const passOldEL = document.getElementById("old-password");
const passNewEl = document.getElementById("new-password");
const btnChangePass = document.getElementById("btn-change-password");
var myModal = new bootstrap.Modal(document.getElementById('modal-change-password'), {
  keyboard: false
})
btnChangePass.addEventListener('click', async function () {
  console.log(passOldEL.innerText);
  try {
        let res = await axios.put(`${API_URL}/users/${id}/change-password`,{
            oldPassWord : passOldEL.value,
            newPassWord:  passNewEl.value
        })   
        myModal.hide();
        } catch (error) {
          alert(error.response.data.message)
        }
})
// quen mat khau 
const btnForgot = document.querySelector("#btn-forgot-password");
btnForgot.addEventListener('click',async function () {
  try {
    let res = await axios.post(`${API_URL}/users/${id}/fogot-password`)
    alert("Mật khẩu của bạn là : " + res.data)
  }
  catch (error) {
    console.log(error)
    alert("Lấy mật khẩu mới không thành công")
}
})
// Upload file
const avataEl = document.getElementById('avatar'); 
const avatarPreviewEl = document.getElementById('avatar-preview'); 

const uploadFileAPI = file => {
  const formData = new FormData();
  formData.append('file', file);

  return axios.post(`${API_URL}/users/${id}/upload-file`, formData); 
}
avataEl.addEventListener('change', async function (event) {
  
  try {
    // lấy file cần upload 
    let file = event.target.files[0];
    let res = await uploadFileAPI(file);
    console.log(res);
    avatarPreviewEl.src = `http://localhost:8081${res.data}`;
  }
  catch (error) {
    
  }
})
// Chạy lấy tỉnh thành trước , sau đó lấy thông tin user
const init = async () => {
    await getDistrict();
    await getUser(id);
}
init()
