package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
//캐릭터의 학년을 고르는 창

public class Select_Character extends JFrame {
	public Select_Character() {
		JPanel p = new JPanel();
		p.setBackground(new Color(255, 250, 205));
		JButton backbtn = new JButton(""); 
		backbtn.setIcon(new ImageIcon("..//image//btn_image//back_btn.jpg"));
		JButton bongbtn = new JButton("");
		bongbtn.setIcon(new ImageIcon("..//image//game_image//bong_1.gif"));
		JButton seoribtn = new JButton("");
		seoribtn.setIcon(new ImageIcon("..//image//game_image//seori_1.gif"));
		JButton naribtn = new JButton("");
		naribtn.setIcon(new ImageIcon("..//image//game_image//nari_1.gif"));
		getContentPane().add(p); //프레임에 판넬 붙이기
		p.setLayout(null);
		p.add(backbtn);
		p.add(bongbtn);
		p.add(seoribtn);
		p.add(naribtn);
		
		backbtn.setBounds(0, 0, 196, 57);
		bongbtn.setBounds(150, 200, 300, 400);
		seoribtn.setBounds(550, 200, 300, 400);
		naribtn.setBounds(950, 200, 300, 400);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("..\\image\\grade_back.jpg"));
		lblNewLabel.setBounds(-12, -46, 1500, 850);
		p.add(lblNewLabel);
		
		setTitle("SchoolRun");
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //x를 누르면 닫히게 한다는 것(SWING은 이게 없어도 먹힘)
		setSize(1500,850);
		setVisible(true);
		
		bongbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//new Select_Bong(); 봉이 1,2,3 하년 중 고르는 클래스
			}
		});
		
		
		
		backbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SelectCT(); //캐릭터, 테마 중에 무엇을 고를지 선택
			}
		});
		
	}
}