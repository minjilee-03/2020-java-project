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
	
	String user_id = "���װ���";
	String user_pw;
	
	//DB �����ϴ� �޼���
	public void connectDB() {
		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connect OK");
			stmt = conn.createStatement();
		} catch(Exception e) {
			e.toString();
		}
	}
	
	
	//�α����ϴ� �޼���
	public boolean login(String name, String pwd) {
		boolean check_login = false;
		
		try {
			this.connectDB();
			sql = "select * from user where name='" + name + "';";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				if(rs.getString("pw").equals(pwd)) {//����� ������
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
	
	
	//�ߺ� üũ�ϴ� �޼���
	public int checkId(String name) {
		this.check = 1;
		try {
			this.connectDB();
			sql = "select * from user where name='" + name + "';";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) { //���� ���̵� ������ check�� ���� false�� �ٲ�
				if(rs.getString("name").equals(name)) {
					this.check = 2;
				} 
			}
			
		} catch (Exception e) {
			e.toString();
		}
		return this.check;
	}
	
	
	//ȸ�������ϴ� �޼���
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
			if (this.user_id != null) { //�α����� ȸ���� ��
				
				sql = "select id from user where name='" + this.user_id + "';";
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					u_id = rs.getInt("id");
					System.out.println(u_id);
				} // �ش� ���̵� ���ϱ�

				sql = "select score from user where name='" + this.user_id + "';";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					u_score = rs.getInt("score");
					System.out.println(u_score);
				} // �ش� ���� ���ϱ�

				sql = "select money from user where name='" + this.user_id + "';";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					u_money = rs.getInt("money");
					System.out.println(u_money);
				} // �ش� �� ���ϱ�

				//������ ����� �������� �̹��� ���� ������ �� ���� �� ��� ����, �׷��� ������ ��� ����X (=���� ���� ������ db�� �����Ѵٴ� ��)
				if (u_score < score) {
					sql = "UPDATE user SET score = " + score + " WHERE id = " + u_id + ";";
					System.out.println(sql);
					stmt.executeUpdate(sql);
				}

				// (������ �ִ� �� + ������ �ϸ鼭 ���� ��)�� ��� ����
				sql = "UPDATE user SET money = " + (u_money + money) + " WHERE id = " + u_id + ";";
				System.out.println(sql);
				stmt.executeUpdate(sql);
			}
			
		} catch (Exception e) {
			e.toString();
		}
	}
}
