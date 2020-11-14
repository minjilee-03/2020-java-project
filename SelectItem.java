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
public class SelectItem extends JFrame implements ActionListener {
	private JButton btn;
	Music introMusic = new Music("..\\music\\ITEM_BGM.MP3", true);//뮤직
	public SelectItem() {
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
				CookieRun cr = new CookieRun();
				cr.main(null);
					
			}
		});
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setIcon(new ImageIcon("..\\image\\back_btn.jpg"));
		button_1.setBounds(0, 753, 166, 41);
		getContentPane().add(button_1);
		button.setIcon(new ImageIcon("..\\image\\start_game.jpg"));
		button.setBounds(700, 541, 652, 195);
		getContentPane().add(button);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("..\\image\\Item_back.jpg"));
		label_1.setBounds(0, 0, 1478, 794);
		getContentPane().add(label_1);
		
		
		btn = new JButton();
		btn.setOpaque(false);
		btn.setBorderPainted(false);
		btn.setBounds(0, 0, 0, 9);
		getContentPane().add(btn);
		btn.addActionListener(this);
		btn.doClick();
	
		setTitle("SchoolRun!");
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //x를 누르면 닫히게 한다는 것(SWING은 이게 없어도 먹힘)
		setSize(1500,850);
		setResizable(false);
		setLocation(100,100);
		backbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				introMusic.close();
				setVisible(false);
				Main_lobi ms = new Main_lobi(); //캐릭터, 테마 중에 무엇을 고를지 선택
				ms.setVisible(true);;
			}
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		introMusic.start();
}
	public static void main(String[] args) {
		new SelectItem();
	}
}