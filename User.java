package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class User {
	String url = "jdbc:mysql://localhost:3307/schoolrun?serverTimezone = UTC";
	String driver = "com.mysql.cj.jdbc.Driver";
	String id = "root";
	String pw = "1234";
	String sql;
	int check = 0;
	Connection conn;
	Statement stmt;
	//DB 연결하는 메서드
	public void connectDB() {
		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connect OK");
			stmt = conn.createStatement();
		} catch(Exception e) {
			e.toString();
		}
	}
	
	
	//로그인하는 메서드
	public boolean login(String name, String pwd) {
		boolean check_login = false;
		
		try {
			this.connectDB();
			sql = "select * from user where name='" + name + "';";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				if(rs.getString("pw").equals(pwd)) //비번이 같으면
					check_login = true;
			}
			
		} catch (Exception e) {
			e.toString();
		}
		return check_login;
	}
	
	
	//중복 체크하는 메서드
	public int checkId(String name) {
		this.check = 1;
		try {
			this.connectDB();
			sql = "select * from user where name='" + name + "';";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) { //같은 아이디가 있으면 check의 값이 false로 바뀜
				if(rs.getString("name").equals(name)) {
					this.check = 2;
				} 
			}
			
		} catch (Exception e) {
			e.toString();
		}
		return this.check;
	}

	
	//회원가입하는 메서드
	public void join(String name, String pwd) {
		try {
			this.connectDB();
			sql = "insert into user(name, pw) value('" + name + "', '" + pwd + "');";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.toString();
		}
	}
	
	
	
	
}
