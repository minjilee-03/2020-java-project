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
	//��������
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
	int checkId = 0; //0�̸� �ߺ� Ȯ�� ����, 1�̸� ��� ����, 2�� �ߺ�

	

	
	//���θ޼���
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
	//������
	public newAccount() {
		initialize();
	}
	//GUI ����޼���
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 717, 738);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//���̵� �Է�
		textID = new JTextField(); 
		textID.setBounds(112, 268, 352, 59);
		frame.getContentPane().add(textID);
		textID.setColumns(10);
		//�ߺ�Ȯ�� ��ư
		checkIDbtn = new JButton(""); 
		checkIDbtn.setIcon(new ImageIcon("..\\image\\double_check.jpg"));
		checkIDbtn.setBounds(481, 268, 124, 51);
		frame.getContentPane().add(checkIDbtn);

		
		//��й�ȣ �Է�
		textPW = new JTextField(); 
		textPW.setBounds(112, 360, 352, 59);
		frame.getContentPane().add(textPW);
		textPW.setColumns(10);
		//��й�ȣ Ȯ��
		textPW_2 = new JTextField(); 
		textPW_2.setBounds(112, 448, 352, 59);
		frame.getContentPane().add(textPW_2);
		textPW_2.setColumns(10);
		//���� ��ư
		joinbtn = new JButton(""); 
		joinbtn.setIcon(new ImageIcon("..\\image\\create_account_cp.jpg"));
		joinbtn.setBounds(257, 558, 223, 59);
		frame.getContentPane().add(joinbtn);
		//"�г���"
		JLabel label_ID = new JLabel("\uB2C9\uB124\uC784 "); 
		label_ID.setForeground(Color.WHITE);
		label_ID.setFont(new Font("HY�ٴ�M", Font.BOLD, 20));
		label_ID.setBounds(112, 245, 82, 21);
		frame.getContentPane().add(label_ID);
		//"��й�ȣ"
		label_PW = new JLabel("\uBE44\uBC00\uBC88\uD638"); 
		label_PW.setForeground(Color.WHITE);
		label_PW.setFont(new Font("HY�ٴ�M", Font.BOLD, 20));
		label_PW.setFont(new Font("HY�ٴ�M", Font.BOLD, 20));
		label_PW.setBounds(112, 339, 124, 21);
		frame.getContentPane().add(label_PW);
		//"��й�ȣ Ȯ��"
		label_PW2 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uC7AC\uD655\uC778");
		label_PW2.setForeground(Color.WHITE);
		label_PW2.setFont(new Font("HY�ٴ�M", Font.BOLD, 20));
		label_PW2.setFont(new Font("HY�ٴ�M", Font.BOLD, 20));
		label_PW2.setBounds(112, 426, 230, 21);
		frame.getContentPane().add(label_PW2);
		//ȸ�� ���� ���� üũ�ڽ�
		JCheckBox agreeCheck = new JCheckBox("\uD68C\uC6D0\uAC00\uC785\uC5D0 \uB3D9\uC758\uD569\uB2C8\uB2E4.");
		agreeCheck.setFont(new Font("HY�ٴ�M", Font.BOLD, 18));
		agreeCheck.setBounds(112, 518, 352, 29);
		frame.getContentPane().add(agreeCheck);
		//�ݴ� ��ư
		closeBtn = new JButton("");
		closeBtn.setIcon(new ImageIcon("..\\image\\close.jpg"));
		closeBtn.setBounds(652, 0, 43, 43);
		frame.getContentPane().add(closeBtn);
		
		//���̵�� 8�� �̳�
		message = new JLabel("���̵�� 8�� �̳����� �մϴ�.");
		message.setBounds(180, 245, 284, 20);
		frame.getContentPane().add(message);
		
		//��� �̹���
		backImg = new JLabel(""); 
		backImg.setIcon(new ImageIcon("..\\image\\new_acount.jpg"));
		backImg.setBounds(0, 0, 695, 682);
		frame.getContentPane().add(backImg);
		
		
			
//===============================================================================

		
		//�ߺ�Ȯ�ι�ư ��� ����
		checkIDbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newId = textID.getText();
				int num = u1.checkId(newId);//���⼭ ���� ������
				if(num==1) {
					System.out.println("��� ����");
					message.setText("����Ͻ� �� �ֽ��ϴ�.");
					checkId = 1;
				} else if(num==2){
					System.out.println("��� �Ұ�");
					message.setText("�ߺ��Ǵ� ���̵��Դϴ�.");
					checkId = 2;
				}
			}
		});

		
		//ȸ�� ���Թ�ư ��� ����
		joinbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newId = textID.getText();
				String newPw = textPW.getText();
				String newPw_2 = textPW_2.getText();
				if(checkId==1) u1.join(newId, newPw);
				else if(checkId==2) {
					System.out.println("���Ұ�");
					message.setText("�ߺ��Ǵ� ���̵��Դϴ�.");
				}
				else if(checkId==0) {
					message.setText("���̵� �ߺ�Ȯ���� ���ּ���");
					System.out.println("�ߺ�Ȯ��plz");
				}
			}
		});
	}
}