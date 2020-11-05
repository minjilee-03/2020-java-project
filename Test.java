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
	
	// ���� ���� ����
	int field = 750; // �ʵ� �� : 700
	String fieldStr = "1111111110101101101101101101";
	List<Foot> fieldList = new ArrayList<>();
	int count1 = 0;
	int nowField = field; // nowField = field�� ��

	ImageIcon landIc = new ImageIcon("..//image//block.jpg");
	Image landImg = landIc.getImage();

	static int getGround(String ground, int index) {
		return Integer.parseInt(ground.substring(index, index + 1));
	}

	// ��Ű �� ���� ���� ����
	ImageIcon cookieIc = new ImageIcon("..//testimg//test01.jpg");
	Image cookieImg = cookieIc.getImage();
	
	int imgY = 5;
	int doubleJump = 0;
	boolean fall = false;
	boolean jump = false;

	static long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}
	
	//���� ���� ����
	String jellyStr = setStr();
	List<Jelly> jellyList = new ArrayList<>();
	int count2 = 0;
	ImageIcon jellyIc = new ImageIcon("..\\testimg\\jellyTest.png");
	Image jellyImg = jellyIc.getImage();
	 
	
	//���� �� ���� ���� �޼���
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

	// ����
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

	// ������
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

			// ���� ����Ʈ
			for (int i = 0; i < fieldStr.length(); i++) {
				int tempX = i * landImg.getWidth(null);

				if (getGround(fieldStr, i) == 1) {
					fieldList.add(new Foot(landImg, tempX, 750, landImg.getWidth(null), landImg.getHeight(null)));
				}
			}
			// ���� ����Ʈ

			for (int i = 0; i < jellyStr.length(); i++) {
				int tempX = i * jellyImg.getWidth(null);

				if (getGround(jellyStr, i) == 1) {
					jellyList.add(new Jelly(jellyImg, tempX, 550, jellyImg.getWidth(null), jellyImg.getHeight(null)));
				}
			}

			// ĥ�ϴ� ������
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
			

			// ���� �̵� ������
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

			// ���� �̵� ������
			
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

			// ĳ������ ���̿� ���� ���� ��ġ�� �����ϴ� ������
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

			// �����ϴ� ������
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						int foot = imgY + cookieImg.getHeight(null); // �̹����� Y��ġ + �̹����� ���� = �߹ٴ� ��ġ

						// �߹ٴ��� ������ ���� ������ �۵�
						if (jump == false && foot < nowField && fall == false) { // ����X, ���߿� ����, �߶�X�� ��
							fall = true;
							System.out.println("���Ͻ���");
							long t1 = getTime(); // ���� �ð��� ������
							long t2;
							int set = 1; // ó�� ���Ϸ�(0~10)���� �׽�Ʈ �غ���

							while (foot < nowField) { // ���� ���� ��� ������
								t2 = getTime() - t1;
								int fallY = set + (int) ((t2) / 60); // ���Ϸ��� �ø���.

								if (foot + fallY >= nowField) {
									fallY = nowField - foot;
								}

								imgY = imgY + fallY;

								foot = imgY + cookieImg.getHeight(null);
								repaint();

								if (jump == true)
									break; // �������ٰ� �����ϸ� ���� ����

								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}

							fall = false;

							if (jump == false)
								doubleJump = 0;// ���� ���� �ְ� �������� �ƴҶ� ���������� 0���� ����

						}

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

			// ���� ������
			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == 32 && doubleJump < 2) {
						new Thread(new Runnable() {
							public void run() {
								doubleJump++;
								int nowJump = doubleJump; // �̹� ������ �׳� �������� ������������ ����
								jump = true; // ���������� ���� ����

								if (doubleJump == 1) {
									System.out.println("����");
								} else if (doubleJump == 2) {
									System.out.println("��������");
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
										break; // ������ �ѹ� �� �Ǹ� ù��° ������ �����

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
			
		} // ������ �޼���

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//����
			for (int i = 0; i < fieldList.size(); i++) {
				Image tempImg = fieldList.get(i).getImage();
				int tempX = fieldList.get(i).getX();
				int tempY = fieldList.get(i).getY();
				int tempWidth = fieldList.get(i).getWidth();
				int tempHeight = fieldList.get(i).getHeight();

				g.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
			}
			
			//����
			for (int i = 0; i < jellyList.size(); i++) {
				Image tempImg = jellyList.get(i).getImg();
				int tempX = jellyList.get(i).getX();
				int tempY = jellyList.get(i).getY();
				int tempWidth = jellyList.get(i).getWidth();
				int tempHeight = jellyList.get(i).getHeight();

				g.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
			}

			//��Ű
			g.drawImage(cookieImg, landImg.getWidth(null) / 2, imgY, cookieImg.getWidth(null), cookieImg.getHeight(null), this);			
			
		}

	}// MyPanel

}