package src;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class newAccount {
	//변수선언
	private JFrame frame;
	private JTextField textID;
	private JButton checkIDbtn;
	private JTextField textPW;
	private JTextField textPW_2;
	private JButton joinbtn;
	private JLabel label_PW;
	private JLabel label_PW2;
	private JButton closeBtn;
	private JLabel backImg;
	private JLabel message;
	User u1 = new User();
	int checkId = 0; //0이면 중복 확인 안함, 1이면 사용 가능, 2면 중복

	

	
	//메인메서드
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newAccount window = new newAccount();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//생성자
	public newAccount() {
		initialize();
	}
	//GUI 설계메서드
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 717, 738);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//아이디 입력
		textID = new JTextField(); 
		textID.setBounds(112, 268, 352, 59);
		frame.getContentPane().add(textID);
		textID.setColumns(10);
		//중복확인 버튼
		checkIDbtn = new JButton(""); 
		checkIDbtn.setIcon(new ImageIcon("..\\image\\double_check.jpg"));
		checkIDbtn.setBounds(481, 268, 124, 51);
		frame.getContentPane().add(checkIDbtn);

		
		//비밀번호 입력
		textPW = new JTextField(); 
		textPW.setBounds(112, 360, 352, 59);
		frame.getContentPane().add(textPW);
		textPW.setColumns(10);
		//비밀번호 확인
		textPW_2 = new JTextField(); 
		textPW_2.setBounds(112, 448, 352, 59);
		frame.getContentPane().add(textPW_2);
		textPW_2.setColumns(10);
		//가입 버튼
		joinbtn = new JButton(""); 
		joinbtn.setIcon(new ImageIcon("..\\image\\create_account_cp.jpg"));
		joinbtn.setBounds(257, 558, 223, 59);
		frame.getContentPane().add(joinbtn);
		//"닉네임"
		JLabel label_ID = new JLabel("\uB2C9\uB124\uC784 "); 
		label_ID.setForeground(Color.WHITE);
		label_ID.setFont(new Font("HY바다M", Font.BOLD, 20));
		label_ID.setBounds(112, 245, 82, 21);
		frame.getContentPane().add(label_ID);
		//"비밀번호"
		label_PW = new JLabel("\uBE44\uBC00\uBC88\uD638"); 
		label_PW.setForeground(Color.WHITE);
		label_PW.setFont(new Font("HY바다M", Font.BOLD, 20));
		label_PW.setFont(new Font("HY바다M", Font.BOLD, 20));
		label_PW.setBounds(112, 339, 124, 21);
		frame.getContentPane().add(label_PW);
		//"비밀번호 확인"
		label_PW2 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uC7AC\uD655\uC778");
		label_PW2.setForeground(Color.WHITE);
		label_PW2.setFont(new Font("HY바다M", Font.BOLD, 20));
		label_PW2.setFont(new Font("HY바다M", Font.BOLD, 20));
		label_PW2.setBounds(112, 426, 230, 21);
		frame.getContentPane().add(label_PW2);
		//회원 가입 동의 체크박스
		JCheckBox agreeCheck = new JCheckBox("\uD68C\uC6D0\uAC00\uC785\uC5D0 \uB3D9\uC758\uD569\uB2C8\uB2E4.");
		agreeCheck.setFont(new Font("HY바다M", Font.BOLD, 18));
		agreeCheck.setBounds(112, 518, 352, 29);
		frame.getContentPane().add(agreeCheck);
		//닫는 버튼
		closeBtn = new JButton("");
		closeBtn.setIcon(new ImageIcon("..\\image\\close.jpg"));
		closeBtn.setBounds(652, 0, 43, 43);
		frame.getContentPane().add(closeBtn);
		
		//아이디는 8자 이내
		message = new JLabel("아이디는 8자 이내여야 합니다.");
		message.setBounds(180, 245, 284, 20);
		frame.getContentPane().add(message);
		
		//배경 이미지
		backImg = new JLabel(""); 
		backImg.setIcon(new ImageIcon("..\\image\\new_acount.jpg"));
		backImg.setBounds(0, 0, 695, 682);
		frame.getContentPane().add(backImg);
		
		
			
//===============================================================================

		
		//중복확인버튼 기능 구현
		checkIDbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newId = textID.getText();
				int num = u1.checkId(newId);//여기서 원래 오류나
				if(num==1) {
					System.out.println("사용 가능");
					message.setText("사용하실 수 있습니다.");
					checkId = 1;
				} else if(num==2){
					System.out.println("사용 불가");
					message.setText("중복되는 아이디입니다.");
					checkId = 2;
				}
			}
		});

		
		//회원 가입버튼 기능 구현
		joinbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newId = textID.getText();
				String newPw = textPW.getText();
				String newPw_2 = textPW_2.getText();
				if(checkId==1) u1.join(newId, newPw);
				else if(checkId==2) {
					System.out.println("사용불가");
					message.setText("중복되는 아이디입니다.");
				}
				else if(checkId==0) {
					message.setText("아이디 중복확인을 해주세요");
					System.out.println("중복확인plz");
				}
			}
		});
	}
}