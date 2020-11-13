package Game;


import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.SelectItem;
import src.User;

public class Resultgame {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	ImageIcon backIc;
	int resultScore = 0;
	int resultMoney = 0;
	User u = new User();
	
	public Resultgame(int r, int m) {
		this.resultScore = r;
		this.resultMoney = m;
		//점수
		JLabel score = new JLabel(Integer.toString(resultScore));
		score.setFont(new Font("1훈새마을운동 R", Font.BOLD | Font.ITALIC, 99));
		score.setForeground(Color.WHITE);
		score.setBounds(281, 141, 420, 100);
		panel.add(score);
		
		//배경
		backIc = new ImageIcon("..//image//score_dialog.jpg");
		panel.setLayout(null);
		
		//게임머니
		JLabel money = new JLabel(Integer.toString(resultMoney));
		money.setFont(new Font("1훈새마을운동 R", Font.PLAIN, 21));
		money.setForeground(Color.WHITE);
		money.setBounds(433, 232, 157, 74);
		panel.add(money);
		
		//메인화면으로
		JButton gotorobiBtn = new JButton("");
		gotorobiBtn.setIcon(new ImageIcon("..\\Image\\btn_image\\gotorobi.jpg"));
		gotorobiBtn.setBounds(460, 366, 267, 83);
		gotorobiBtn.setBorderPainted(false);
		gotorobiBtn.setFocusPainted(false);
		panel.add(gotorobiBtn);
		gotorobiBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				u.intoResult(resultScore,  resultMoney);
				frame.dispose();
				new SelectItem();
			}
		});
		
		//다시하기
		JButton restartBtn = new JButton("");
		restartBtn.setIcon(new ImageIcon("..\\Image\\btn_image\\tryagain_btn.jpg"));
		restartBtn.setBounds(94, 366, 267, 83);
		restartBtn.setBorderPainted(false);
		restartBtn.setFocusPainted(false);
		panel.add(restartBtn);
		restartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				u.intoResult(resultScore,  resultMoney);
				frame.dispose();
				CookieRun cr = new CookieRun();  
			}
		});
		
		//배경
		JLabel backimg = new JLabel("");
		backimg.setFont(new Font("1훈새마을운동 R", Font.BOLD, 18));
		backimg.setIcon(new ImageIcon("..\\Image\\back_image\\score_dialog.jpg"));
		backimg.setBounds(0, 0, 801, 498);
		panel.add(backimg);
		
		//다이얼로그
		panel.setBounds(0, 0, 815, 535);
		frame.add(panel);
		
		frame.setBounds(0, 0, 820, 540);
		frame.setVisible(true);
	}
	
}