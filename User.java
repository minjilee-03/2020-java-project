package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	String url = "jdbc:mysql://localhost:3307/schoolrun?serverTimezone = UTC";
	String driver = "com.mysql.cj.jdbc.Driver";
	String id = "root";
	String pw = "1234";
	String sql;
	int check = 0;
	Connection conn;
	Statement stmt;
	
	String user_id = "반항감자";
	String user_pw;
	
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
				if(rs.getString("pw").equals(pwd)) {//비번이 같으면
					check_login = true;
					this.user_id = name;
					this.user_pw = pwd;
				}
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
	
	public void intoResult(int score, int money) {
		int u_id = 0;
		int u_score = 0;
		int u_money = 0;
		
		try {
			this.connectDB();
			if (this.user_id != null) { //로그인한 회원일 때
				
				sql = "select id from user where name='" + this.user_id + "';";
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					u_id = rs.getInt("id");
					System.out.println(u_id);
				} // 해당 아이디 구하기

				sql = "select score from user where name='" + this.user_id + "';";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					u_score = rs.getInt("score");
					System.out.println(u_score);
				} // 해당 점수 구하기

				sql = "select money from user where name='" + this.user_id + "';";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					u_money = rs.getInt("money");
					System.out.println(u_money);
				} // 해당 돈 구하기

				//기존에 저장된 점수보다 이번에 나온 점수가 더 높을 때 디비에 저장, 그렇지 않으면 디비에 저장X (=가장 높은 점수를 db에 저장한다는 뜻)
				if (u_score < score) {
					sql = "UPDATE user SET score = " + score + " WHERE id = " + u_id + ";";
					System.out.println(sql);
					stmt.executeUpdate(sql);
				}

				// (기존에 있는 돈 + 게임을 하면서 얻은 돈)을 디비에 저장
				sql = "UPDATE user SET money = " + (u_money + money) + " WHERE id = " + u_id + ";";
				System.out.println(sql);
				stmt.executeUpdate(sql);
			}
			
		} catch (Exception e) {
			e.toString();
		}
	}
}
