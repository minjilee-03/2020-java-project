package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Main_login extends JFrame {


	private JTextField id;
	private JLabel result, back_label;
	private JPasswordField pwd;
	private JButton loginbtn, joinbtn, guestbtn;
	private JButton button_1;
	private JFrame frame;
	
	User u1 = new User(); // 로그인 클래스
	private JLabel label;

	//메인
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_login window = new Main_login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//생성자
	public Main_login() {
		initialize();
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1500, 850);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(
				"\uC544\uC774\uB514(*\uBAA8\uB4E0 \uC544\uC774\uB514\uB294 \uB2C9\uB124\uC784)");
		lblNewLabel.setBounds(530, 370, 316, 46);
		frame.getContentPane().add(lblNewLabel);

		id = new JTextField(); // 아이디 텍스트필드
		id.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		id.setBounds(530, 411, 286, 57);
		frame.getContentPane().add(id);
		id.setColumns(10);

		JLabel pw_label = new JLabel("비밀번호"); // 비밀번호 라벨
		pw_label.setBounds(530, 468, 147, 21);
		frame.getContentPane().add(pw_label);

		loginbtn = new JButton(""); // 로그인 버튼
		loginbtn.setIcon(new ImageIcon("..//image//btn_image//login.jpg"));
		loginbtn.setFocusPainted(false);

		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u_id = id.getText();
				String u_pw = String.valueOf(pwd.getPassword());

				if (u1.login(u_id, u_pw)==true) {
					frame.setVisible(false);
					Main_start m1 = new Main_start();
					m1.main(null);
				} else {
					result.setText("아이디 또는 비밀 번호가 일치하지 않습니다.");
					result.setForeground(Color.red);
				}
			}
		});
		
		loginbtn.setBounds(831, 411, 136, 136);
		frame.getContentPane().add(loginbtn);

		pwd = new JPasswordField(); // 비밀번호
		pwd.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 30));
		pwd.setBounds(530, 490, 286, 57);
		frame.getContentPane().add(pwd);

		joinbtn = new JButton("");
		joinbtn.setFocusPainted(false);
		joinbtn.setIcon(new ImageIcon("..//image//btn_image//new_count.jpg"));
		joinbtn.setBounds(530, 562, 136, 34);
		frame.getContentPane().add(joinbtn);
		joinbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main_join m1 = new Main_join();
				m1.main(null);
			}
		});
		

		guestbtn = new JButton(""); // 게스트모드
		guestbtn.setFocusPainted(false);
		guestbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Main_start ms = new Main_start();
				ms.main(null);
			}
		});
		
		guestbtn.setIcon(new ImageIcon("..//image//btn_image//guest_bt.jpg"));
		guestbtn.setBounds(683, 562, 136, 34);
		frame.getContentPane().add(guestbtn);
						
		result = new JLabel("\t\t\t\t\uB85C\uADF8\uC778\uC744 \uD574\uC8FC\uC138\uC694!"); // 로그인결과
		result.setForeground(Color.WHITE);
		result.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 13));
		result.setBounds(530, 611, 361, 34);
		frame.getContentPane().add(result);							
		result.setOpaque(false);
						
						label = new JLabel("");
						label.setIcon(new ImageIcon("..//image//back_image//check_login_im.jpg"));
						label.setOpaque(true);
						label.setForeground(Color.RED);
						label.setFont(new Font("맑은 고딕", Font.BOLD, 14));
						label.setBounds(530, 611, 361, 34);
						frame.getContentPane().add(label);
				
						back_label = new JLabel(""); // 배경
						back_label.setIcon(new ImageIcon("..//image//back_image//start_background.jpg"));
						back_label.setBounds(0, 0, 1478, 794);
						frame.getContentPane().add(back_label);
	}
}
