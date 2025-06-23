package software5.exercise6;
import java.sql.*;
import java.util.ArrayList;

public class Login {
	public boolean login(User u) {
//文件判断
//		ArrayList<String> uList = new FileDAO("user.txt").readUserNameFromFile();
//		if(!uList.contains(u.getUserName())) {
//			System.out.print(u.getUserName() + "\" is not registered\"");
//			return false;
//		}
//		ArrayList<User> uList2 = new FileDAO("user.txt").readDataFromFile2();
//		for(User user:uList2) {
//			if(user.getUserName().equals(u.getUserName())
//					&& user.getUserPwd().equals(u.getUserPwd())) {
//				System.out.println("login success");
//				new FileDAO("login.txt").writeDataToFile(u.toString());
//				return true;
//			}
//		}
//		System.out.println("password is wrong");
//		return false;
		//数据库判断
		String url = "jdbc:mysql://localhost:3306/java_test?&useSSL=false&serverTimezone=UTC";
		String username = "root";
		String password = "Sun371502!";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			String sql="select * from Regist";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String db_user=rs.getString("username");
				String db_pwd = rs.getString("password");
				if(u.getUserName().equals(db_user)) {
					if(u.getUserPwd().equals(db_pwd)){
						System.out.println("login success");
					}
					else {
						System.out.println("password is wrong");
						return false;
					}
					return true;
				}
			}

		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("未找到注册信息");
		return false;
	}
}
