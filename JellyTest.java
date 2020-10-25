import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class JellyTest {
	
	private JFrame frame;
	
	String jellyStr = setJellyStr();
	List<Jelly> jellyList = new ArrayList<>();
	int count = 0;
	
	ImageIcon jellyIc = new ImageIcon("..\\testimg\\jellyTest.png");
	Image jellyImg = jellyIc.getImage();
	
	static int getGround(String ground, int index) {
		return Integer.parseInt(ground.substring(index, index+1));
	}
	
	
	//메인
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JellyTest window = new JellyTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//생성자
	public JellyTest() {
		initialize();
	}

	//gui 코드
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1500, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1478, 794);
		frame.getContentPane().add(panel);
	}
	
	
	
	
	class MyPanel extends JPanel {
		public MyPanel() {
			setFocusable(true);	

			//발판 리스트 구성
			for(int i=0; i<jellyStr.length(); i++) {
				int tempX = i * jellyImg.getWidth(null);
				
				if(getGround(jellyStr, i) == 1) {
					jellyList.add(new Jelly(jellyImg, tempX, 400, jellyImg.getWidth(null), jellyImg.getHeight(null)));
				}
			}
			
			//칠하는 스레드
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}	
				}
			}).start();
			
			//발판 이동 스레드
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						
						
						for(int i = 0; i<jellyList.size(); i++) {
							jellyList.get(i).setX(jellyList.get(i).getX()-4);
						}
						
						int range = (int) (jellyImg.getWidth(null)*1.2);
						
						for(int i = 0; i<jellyList.size(); i++) {
							if(jellyList.get(i).getX() >= 0 && jellyList.get(i).getX() < range) {
								count = 1;
								break;
							} else if(i == jellyList.size()-1) count = 0;
						}
						
						
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}
			}).start();
			
			
		} //생성자 메서드
		
		@Override
		public void paintComponent(Graphics g) { 
			super.paintComponent(g);
			for(int i = 0; i< jellyList.size(); i++) {
				Image tempImg = jellyList.get(i).getImg();
				int tempX = jellyList.get(i).getX();
				int tempY = jellyList.get(i).getY();
				int tempWidth = jellyList.get(i).getWidth();
				int tempHeight = jellyList.get(i).getHeight();
				
				g.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
			}
			
		}
		
	}//MyPanel
	
	
	public String setJellyStr() {
		Random rand = new Random();
		String result = "";
		int jellyNum [] = new int[100];
		double num = 0.0;
		for(int i = 0; i<100; i++) {
			num = Math.random();
			jellyNum[i] = (int) Math.round(num);
			result += Integer.toString(jellyNum[i]);
		}
		
		return result;
	}
	
	
}
