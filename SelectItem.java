package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

//테마 고르는창
public class SelectItem extends JFrame {
	public SelectItem() {
		JPanel p = new JPanel();
		JButton backbtn = new JButton(""); 
		backbtn.setIcon(new ImageIcon("..\\image\\back_btn.jpg"));
		getContentPane().add(p);
		p.setLayout(null);
		p.add(backbtn);
		
		backbtn.setBounds(0, 0, 196, 51); 
	
		setTitle("SchoolRun!");
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //x를 누르면 닫히게 한다는 것(SWING은 이게 없어도 먹힘)
		setSize(1500,850);
		setVisible(true);
		
		backbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SelectCT(); //캐릭터, 테마 중에 무엇을 고를지 선택
			}
		});
		
	}
	public static void main(String[] args) {
		new SelectItem();
	}
}