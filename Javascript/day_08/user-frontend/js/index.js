const API_URL = "http://localhost:8081/api/v1";

let users = [];

// API lấy đanh sách user + tìm kiếm
const getUsersAPI = (keyword = "") => {
  let url = `${API_URL}/users`;
  if (keyword) {
    url = `${API_URL}/users/search?name=${keyword}`;
  }
  return axios.get(url);
};

//Hàm gọi API
const getUsers = async (keyword) => {
  try {
    let res = await getUsersAPI(keyword);

    users = res.data;
    renderUsers(users);
    console.log(res);
  } catch (error) {
    console.log(error);
  }
};

//Render user
const tableContentEl = document.querySelector("tbody");
const searchEl = document.querySelector(".btn-success");

const renderUsers = (arr) => {
  tableContentEl.innerHTML = "";

  let html = "";

  for (let i = 0; i < arr.length; i++) {
    const u = arr[i];
    html += `<tr>
        <td>${i + 1}</td>
        <td>${u.name}</td>
        <td>${u.email}</td>
        <td>${u.phone}</td>
        <td>${u.address}</td>
        <td>
            <a href="./detail.html?id=${
              u.id
            }" class="btn btn-success">Xem chi tiết</a>
            <button class="btn btn-danger" onclick="deleteUser(${
              u.id
            })">Xóa</button>
        </td>
    </tr>`;
  }
  tableContentEl.innerHTML = html;
};

//Search user
searchEl.addEventListener("keydown", function (event) {
  if (event.keyCode == 13) {
    let value = event.target.value;

    getUsers(value);
  }
});

//Xóa user
const deleteUser = async (id) => {
  try {
    // Hỏi trước khi xóa
    let isConfirm = confirm("Bạn có muốn xóa không");
    if (isConfirm) {
      //Gọi API xóa
      axios.delete(`${API_URL}/users/${id}`);

      // Xóa trong mảng ban đầu
      users.forEach((user, index) => {
        if (user.id == id) {
          user.splice(index, 1);
        }
      });
      //Hiển thị lại trên giao diện
      renderUsers(users);
    }
  } catch (error) {
    console.log(error);
  }
};

getUsers();
