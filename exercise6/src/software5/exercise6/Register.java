package software5.exercise6;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Register {
	//private User user;
	public boolean register(User u) {

		//!文件判断
		if(!new UserValidation().validate2(u.getUserName())) {

			System.out.println("username is not illegal");
			return false;
		}
		if(!new PasswordValidation().validate2(u.getUserPwd())) {
			System.out.println("password is not illegal");
			return false;
		}
//		ArrayList<String> uList = new FileDAO("user.txt").readUserNameFromFile();
//		if(uList.contains(u.getUserName())) {
//			System.out.println("userword is exist");
//			return false;
//		}
//		new FileDAO("user.txt").writeDataToFile(u.toString());
//
//		return true;

		//数据库判断
		String url = "jdbc:mysql://localhost:3306/java_test?&useSSL=false&serverTimezone=UTC";
		String username = "root";
		String password = "Sun371502!";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Successful location");

			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Successful connection");

			String sql = "select * from Regist";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String u_name= rs.getString("username");
				String u_pass = rs.getString("password");
				if(u_name.equals(u.getUserName())) {
					System.out.println("userword is exist");
					return false;
				}
			}

			rs.close();
			statement.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return true;

	}
}
