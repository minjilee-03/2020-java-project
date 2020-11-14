package src;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Game.CookieRun;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//테마 고르는창
public class Main_lobi extends JFrame implements ActionListener {
	private JButton btn;
	Music introMusic = new Music("..\\music\\LOBI_BGM.MP3", true);//뮤직
	public Main_lobi() {
		getContentPane().setLayout(null);
		JPanel p = new JPanel();
		p.setBounds(0, 0, 0, 0);
		JButton backbtn = new JButton(""); 
		backbtn.setIcon(new ImageIcon("..\\image\\back_btn.jpg"));
		getContentPane().add(p);
		p.setLayout(null);
		p.add(backbtn);
		
		backbtn.setBounds(0, 743, 196, 51); 
	
		JButton button = new JButton("");
		button.setBorderPainted(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				introMusic.close();
				setVisible(false);
				SelectItem cr = new SelectItem();
				cr.setVisible(true);
					
			}
		});
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				introMusic.close();
				setVisible(false);
				Login l1 = new Login();
				l1.setVisible(true);
			}
		});
		button_1.setIcon(new ImageIcon("..\\image\\logout.jpg"));
		button_1.setBounds(0, 749, 184, 45);
		getContentPane().add(button_1);
		button.setIcon(new ImageIcon("..\\image\\start_btm.jpg"));
		button.setBounds(937, 580, 505, 128);
		getContentPane().add(button);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("..\\image\\main_back.jpg"));
		label_1.setBounds(0, 0, 1478, 794);
		getContentPane().add(label_1);
		
		btn = new JButton();
		btn.setBorderPainted(false);
		btn.setOpaque(false);
		btn.setBounds(0, 0, 9, 9);
		getContentPane().add(btn);
		btn.addActionListener(this);
		btn.doClick();
	
		setTitle("SchoolRun!");
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //x를 누르면 닫히게 한다는 것(SWING은 이게 없어도 먹힘)
		setSize(1500,850);
		setVisible(true);
		setResizable(false);
		setLocation(100,100);
		backbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				introMusic.close();
				setVisible(false);
				Login ms = new Login(); //캐릭터, 테마 중에 무엇을 고를지 선택
				ms.setVisible(true);
			}
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		introMusic.start();
}
	public static void main(String[] args) {
		new Main_lobi();
	}
}