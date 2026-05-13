# Checklist: Tạo Test Script Mới

Bản checklist này cung cấp hướng dẫn từng bước để tạo một test case tự động mới trong dự án này, đảm bảo tính nhất quán với các pattern và best practice hiện có.

## 1. Quản lý Hằng số (Constants)
- [ ] **Liên kết Điều hướng (Navigation Links)**: Nếu test liên quan đến một trang mới, hãy thêm path/URL của nó vào `HeaderNavigationConstants.java`. Đảm bảo giá trị khớp chính xác với thuộc tính `href` trên website (ví dụ: `/login`, `/view_cart`).
- [ ] **Kết quả Kỳ vọng/Nhãn (Expected Results/Labels)**: Thêm bất kỳ văn bản cố định, thông báo thành công hoặc tiêu đề nào vào `TestDataConstants.java`.
- [ ] **Cấu hình (Configuration)**: Nếu có cài đặt mới theo môi trường, hãy thêm vào `config.properties` và định nghĩa key trong `FrameworkConstants.java`.

## 2. Page Object Model (POM)
- [ ] **Page Class Mới**: Tạo một class mới trong `src/main/java/com/demo/pages/` nếu chưa tồn tại.
- [ ] **Kế thừa (Inheritance)**: Đảm bảo class page kế thừa từ `CommonPage` (để truy cập header/footer) hoặc `BasePage`.
- [ ] **Bộ định vị (Locators)**:
    - [ ] Sử dụng tên mô tả kèm tiền tố (ví dụ: `btnSubmit`, `txtUsername`, `lblErrorMessage`).
    - [ ] Sử dụng định dạng tiền tố tiêu chuẩn: `id=`, `css=`, `xpath=`.
    - [ ] Ưu tiên dùng `data-qa` attribute, `id` hoặc `name` thay vì các XPath phức tạp.
    - [ ] Sử dụng `%s` cho các dynamic locator (ví dụ: `xpath=//div[text()='%s']`).
- [ ] **Phương thức (Methods)**:
    - [ ] Thêm annotation `@Step` từ Allure cho mọi public method.
    - [ ] Sử dụng các method của `BasePage`: `click()`, `enterText()`, `isElementVisible()`, v.v.
    - [ ] Giữ cho các method tập trung vào các hành động đơn lẻ hoặc các bước logic có ý nghĩa.
- [ ] **Page Factory**: Đăng ký trang mới trong `PageFactoryManager.java` để khởi tạo dễ dàng.

## 2. Dữ liệu Kiểm thử (Test Data - Tùy chọn)
- [ ] **Dữ liệu CSV**: Nếu test theo dạng data-driven:
    - [ ] Tạo/Cập nhật file CSV trong `src/test/resources/data/`.
    - [ ] Định nghĩa đường dẫn CSV và Test Case ID trong `TestDataConstants.java`.
- [ ] **Dữ liệu Tĩnh (Static Data)**: Đối với các trường hợp đơn giản, sử dụng hằng số trong `TestDataConstants.java`.

## 4. Tạo Test Script
- [ ] **Test Class**: Tạo hoặc cập nhật một class trong `src/test/java/com/demo/tests/`.
- [ ] **Kế thừa (Inheritance)**: Đảm bảo class kế thừa từ `BaseTest`.
- [ ] **Annotation**: Sử dụng `@Test` với `description` mô tả rõ ràng, và sử dụng tiếng anh để mô tả (ví dụ: `description = "TC009: Search Product"`).
- [ ] **Khởi tạo & Điều hướng (Init & Navigation)**:
    - [ ] Sử dụng `PageFactoryManager` để lấy các instance của page.
    - [ ] **Điều hướng mặc định**: Xác nhận rằng việc mở URL ứng dụng đã được xử lý tự động trong `BaseTest.setUp()`. Không gọi lại lệnh mở URL trong test script trừ khi cần chuyển hướng đặc biệt.
- [ ] **Kiểm tra (Assertions)**:
    - [ ] Sử dụng `Assert.assertTrue()` cho các điểm kiểm tra quan trọng.
    - [ ] Sử dụng `SoftAssert` khi cần nhiều xác minh không gây dừng test trong một kịch bản.
    - [ ] Cung cấp thông báo lỗi có ý nghĩa cho mỗi assertion.
- [ ] **Ghi chú (Comments)**: 
    - [ ] Sử dụng các comment đánh số để map các bước trong test script với manual test case và sử dụng tiếng anh để comment.
    - [ ] Đối với các page được khởi tạo trong test class thì thêm comment init pages, mỗi page một dòng riêng biệt, theo sau là khởi tạo page.

## 5. Xác minh & Chất lượng
- [ ] **Chạy Local**: Sau khi hoàn thiện, thực hiện chạy test bằng lệnh `mvn test -Dtest=ClassName#MethodName` và đảm bảo mọi thứ hoạt động bình thường.
- [ ] **Kiểm tra Log**: Đảm bảo đầu ra của `LogUtils` rõ ràng và hữu ích và thể hiện bằng tiếng anh.
- [ ] **Allure Report**: (Tùy chọn) Tạo và kiểm tra báo cáo Allure để đảm bảo các bước được tài liệu hóa đúng cách, và các step name là tiếng anh.
- [ ] **Không Hardcoding**: Tuyệt đối không hardcode URL, thông tin cá nhân (address, payment), hoặc các giá trị kiểm thử trong script. Sử dụng `TestDataConstants` hoặc CSV data.
- [ ] **Tối ưu hóa API**: Nếu test case tập trung vào tính năng cụ thể (như Checkout, Search) và bước tạo/xóa user đã có test case riêng, hãy sử dụng `UserAPI` trong `@BeforeMethod` hoặc khởi đầu test để Setup/Teardown dữ liệu nhằm tăng tốc độ thực thi.
- [ ] **Dọn dẹp Import**: Xóa bỏ toàn bộ các thư viện không sử dụng (unused imports) trước khi hoàn tất code.
- [ ] **Dữ liệu Ngẫu nhiên**: Sử dụng `RandomGenerator` cho các trường yêu cầu tính duy nhất (Email, Name, Mobile) để tránh xung đột dữ liệu khi chạy test nhiều lần.
