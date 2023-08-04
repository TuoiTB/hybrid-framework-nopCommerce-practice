package pageUI;

public class HomePageUI {
	//Contains locators/ elements of that page
	
	//Biến: Variable/Property
	public static final String REGISTER_LINK = "//a[@class='ico-register']";
		//public: để truy cập từ các class bên ngoài package theo cách thông thường
		//static: có thể truy cập từ phạm vi class- chứ không cần phạm vi từ object
		//final: không có phép ghi đè lên biến này nữa. Ngăn cản việc gán lại thành 1 element khác.
		//String: vì các By locator đều nhận tham số vào là String
		//Tên biến: khi kết hợp cùng static+ final thì mặc định Java sẽ hiểu đấy là hằng số-constant
			//tự động chuyển in đậm và in nghiêng. 
			//Hằng số trong Java: Bắt buộc viết hoa và phân tách bởi gạch nối (Convention trong Jave)
		//Gía trị của biến: nằm trong dấu nháy đôi
			//Là cú pháp xpath
	
	public static final String LOGIN_LINK = "//a[@class='ico-login']";
	
	public static final String MY_ACCOUNT_LINK = "//a[@class='ico-account']";
	
}
