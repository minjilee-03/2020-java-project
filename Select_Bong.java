package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.CookieInfo;
import Game.CookieRun;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
//ĳ������ �г��� ���� â

public class Select_Bong extends JFrame {
	//CookieInfo c1= new CookieInfo(); ĳ���� �̹��� �� �����ϴ� Ŭ����
	
	public Select_Bong() {
		JPanel p = new JPanel();
		p.setBackground(new Color(255, 250, 205));
		JButton backbtn = new JButton(""); 
		backbtn.setIcon(new ImageIcon("..//image//btn_image//back_btn.jpg"));
		JButton g1 = new JButton("");
		g1.setIcon(new ImageIcon("..\\image\\game_image\\bong_1.gif"));
		JButton g2 = new JButton("");
		g2.setIcon(new ImageIcon("..\\image\\game_image\\bong_2.gif"));
		JButton g3 = new JButton("");
		g3.setIcon(new ImageIcon("..\\image\\game_image\\bong_3.gif"));
		getContentPane().add(p); //�����ӿ� �ǳ� ���̱�
		p.setLayout(null);
		p.add(backbtn);
		p.add(g1);
		p.add(g2);
		p.add(g3);
		
		backbtn.setBounds(0, 0, 196, 57);
		g1.setBounds(150, 200, 300, 400);
		g2.setBounds(550, 200, 300, 400);
		g3.setBounds(950, 200, 300, 400);
		
		//�ؿ� �ִ°� ��ư Ŭ���� �̹��� �����ϴ� �ڵ�
		
		/*g1.addActionListener( new ActionListener() { //c_btn�� ������ �����ϴ� �޼���
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String cr1 = "..\\image\\game_image\\seori_1.gif"; //�⺻
				String cr2 = "..\\image\\game_image\\seori_1_jump.png"; //����
				String cr3 = "..\\image\\game_image\\seori_1_jump2.gif"; //��������
				String cr4 = "..\\image\\game_image\\seori_1_fall.png"; //����
				String cr5 = "..\\image\\game_image\\seori_1_slide.png"; //�����̵�
			
				c1.setCharacter(cr1, cr2, cr3, cr4, cr5);
			}
		});*/
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("..\\image\\grade_back.jpg"));
		lblNewLabel.setBounds(-12, -46, 1500, 850);
		p.add(lblNewLabel);
		
		setTitle("SchoolRun");
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //x�� ������ ������ �Ѵٴ� ��(SWING�� �̰� ��� ����)
		setSize(1500,850);
		setVisible(true);
		
		backbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SelectCT(); //ĳ����, �׸� �߿� ������ ���� ����
			}
		});
	}
}