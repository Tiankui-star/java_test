package software5.exercise6;
import java.sql.*;
import java.time.LocalDate;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
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
		String Username;
		String Password;
		String dt = LocalDate.now().toString();
		String Salt;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			String sql="select * from Regist";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String db_user=rs.getString("username");
				String db_pwd = rs.getString("password");
				String saltBase64 = rs.getString("salt");
				byte[] storedSalt = Base64.getDecoder().decode(saltBase64);
				if(u.getUserName().equals(db_user)) {
					Username=u.getUserName();
					if(verifyPassword(u.getUserPwd(),db_pwd,storedSalt)){
						Password=db_pwd;
						Salt=saltBase64;
						System.out.println("login success");
						try {
							conn = DriverManager.getConnection(url, username,password);
							PreparedStatement pstmt = conn.prepareStatement(
									"INSERT INTO Login(username, password, login_date,salt) VALUES (?, ?, ?,?)");
							pstmt.setString(1, Username);
							pstmt.setString(2, Password);
							pstmt.setString(3, dt);
							pstmt.setString(4, Salt);
							int affectedRows = pstmt.executeUpdate();
						}catch (SQLException e) {
							e.printStackTrace();
						}
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
	private static final int SALT_LENGTH = 16; // 盐值长度(字节)
	private static final int ITERATIONS = 100000; // 迭代次数
	private static final int KEY_LENGTH = 256; // 密钥长度(位)
	private static final String ALGORITHM = "PBKDF2WithHmacSHA256"; // 算法
	public static String hashPassword(String password, byte[] salt) {
		try {
			PBEKeySpec spec = new PBEKeySpec(
					password.toCharArray(),
					salt,
					ITERATIONS,
					KEY_LENGTH
			);
			SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
			byte[] hash = factory.generateSecret(spec).getEncoded();
			return Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException("密码哈希失败", e);
		}
	}
	public static boolean verifyPassword(
			String inputPassword,
			String storedPassword,
			byte[] salt
	) {
		String hashedInput = hashPassword(inputPassword, salt);
		return hashedInput.equals(storedPassword);
	}
}
