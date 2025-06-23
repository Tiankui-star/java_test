package software5.exercise6;

public class User {
	private String userName;
	private String userPwd;
	private String regDate;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public User() {
		;
	}
	public User(String un, String up, String rd) {
		this.userName = un;
		this.userPwd = up;
		this.regDate = rd;
	}
	//user:"userName,userPwd,regDate"
	public User(String user) {
		String[] suser = user.split(",");
		this.userName = suser[0].trim();
		this.userPwd = suser[1].trim();
		this.regDate = suser[2].trim();
	}
	
	public String toString() {
		return this.userName + "," + this.userPwd + "," + this.regDate;
	}
}
