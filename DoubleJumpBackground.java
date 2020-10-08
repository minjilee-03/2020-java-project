import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//배경 무한 반복
public class DoubleJumpBackground {

	private JFrame frame;
	
	//배경
	ImageIcon backIc = new ImageIcon("..//practiceImg//backImg.png");
	Image backImg = backIc.getImage();
	
	int backX = 0; //이미지의 x값을 변경하기 위한 변수
	int back2X = backImg.getWidth(null); //2번째 이미지가 뒤따라 와야하기에 이미지의 넓이를 가져온다.

	//쿠키
	ImageIcon imageIc = new ImageIcon("..//practiceImg//cookieTest.png");
	Image img = imageIc.getImage();	
	int imgY = 5;
	
	int field = 250; 
	
	boolean fall = false;
	boolean jump = false;
	
	int doubleJump = 0; //점프 카운트(2면 더블점프)
	
	static long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}

	
	//메인
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoubleJumpBackground window = new DoubleJumpBackground();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//생성자 메서드
	public DoubleJumpBackground() {
		initialize();
	}

	//gui 코드
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new MyPanel();
		panel.setBounds(0, 0, 428, 244);
		frame.getContentPane().add(panel);
	}
	
	class MyPanel extends JPanel {
		public MyPanel() {
			setFocusable(true);
			
			new Thread(new Runnable() {

				//낙하하는 스레드
				@Override
				public void run() {
					while(true) {
						int foot = imgY + img.getHeight(null); //이미지의 Y위치 + 이미지의 높이 = 발바닥 위치
						
						//발바닥이 땅보다 위에 있으면 작동
						if(jump == false && foot < field && fall == false) { //점프X, 공중에 존재, 추락X일 때
							fall = true;
							System.out.println("낙하시작");
							long t1 = getTime(); //현재 시간을 가져옴
							long t2;
							int set = 1; //처음 낙하량(0~10)까지 테스트 해보자
							
							while(foot < field) { //발이 땅에 닿기 전까지
								t2 = getTime()-t1;
								int fallY = set + (int) ((t2)/60); //낙하량을 늘린다.
								
								if(foot + fallY >= field) {
									imgY = field - img.getHeight(null);
									break;
								} else {
									imgY = imgY + fallY;
								}
								
								foot = imgY + img.getHeight(null);
								repaint();
								
								if(jump == true) { //떨어지다가 더블점프하면 낙하 중지
									break;
								}
								try {
									Thread.sleep(10);
								} catch(InterruptedException e) {
									e.printStackTrace();
								}
							}
							
							fall = false;
							
							if(jump == false) { //발이 땅에 있고 점프중이 아닐때 더블점프를 0으로 변경
								doubleJump = 0;
							}
							
						}
						
						try {
							Thread.sleep(10);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
				
			}).start();
			
			//점프 스레드
			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 32 && doubleJump < 2) {
						new Thread(new Runnable() {
							public void run() {
								doubleJump++;
								int nowJump = doubleJump; //이번 점프가 그냥 점프인지 더블점프인지 저장
								jump = true; //점프중으로 상태 변경
								
								if(doubleJump == 1) {
									System.out.println("점프");
								} else if (doubleJump == 2) {
									System.out.println("더블점프");
								}
								
								
								
								int foot = imgY + img.getHeight(null);
								long t1 = getTime();
								long t2;
								int set = 5;
								int jumpY = 8;
								
								while(jumpY > 0) {
									t2 = getTime()-t1;
									jumpY = set - (int) ((t2)/60);
									imgY = imgY - jumpY;
									foot = imgY + img.getHeight(null);
									repaint();
									
									if(nowJump != doubleJump) { //점프가 한번 더 되면 첫번째 점프는 멈춘다
										break;
									}
									try {
										Thread.sleep(10);
									} catch(InterruptedException e) {
										e.printStackTrace();
									}
								}
								
								if(nowJump == doubleJump) {
									jump = false;
								}
								
							}
							
						}).start();
					}
				}	
			});
			
			//배경스레드
			new Thread(new Runnable() {
				public void run() {
					while(true) {
						backX--; //backX를 1씩 감소
						back2X--;
						
						//만약 backX의 좌표값이 이미지의 넓이보다 작으면 이미지 넓이의 좌표로 이동
						//ex. 이미지의 X값이 -100이 되면 화면에서 더이상 안보임 -> 두번째 이미지의 좌표는 0이 될 것 -> 그때 처음 사진의 x좌표를 이미지의 넓이인 100으로 만들면 두번째 이미지의 뒤로 가는 것
						if(backX < -(backImg.getWidth(null))) {
							backX = backImg.getWidth(null);
						}
						//만약 back2X의 좌표값이 이미지의 넓이보다 작으면 이미지 넓이의 좌표로 이동
						if(back2X < -(backImg.getWidth(null))) {
							back2X = backImg.getWidth(null);
						}
						
						repaint(); //다시 그림
						try {
							Thread.sleep(10); //0.1초 쉰다
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
				
			}).start();
			
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backImg, backX, 0, this) ; //1번 그림, 배경을 그리는 것
			g.drawImage(backImg, back2X, 0, this); //2번 그림
			g.drawImage(img, 100, imgY, this);
		}
	}
	
}
