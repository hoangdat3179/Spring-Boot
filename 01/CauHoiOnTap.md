### Câu 1 : Trong quá trình tạo dự án Spring Boot chúng ta phải khai báo những tham số sau đây : groupID, artifactID. Ý nghĩa các tham số này là gì?
 #### groupID : 
 * Định nghĩa : là id của nhóm dự án , nó là duy nhất trong 1 tổ chức / công ty / cá nhân của dự án 
 * Sử dụng : Dùng để xác định nhóm dự án Maven
 ---
 #### artifactID : 
 * Định nghĩa : Là id của dự án , Nó định nghĩa tên của dự án 
 * Sử dụng : Dùng để xác định tên dự án 
 ---
### Câu 2 :  Tại sao phải đảo ngược tên miền trong <groupId>vn.techmaster</groupId>? 
 * Lý do phải đảo ngược tên miền trong <groupId>vn.techmaster</groupId> vì khi tìm kiếm tên miền trước ta sẽ có được tập hợp các dự án có cùng tên miền phục vụ cho việc tìm kiếm dễ dàng hơn.
---
### Câu 3 : SpringBoot có 2 cơ chế để quản lý thư viện. Hãy kể tên chúng? 
---
### Câu 4 : File pom.xml có tác dụng gì?
* File pom.xml là nơi khai báo tất cả những gì liên quan đến dự án được cấu hình qua maven, như khai báo các dependency, version của dự án, tên dự án, repossitory … 
---
### Câu 5 : Trong file pom.xml có các thẻ dependency. Ý nghĩa của chúng là gì? 
* Ý nghĩa dùng để khai báo các thư viện cần sử dụng trong dự án Maven 
---
### Câu 6 : Ý nghĩa của @Controllerlà gì?
* @Controller chỉ ra rằng lớp được chú thích là một bộ điều khiển. Nó là một chuyên môn hóa của @Component và được tự động phát hiện thông qua quét classpath. Nó thường được sử dụng kết hợp với các phương thức xử lý chú thích dựa trên chú thích @RequestMapping.
* 1 class được đánh dấu là controller thì để khai báo class đó là 1 controller có nhiệm vụ RequestMapping trên url cvafo các method tuwowgn ưng trong Controller 
--- 
### Câu 7 : Ý nghĩa của @RequestMapping là gì? Nó có những tham số gì ngoài value? 
#### @RequestMapping có nhiệm vụ ánh xạ các request (Yêu cầu ) người dùng nhập vào method tương ưng Controller , Khi ta đưa vào URL là http://localhost:8080/method2 thì nó sẽ được xử lý bởi phương thức là public String method2().
* value : Chỉ ra các action được ánh xạ với phương thức xử lý 
* method : - Chỉ ra phương thức truyền dữ liệu của trình duyệt web (POST ,GET , PUT , DELETE)
* Mặc định khi bạn không định nghĩa thuộc tính method thì request method sẽ là GET , Nếu sử dụng các method khác thì sẽ lỗi 405(Lỗi này sẽ xuất hiện khi người dùng nhập phương thức sai )
* params : Chỉ ra tham số bắt buộc phải có để thực hiện action 
* header: Khi chỉ rõ header trong @requestMapping thì nó sẽ chỉ xử lý những request có header chứa các tham số đã chỉ rõ 
* Consumes : Chỉ chấp nhận các request có content-type giống với giá trị khai báo bên trong cusumes 
* Produces : Kiểu trả về dữ liệu , cái này thường chỉ dùng với các REST_API
--- 
### Câu 8 : Ý nghĩa của @RequestResponse khi đặt trong hàm hứng request để làm gì? 
--- 
### Câu 9 : Hãy trả lời khi nào thì dùng @PathVariable và khi nào nên dùng @RequestParam ? 
#### @PathVariable : 
- Là annotation để lấy giá trị tham số là 1 thành phần trong đường dẫn 
- Trích xuất dữ liệu từ request URL 
- @PathVariable thì nó chỉ có gái trị thuộc trính cho mẫu URL . Trong 1 method có thể sử dụng nhiều @PathVariable để đón các dữ liệu khác nhau trên URL , nhưng ko đc nhiều method cùng chung URL patern 
-@PathVariable có thể hoán đổi đc vị trí trong thành phần đường dẫn 
#### @RequestParam : 
- Là annotation dùng để lấy giá trị của pramaters trên url (Kiểu query string )
- @RequestParam Có 4 tham số hỗ trợ : 
  + defaultValue : Dây là giá trị mặc định nếu như gái trị của parameters trên URL rỗng   
  + name : Tên của parameters binding
  + required : Cho biết tham số này là có bắt buộc hay không , nếu required = true thì thiếu parameters đó request sẽ fail 
  + value : Đây là alias cho tên của thuộc tính 
  --- 
### Câu 10 : Thứ tự các thành phần đường dẫn @PathVariable có thể hoán đổi được không? 
* @PathVariable có thể hoán đổi đc vị trí trong thành phần đường dẫn 
--- 
### Câu 11 : @GetMapping khác gì so với @PostMapping?
#### @GetMapping : 
- Là 1 phiên bản chuyên biệt của @RequestMapping 
- Là phương thức được chú thích trong các lớp @Controller xử lý các yêu cầu HTTP GET phù hợp với biểu thức URL nhất định 
#### @PostMapping ; 
- Là 1 phiên bản chuyên biệt của @RequestMapping 
- Là phương thức được chú thích trong các lớp @Controller xử lý các yêu cầu HTTP POST phù hợp với URL nhất định 
--- 
### Câu 12 : Trong các annotation @RequestMapping, @GetMapping, @PostMapping… có tham số produces = MediaType.XXXX ý nghĩa tham số này là gì? 
- Produces : Kiểu trả về dữ liệu , cái này thường chỉ dùng với các REST_API
--- 
### Câu 13 : Giải thích ý nghĩa của @RequestBody trong đoạn code dưới đây ?
@PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Message echoMessage(@RequestBody Message message){
    return message;
}
- Là 1 annotation ánh xạ dữ liệu từ client lên server 
--- 
### Câu 14 : Cổng mặc định ứng dụng SpringBoot là 8080. Hãy google cách để thay đổi cổng lắng nghe mặc định?
- Mặc định khi khơi động thì sẽ chạy port 8080 
- Chúng ta có thể thay đổi điều này bằng cách là điều chỉnh giá trị server.port trong application.properties 