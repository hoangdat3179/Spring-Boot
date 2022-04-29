// Thêm công việc
// Sửa công việc - tiêu đề & trạng thái
// Xóa công việc
// Lọc công việc theo trạng thái

const API_URL = "http://localhost:8080/api/v1";
let todos = [];
function getTodosAPI(status) {
  switch (status) {
    case "all": {
      return axios.get(`${API_URL}/todos`);
    }
    case "unactive": {
      return axios.get(`${API_URL}/todos?status=false`);
    }
    case "active": {
      return axios.get(`${API_URL}/todos?status=true`);
    }
    default: {
      return axios.get(`${API_URL}/todos`);
    }
  }
}

async function getTodos(status) {
  try {
    let res = await getTodosAPI(status);
    todos = res.data;

    renderTodos(res.data);
  } catch (error) {
    console.log(error);
  }
}

// Truy cập vào các thành phần
const todoListEl = document.querySelector(".todo-list");
function renderTodos(arr) {
  // Xóa hết nội dung đang có
  todoListEl.innerHTML = "";

  // Kiểm tra mảng có rỗng hay không
  if (arr.length == 0) {
    todoListEl.innerHTML = "Không có công việc nào trong danh sách";
    return;
  }

  // Sử dụng vòng lặp để render
  let html = "";
  for (let i = 0; i < arr.length; i++) {
    const t = arr[i];
    html += `<div class="todo-item" ${t.status ? "active-todo" : ""}">
                    <div class="todo-item-title">
                        <input type="checkbox" ${
                          t.status ? "checked" : ""
                        } onclick="toggleStatus(${t.id})"
                        />
                            <p>${t.title}</p>
                </div>
                <div class="option">
                <button class="btn btn-update" onclick="updateTitle(${t.id})">
                <img src="./img/pencil.svg" alt="icon">
        </button>
                     <button class="btn btn-delete" onclick="deleteTodo(${
                       t.id
                     })">
                <img src="./img/remove.svg" alt="icon" />
            </button>
        </div>
    </div>
    `;
  }

  todoListEl.innerHTML = html;
}

// Xóa công việc
async function deleteTodo(id) {
  try {
    // Gọi API xóa
    await axios.delete(`${API_URL}/todos/${id}`);

    // Xóa ở mảng ban đầu
    todos.forEach((todo, index) => {
      if (todo.id == id) {
        todos.splice(index, 1);
      }
    });

    // Sau khi xóa thì render lại giao diện
    renderTodos(todos);
  } catch (error) {
    console.log(error);
  }
}

const inputTodoEl = document.getElementById("todo-input");
const btnAdd = document.getElementById("btn-add");

// API thêm công việc
function createTodoAPI(title) {
  return axios.post(`${API_URL}/todos`, {
    title: title,
  });
}

// Hàm xử lý việc thêm
async function createTodo(title) {
  try {
    // Gọi API tạo todo
    const res = await createTodoAPI(title);

    // Khi có kết quả trả về từ server thì thêm vào trong mảng todos
    todos.push(res.data);

    // Render ra ngoài giao diện
    renderTodos(todos);
  } catch (error) {
    console.log(error);
  }
}

// Thay đổi trạng thái todo
async function toggleStatus(id) {
  try {
    // Tìm công việc tương ứng với id
    let todo = todos.find((todo) => todo.id == id);

    // Đổi lại trạng thái của công việc
    todo.status = !todo.status;

    // Gọi API cập nhật
    let res = await updateTodoAPI(todo);

    // Update lại status cho công việc trong mảng ban đầu
    todos.forEach((todo, index) => {
      if (todo.id == id) {
        todos[index] = res.data;
      }
    });

    // Render lại giao diện sau khi thay đổi trạng thái công việc
    renderTodos(todos);
  } catch (error) {
    console.log(error);
  }
}

const todo_option_item = document.querySelectorAll(".todo-option-item input");

// Lấy giá trị trong 1 ô input radio
function getOptionSelected() {
  for (let i = 0; i < todo_option_item.length; i++) {
    if (todo_option_item[i].checked) {
      return todo_option_item[i].value;
    }
  }
}

// Lắng nghe sự thay đổi của từng input radio, nếu xảy ra sự thay đổi nào về mặt lựa chọn thì gọi API để lấy dữ liệu và hiển thị lại
todo_option_item.forEach((btn) => {
  btn.addEventListener("change", function () {
    let optionSelected = getOptionSelected();
    getTodos(optionSelected);
  });
});

let isUpdate = false;
let idUpdate = null;
// Cập nhật tiêu đề todo
function updateTitle(id) {
  let title;

  // Tìm kiếm công việc muốn cập nhật và lưu lại giá trị title
  todos.forEach((todo) => {
    if (todo.id == id) {
      title = todo.title;
    }
  });

  // Đổi tên "THÊM" -> "CẬP NHẬT"
  btnAdd.innerText = "CẬP NHẬT";

  // Hiển thị tiêu đề cần cập nhật lên ô input
  inputTodoEl.value = title;
  inputTodoEl.focus();

  // Lưu lại id của công việc cần cập nhật và cho phép cập nhật
  idUpdate = id;
  isUpdate = true;
}

btnAdd.addEventListener("click", function () {
  // Lấy tiêu đề trong ô input
  let todoTitle = inputTodoEl.value;

  // Kiểm tra xem tiêu đề có trống hay không
  if (todoTitle == "") {
    alert("Nội dung không được để trống!");
    return;
  }

  // Nếu isUpdate == true thì cho phép cập nhật
  // Ngược lại isUpdate == false thì cho phép thêm
  if (isUpdate) {
    // Tìm công viêc có id = idUpdate
    let todo = todos.find((todo) => todo.id == idUpdate);

    // Sửa lại tiêu đề của công việc đó = nội dung trong ô input
    todo.title = todoTitle;

    // Thực hiện cập nhật
    updateTodo(todo);
  } else {
    // Thực hiện thêm công việc
    createTodo(todoTitle);
  }

  inputTodoEl.value = "";
});

// Cập nhật todo
function updateTodoAPI(todo) {
  return axios.put(`${API_URL}/todos/${todo.id}`, {
    title: todo.title,
    status: todo.status,
  });
}

async function updateTodo(todoUpdate) {
  try {
    // Gọi API cập nhật
    let res = await updateTodoAPI(todoUpdate);

    // Cập nhật lại trong mảng todos
    todos.forEach((todo, index) => {
      if (todo.id == todoUpdate.id) {
        todos[index] = res.data;
      }
    });

    // Thay đổi giao diện về ban đầu
    btnAdd.innerText = "Thêm";

    // Reset lại biến sau khi đã cập nhật thành công
    isUpdate = false;
    idUpdate = null;

    renderTodos(todos);
  } catch (error) {
    console.log(error);
  }
}

getTodos();
