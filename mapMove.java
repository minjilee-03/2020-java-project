import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class mapMove {

	private JFrame frame;
	ImageIcon backIc = new ImageIcon("..//image//stage1_back.jpg");
	Image backImg = backIc.getImage();
	
	int backX = 0;
	int backX2 = backImg.getWidth(null);
	

	//메인
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mapMove window = new mapMove();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//생성자
	public mapMove() {
		initialize();
	}

	//Gui코드
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1500, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1478, 794);
		frame.getContentPane().add(panel);
		
	}
	
	
	//MyPanel 클래스
	class MyPanel extends JPanel {
		public MyPanel() {
			setFocusable(true);
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(true) {
						backX--;
						backX2--;
						
						if(backX < -(backImg.getWidth(null))) { backX = backImg.getWidth(null);}
						if(backX2 < -(backImg.getWidth(null))) { backX2 = backImg.getWidth(null);}
						
						repaint();
						
						try {
							Thread.sleep(10);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}).start();
		}
		
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backImg, backX, 0, this);
			g.drawImage(backImg, backX2, 0, this);
		}
		
		
	}
	
	
}
