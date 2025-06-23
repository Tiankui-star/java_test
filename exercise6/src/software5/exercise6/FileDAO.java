package software5.exercise6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDAO {
	private File fUser;
	private String fName;
	//private Writer fw;
	//private BufferedWriter bw;
	//private Scanner sc;
	public File getfUser() {
		return fUser;
	}
	public void setfUser(File fUser) {
		this.fUser = fUser;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	public FileDAO() {
		;
	}
	public FileDAO(String fname) {
		this.fName = fname;
		this.fUser = new File(fname);
		// 如果文件不存在，则自动创建空文件
		if (!fUser.exists()) {
			try {
				fUser.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public boolean writeDataToFile(String data) {
		Writer fw = null;
		BufferedWriter bw = null;
		boolean b = false;
		try {
			//???
			fw = new FileWriter(this.fUser, true);
			bw = new BufferedWriter(fw);
			bw.write(data + "\n");
			bw.flush();
			b = true;
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}finally {
			try {
				if(fw!=null) {
					fw.close();
				}
				if(bw!=null) {
					bw.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	
	//String[]
	//User[]
	//ArrayList<User>
	public ArrayList<String> readDataFromFile(){
		ArrayList<String> uList = new ArrayList<String>();
		Scanner sc = null;
		try {
			sc = new Scanner(this.fUser);
			sc.useDelimiter("\n");
			while(sc.hasNext()) {
				uList.add(sc.next());
			}
		}catch(FileNotFoundException fe) {
			fe.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(sc!=null) {
				sc.close();
			}
		}
		return uList;
	}
	
	public ArrayList<User> readDataFromFile2(){
		ArrayList<User> uList = new ArrayList<User>();
		Scanner sc = null;
		try {
			sc = new Scanner(this.fUser);
			sc.useDelimiter("\n");
			while(sc.hasNext()) {
				//uList.add(sc.next());
				uList.add(new User(sc.next()));
			}
		}catch(FileNotFoundException fe) {
			fe.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(sc!=null) {
				sc.close();
			}
		}
		return uList;
	}
	
	public ArrayList<String> readUserNameFromFile(){
		ArrayList<String> uList = new ArrayList<String>();
		Scanner sc = null;
		try {
			sc = new Scanner(this.fUser);
			sc.useDelimiter("\n");
			while(sc.hasNext()) {
				uList.add(sc.next().split(",")[0]);
			}
		}catch(FileNotFoundException fe) {
			fe.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(sc!=null) {
				sc.close();
			}
		}
		return uList;
	}
}
