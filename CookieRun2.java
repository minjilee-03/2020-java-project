import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import class_pack.Back;
import class_pack.Cookie;
import class_pack.Foot;
import class_pack.Jelly;
import class_pack.Tacle;
import class_pack.Util;

public class CookieRun2 {
	//��������
	JFrame frame;
	Button escButton;
	
	//���
	private ImageIcon backIc = new ImageIcon("..//image//game_image//stage1_back.jpg");
	private ImageIcon secondbackIc = new ImageIcon("..//image//game_Image//stage1_back.jpg");
	//��Ű
	private ImageIcon cookieIc = new ImageIcon("..//image//game_image//bong_1.gif");
	private ImageIcon jumpIc = new ImageIcon("..//image//game_image//bong_1_jump2.png");
	private ImageIcon doubleJumpIc = new ImageIcon("..//image//game_image//bong_1_jump_5.gif");
	private ImageIcon fallIc = new ImageIcon("..//image//game_image//bong_1_fall.png");
	private ImageIcon slideIc = new ImageIcon("..//image//game_image//bong_1_slide.png");
	private ImageIcon hitIc = new ImageIcon("..//image//game_image//bong_1_fall.png");
	//����
	private ImageIcon fieldIc = new ImageIcon("..//image//game_image//test02.jpg");
	private String fieldStr = "11111111111111111111111111111011111111111110111111111111111110011111110111111111111011111111111111010111111001111101101101111";
	private List<Foot> fieldList = new ArrayList<>();
	//�ƿ��ƴ��� Ȯ���ϴ� ����
	private ImageIcon field2Ic = new ImageIcon("..//image//game_image//test02.jpg");
	private String field2Str = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
	private List<Foot> field2List = new ArrayList<>();
	//����
	private ImageIcon jelly1Ic = new ImageIcon("..//testimg//jelly01.jpg");
	private ImageIcon jelly2Ic = new ImageIcon("..//testimg//jelly2Test.png");
	private ImageIcon jellyHPIc = new ImageIcon("..//testimg//jellyHPTest.png");
	private ImageIcon jellyEffectIc = new ImageIcon("..//testimg//effectTest.png");
	private String jellyStr = "102030102030102030102030102030102030102030102030102030102030102030102030102030102030102030102030102030102030102030102030";
	private List<Jelly> jellyList = new ArrayList<>();
	//��ֹ�
	private ImageIcon tacle1Ic = new ImageIcon("..//testimg//tacleTest10.png");
	private ImageIcon tacle2Ic = new ImageIcon("..//testimg//tacleTest25.png");
	private String tacleStr = "00000000000000010010000100001000000000";
	private List<Tacle> tacleList = new ArrayList<>();
	//�׿��� ����
	private int runPage = 0; //ȭ�� �̵��Ҷ����� ü�±��
	private boolean escKeyOn = false;
	private boolean downKeyOn = false;
	private boolean gameover = false;
	private int resultScore = 0;
	private int gameSpeed = 3;
	private int nowField = 2000;
	
	int face;
	int foots;
	
	Cookie c1;
	Back b11;
	Back b12;
	
	static int getGround(String ground, int index) {
		return Integer.parseInt(ground.substring(index, index+1));
	}
	
	
	//����ȭ
	private AlphaComposite alphaComposite;
	private Graphics buffg;
	
	

	
	//MyPanel
	class MyPanel extends JPanel {
		//������ �޼���
		public MyPanel() {
			setFocusable(true);
			c1 = new Cookie(cookieIc.getImage());
			if(c1.getImage() != slideIc.getImage()) {
				face = c1.getX() + c1.getWidth();
				foots = c1.getY() + c1.getHeight();
			} else {
				face = c1.getX() + 120;
				foots = c1.getY() + 80;
			}
			b11 = new Back(backIc.getImage(), 0, 0, backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));
			b12 = new Back(backIc.getImage(), backIc.getImage().getWidth(null), 0, backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));
			
			//���� ����Ʈ ����
			for(int i = 0; i<fieldStr.length(); i++) {
				int tempX = i * fieldIc.getImage().getWidth(null);
				
				if(getGround(fieldStr, i)==1) {
					fieldList.add(new Foot(fieldIc.getImage(), tempX, 750, fieldIc.getImage().getWidth(null), fieldIc.getImage().getHeight(null)));
				}
			}
			
			//�ƿ����� ����Ʈ ����
			for(int i = 0; i<field2Str.length(); i++) {
				int tempX = i * field2Ic.getImage().getWidth(null);
				
				if(getGround(field2Str, i)==1) {
					field2List.add(new Foot(field2Ic.getImage(), tempX, 1000, field2Ic.getImage().getWidth(null), field2Ic.getImage().getHeight(null)));
				}
			}
			
			//���� ����Ʈ ����
			for(int i = 0; i<jellyStr.length(); i++) {
				int tempX = i * jelly1Ic.getImage().getWidth(null);
				
				if(getGround(jellyStr, i)==1) {
					jellyList.add(new Jelly(jelly1Ic.getImage(), tempX, 400, jelly1Ic.getImage().getWidth(null), jelly1Ic.getImage().getHeight(null), 100));
				} else if(getGround(jellyStr, i)==2) {
					jellyList.add(new Jelly(jelly2Ic.getImage(), tempX, 400, jelly2Ic.getImage().getWidth(null), jelly2Ic.getImage().getHeight(null), 200));
				} else if(getGround(jellyStr, i)==3) {
					jellyList.add(new Jelly(jellyHPIc.getImage(), tempX, 400, jellyHPIc.getImage().getWidth(null), jellyHPIc.getImage().getHeight(null), 500));
				}
			}
			
			//��ֹ� ����Ʈ ����
			for(int i = 0; i<tacleStr.length(); i++) {
				int tempX = i * tacle1Ic.getImage().getWidth(null);
				
				if(getGround(tacleStr, i)==1) {
					tacleList.add(new Tacle(tacle1Ic.getImage(), tempX, 670, tacle1Ic.getImage().getWidth(null), tacle1Ic.getImage().getHeight(null), 0));
				} else if(getGround(tacleStr, i)==2) {
					tacleList.add(new Tacle(tacle2Ic.getImage(), tempX, 500, tacle2Ic.getImage().getWidth(null), tacle2Ic.getImage().getHeight(null), 0));
				}
			}
			
			//������Ʈ ���� ������
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						repaint();
						
						//���� ���ϸ� ���߱�
						if(c1.getHp()<=0) {
							while(true){
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
						
						//�������� ���߱�
						if(c1.getY() + c1.getHeight() >= 1000) {
							while(true){
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
						
						
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}
				}
			}).start();
			
			mapMove(); 
			fall();
			
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ESCAPE) { //esc�� �����ٸ�
						if(!escKeyOn) {
							escKeyOn = true;
							add(escButton);
							repaint(); 
						} else {
							remove(escButton);
							escKeyOn = false;
						}
					}
					
					if(!escKeyOn) {
						if(e.getKeyCode() == KeyEvent.VK_SPACE && c1.getCountJump()<2) {
							jump();
						}
						
						if(e.getKeyCode() == KeyEvent.VK_DOWN) {
							downKeyOn = true;
							
							if(c1.getImage() != slideIc.getImage() && !c1.isJump() && !c1.isFall()) {
								c1.setImage(slideIc.getImage());
							}
						}	
					}
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_DOWN) {
						downKeyOn = false;
						
						if(c1.getImage() != cookieIc.getImage() && !c1.isJump() && !c1.isFall()) {
							c1.setImage(cookieIc.getImage());
						}
					}
				}
			});
			
		}
		
		//����Ʈ �޼���
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D)buffg;
			
			//���
			g.drawImage(b11.getImage(), b11.getX(), 0, null);
			g.drawImage(b12.getImage(), b12.getX(), 0, null);
			
			//����
			for(int i = 0; i< fieldList.size(); i++) {
				Image tempImg = fieldList.get(i).getImage();
				int tempX = fieldList.get(i).getX();
				int tempY = fieldList.get(i).getY();
				int tempWidth = fieldList.get(i).getWidth();
				int tempHeight = fieldList.get(i).getHeight();
				
				g.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
			}
			
			//����
			for(int i = 0; i< jellyList.size(); i++) {
				Image tempImg = jellyList.get(i).getImage();
				int tempX = jellyList.get(i).getX();
				int tempY = jellyList.get(i).getY();
				int tempWidth = jellyList.get(i).getWidth();
				int tempHeight = jellyList.get(i).getHeight();
				
				g.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
			}
			
			//��ֹ�
			for(int i = 0; i< tacleList.size(); i++) {
				Image tempImg = tacleList.get(i).getImage();
				int tempX = tacleList.get(i).getX();
				int tempY = tacleList.get(i).getY();
				int tempWidth = tacleList.get(i).getWidth();
				int tempHeight = tacleList.get(i).getHeight();
				
				g.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
			}
			
			if(c1.getImage() != slideIc.getImage()) {
				g.drawImage(c1.getImage(), c1.getX(), c1.getY(), c1.getWidth(), c1.getHeight(), null);
			} else {
				g.drawImage(c1.getImage(), c1.getX(), c1.getY()+40, 120, 80, null);
			}
			//����, ü��
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(resultScore), 1400, 40);
			
			g.setColor(Color.RED);
			g.fillRect(50,  40, c1.getHp()/2, 30);
			
			//���ӿ���
			if(c1.getHp() <= 0) {
				g.setColor(Color.RED);
				g.drawString("���ӿ���", 700, 50);
			}
		}
	
	}//MyPanel ��
	
	

	//��� �޼���
	void mapMove() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					if(runPage > 800) { //800�ȼ��� ������
						c1.setHp(c1.getHp()-10);
						runPage = 0;
					}
					runPage += gameSpeed;
					
					//���
					if(b11.getX() < -(b11.getWidth()-1)) { b11.setX(b11.getWidth()); }
					if(b12.getX() < -(b12.getWidth()-1)) { b12.setX(b12.getWidth()-1); }
					
					b11.setX(b11.getX()-(gameSpeed/3));
					b12.setX(b12.getX()-(gameSpeed/3));
					
					//���� �̵�
					for(int i = 0; i<fieldList.size(); i++) {
						Foot tempField = fieldList.get(i);
						
						if(tempField.getX() < -90) {
							fieldList.remove(tempField);
						} else {
							tempField.setX(tempField.getX() - gameSpeed);
						}
					}
					
					//���� �̵�
					for(int i = 0; i<jellyList.size(); i++) {
						Jelly tempJelly = jellyList.get(i);
						
						if(tempJelly.getX() < -90) {
							fieldList.remove(tempJelly);
						} else {
							tempJelly.setX(tempJelly.getX()-gameSpeed);
							
							foots = c1.getY() + c1.getHeight();
							if(c1.getImage() != slideIc.getImage() && tempJelly.getX() + tempJelly.getWidth()*20/100 >= c1.getX()
									&& tempJelly.getX() + tempJelly.getWidth()*80/100 <= face
									&& tempJelly.getY() + tempJelly.getWidth()*20/100 >= c1.getY()
									&& tempJelly.getY() + tempJelly.getWidth()*80/100 <= foots
									&& tempJelly.getImage() != jellyEffectIc.getImage()) {
								
								tempJelly.setImage(jellyEffectIc.getImage());
								resultScore += tempJelly.getScore();
							
							} else if(c1.getImage() == slideIc.getImage() && tempJelly.getX() + tempJelly.getWidth()*20/100 >= c1.getX()
									&& tempJelly.getX() + tempJelly.getWidth()*80/100 <= face
									&& tempJelly.getY() + tempJelly.getWidth()*20/100 >= c1.getY() + c1.getHeight()*1/3
									&& tempJelly.getY() + tempJelly.getWidth()*80/100 <= foots
									&& tempJelly.getImage() != jellyEffectIc.getImage()) {
								tempJelly.setImage(jellyEffectIc.getImage());
								resultScore += tempJelly.getScore();
							}
						}
					}
					
					//��ֹ� �̵�
					for(int i = 0; i<tacleList.size(); i++) {
						Tacle tempTacle = tacleList.get(i);
						
						if(tempTacle.getX() <-90) {
							fieldList.remove(tempTacle);
						} else {
							tempTacle.setX(tempTacle.getX()-gameSpeed);
							
							face = c1.getX() + c1.getWidth();
							foots = c1.getY() + c1.getHeight();
							
							//�����̵� X, �׳� ��ֹ�
							if(!c1.isInvincible() && c1.getImage() != slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth()/2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth()/2 <= face
									&& tempTacle.getY() + tempTacle.getHeight()/2 >= c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight()/2 <= foots) {
								
								hit();
							
							//�����̵� X, ���� ��ֹ�
							} else if(!c1.isInvincible() && c1.getImage() != slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth()/2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth()/2 <= face
									&& tempTacle.getY() <= c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight()*95/100 > c1.getY()) {
								
								hit();
								
							//�����̵�O, �׳� ��ֹ�	
							} else if(!c1.isInvincible() && c1.getImage() == slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth()/2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth()/2 <= face
									&& tempTacle.getY() + tempTacle.getHeight()/2 >= c1.getY() + c1.getHeight()*2/3
									&& tempTacle.getY() + tempTacle.getHeight()/2 <= foots) {
								
								hit();
							
							//�����̵�O, ������ֹ�	
							} else if(!c1.isInvincible() && c1.getImage() == slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth()/2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth()/2 <= face
									&& tempTacle.getY() < c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight()*95/100 > c1.getY() + c1.getHeight()*2/3) {
								
								hit();
								
							}
						}
					}
					
					//��Ű�� ���� ���� ���
					int tempField;
					int tempNowField;
					
					if(c1.isInvincible()) {
						tempNowField = 400;
					} else {
						tempNowField = 2000;
					}
					
					for(int i = 0; i<fieldList.size(); i++) {
						int tempX = fieldList.get(i).getX();
						
						if(tempX > c1.getX()-60 && tempX <= face) {
							tempField = fieldList.get(i).getY();
							foots = c1.getY() + c1.getHeight();
							
							if(tempField < tempNowField && tempField >= foots) {
								tempNowField = tempField;
							}
						}
					}
					
					nowField = tempNowField;
					
					//������ ���ϸ� �����̵� ����
					if(c1.getHp() <= 0) {
						while(true) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
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
	} //mapMove ��
	
	//�ε����� �޼���
	void hit() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				c1.setInvincible(true);
				System.out.println("��������");
				c1.setHp(c1.getHeight()-100);
				c1.setImage(hitIc.getImage());
				c1.setAlpha(80);
				
				try {
					Thread.sleep(500);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				
				if(c1.getImage() == hitIc.getImage()) {
					c1.setImage(cookieIc.getImage());
				}
				
				
					
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
				c1.setAlpha(255);
				c1.setInvincible(false);
				System.out.println("�������� ����");
			}	
		}).start();
	}//hit ��

	
	//�������� �޼���
	void fall() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					foots = c1.getY() + c1.getHeight(); //�� ��ġ ��ĵ
					
					if(foots < nowField && !c1.isJump() && !c1.isFall()) {
						c1.setFall(true);
						System.out.println("����");
					
						long t1 = Util.getTime();
						long t2;
						int set = 1; //���Ϸ�
						
						while(foots < nowField) {
							t2 = Util.getTime() - t1;
							int fallY = set + (int) ((t2)/60);
							
							if(foots + fallY >= nowField) {
								fallY = nowField - foots;
							}
							
							c1.setY(c1.getY() + fallY);
							
							if(c1.isJump()) {
								break;
							}
							
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
						}
						
						c1.setFall(false);
						
						
						if(downKeyOn && !c1.isJump() && !c1.isFall() && c1.getImage() != slideIc.getImage()) {
							c1.setImage(slideIc.getImage());
						} else if(!downKeyOn && !c1.isJump() && !c1.isFall() && c1.getImage() != cookieIc.getImage()) {
							c1.setImage(cookieIc.getImage());
						}
						
						if(!c1.isJump()) { c1.setCountJump(0); }
					}
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}	
			}
		}).start();
	} //fall ��
	
	
	//�����ϴ� �޼���
	void jump() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				c1.setCountJump(c1.getCountJump()+1);
				int nowJump = c1.getCountJump();
				c1.setJump(true);
				
				if(c1.getCountJump()==1) {
					System.out.println("����");
					c1.setImage(jumpIc.getImage());
				} else if(c1.getCountJump()==2) {
					System.out.println("���� ����");
					c1.setImage(doubleJumpIc.getImage());
				}
				
				long t1 = Util.getTime();
				long t2;
				int set = 8; //������
				int jumpY = 1;
				
				while(jumpY >= 0) {
					t2 = Util.getTime() - t1;
					jumpY = set - (int) ((t2)/40);
					
					c1.setY(c1.getY()-jumpY);
					
					if(nowJump != c1.getCountJump()) {
						break;
					}
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				if(nowJump == c1.getCountJump()) {
					c1.setJump(false);
				}
				
			}
			
		}).start();
		
	}//jump ��
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CookieRun2 window = new CookieRun2();
					if(window.c1.getHp() > 0) {
						window.frame.setVisible(true);
					} else {
						window.notifyAll();
						window.frame.setVisible(false);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CookieRun2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1500, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1478, 794);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		escButton = new Button("�����");
		escButton.setBounds(350, 240, 50, 30);
		escButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.remove(escButton);
				escKeyOn = false;
			}
		});
	}
}
