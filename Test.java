import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {
	JFrame frame;
	
	// 발판 관련 변수
	int field = 750; // 필드 값 : 700
	String fieldStr = "1111111110101101101101101101";
	List<Foot> fieldList = new ArrayList<>();
	int count1 = 0;
	int nowField = field; // nowField = field의 값

	ImageIcon landIc = new ImageIcon("..//image//block.jpg");
	Image landImg = landIc.getImage();

	static int getGround(String ground, int index) {
		return Integer.parseInt(ground.substring(index, index + 1));
	}

	// 쿠키 및 점프 관련 변수
	ImageIcon cookieIc = new ImageIcon("..//testimg//test01.jpg");
	Image cookieImg = cookieIc.getImage();
	
	int imgY = 5;
	int doubleJump = 0;
	boolean fall = false;
	boolean jump = false;

	static long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}
	
	//젤리 관련 변수
	String jellyStr = setStr();
	List<Jelly> jellyList = new ArrayList<>();
	int count2 = 0;
	ImageIcon jellyIc = new ImageIcon("..\\testimg\\jellyTest.png");
	Image jellyImg = jellyIc.getImage();
	 
	
	//젤리 및 발판 랜덤 메서드
	public String setStr() {
		Random rand = new Random();
		String result = "";
		int Fieldnum[] = new int[100];
		double num = 0.0;
		for (int i = 0; i < 100; i++) {
			num = Math.random();
			Fieldnum[i] = (int) Math.round(num);
			result += Integer.toString(Fieldnum[i]);
		}
		return result;
	}

	// 메인
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자
	public Test() {
			initialize();
		}

	// gui
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

			// 발판 리스트
			for (int i = 0; i < fieldStr.length(); i++) {
				int tempX = i * landImg.getWidth(null);

				if (getGround(fieldStr, i) == 1) {
					fieldList.add(new Foot(landImg, tempX, 750, landImg.getWidth(null), landImg.getHeight(null)));
				}
			}
			// 젤리 리스트

			for (int i = 0; i < jellyStr.length(); i++) {
				int tempX = i * jellyImg.getWidth(null);

				if (getGround(jellyStr, i) == 1) {
					jellyList.add(new Jelly(jellyImg, tempX, 550, jellyImg.getWidth(null), jellyImg.getHeight(null)));
				}
			}

			// 칠하는 스레드
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			

			// 발판 이동 스레드
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						for (int i = 0; i < fieldList.size(); i++) {
							fieldList.get(i).setX(fieldList.get(i).getX() - 3);
						}
						int range = (int) (landImg.getWidth(null) * 1.2);
						for (int i = 0; i < fieldList.size(); i++) {
							if (fieldList.get(i).getX() >= 0 && fieldList.get(i).getX() < range) {
								count1 = 1;
								break;
							} else if (i == fieldList.size() - 1)
								count1 = 0;
						}

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

			// 젤리 이동 스레드
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						for (int i = 0; i < jellyList.size(); i++) { jellyList.get(i).setX(jellyList.get(i).getX() - 3); }
						int range = (int) (jellyImg.getWidth(null) * 1.2);
						for (int i = 0; i < jellyList.size(); i++) {
							if (jellyList.get(i).getX() >= 0 && jellyList.get(i).getX() < range) {
								count2 = 1;
								break;
							} else if (i == jellyList.size() - 1) count2 = 0;
						}
						
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

			// 캐릭터의 높이에 따라 발판 위치를 지정하는 스레드
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						int foot = imgY + cookieImg.getHeight(null);

						if (count1 == 0)
							nowField = 2000;
						else if (count1 == 1 && foot > field)
							nowField = 2000;
						else if (count1 == 1 && foot < field)
							nowField = field;

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

			// 낙하하는 스레드
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						int foot = imgY + cookieImg.getHeight(null); // 이미지의 Y위치 + 이미지의 높이 = 발바닥 위치

						// 발바닥이 땅보다 위에 있으면 작동
						if (jump == false && foot < nowField && fall == false) { // 점프X, 공중에 존재, 추락X일 때
							fall = true;
							System.out.println("낙하시작");
							long t1 = getTime(); // 현재 시간을 가져옴
							long t2;
							int set = 1; // 처음 낙하량(0~10)까지 테스트 해보자

							while (foot < nowField) { // 발이 땅에 닿기 전까지
								t2 = getTime() - t1;
								int fallY = set + (int) ((t2) / 60); // 낙하량을 늘린다.

								if (foot + fallY >= nowField) {
									fallY = nowField - foot;
								}

								imgY = imgY + fallY;

								foot = imgY + cookieImg.getHeight(null);
								repaint();

								if (jump == true)
									break; // 떨어지다가 점프하면 낙하 중지

								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}

							fall = false;

							if (jump == false)
								doubleJump = 0;// 발이 땅에 있고 점프중이 아닐때 더블점프를 0으로 변경

						}

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

			// 점프 스레드
			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == 32 && doubleJump < 2) {
						new Thread(new Runnable() {
							public void run() {
								doubleJump++;
								int nowJump = doubleJump; // 이번 점프가 그냥 점프인지 더블점프인지 저장
								jump = true; // 점프중으로 상태 변경

								if (doubleJump == 1) {
									System.out.println("점프");
								} else if (doubleJump == 2) {
									System.out.println("더블점프");
								}

								int foot = imgY + cookieImg.getHeight(null);
								long t1 = getTime();
								long t2;
								int set = 5;
								int jumpY = 8;

								while (jumpY > 0) {
									t2 = getTime() - t1;
									jumpY = set - (int) ((t2) / 60);
									imgY = imgY - jumpY;
									foot = imgY + cookieImg.getHeight(null);
									repaint();

									if (nowJump != doubleJump)
										break; // 점프가 한번 더 되면 첫번째 점프는 멈춘다

									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}

								if (nowJump == doubleJump)
									jump = false;

							}

						}).start();
					}
				}
			});
			
		} // 생성자 메서드

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//발판
			for (int i = 0; i < fieldList.size(); i++) {
				Image tempImg = fieldList.get(i).getImage();
				int tempX = fieldList.get(i).getX();
				int tempY = fieldList.get(i).getY();
				int tempWidth = fieldList.get(i).getWidth();
				int tempHeight = fieldList.get(i).getHeight();

				g.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
			}
			
			//발판
			for (int i = 0; i < jellyList.size(); i++) {
				Image tempImg = jellyList.get(i).getImg();
				int tempX = jellyList.get(i).getX();
				int tempY = jellyList.get(i).getY();
				int tempWidth = jellyList.get(i).getWidth();
				int tempHeight = jellyList.get(i).getHeight();

				g.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
			}

			//쿠키
			g.drawImage(cookieImg, landImg.getWidth(null) / 2, imgY, cookieImg.getWidth(null), cookieImg.getHeight(null), this);			
			
		}

	}// MyPanel

}
