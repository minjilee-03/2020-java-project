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

//��� ���� �ݺ�
public class DoubleJumpBackground {

	private JFrame frame;
	
	//���
	ImageIcon backIc = new ImageIcon("..//practiceImg//backImg.png");
	Image backImg = backIc.getImage();
	
	int backX = 0; //�̹����� x���� �����ϱ� ���� ����
	int back2X = backImg.getWidth(null); //2��° �̹����� �ڵ��� �;��ϱ⿡ �̹����� ���̸� �����´�.

	//��Ű
	ImageIcon imageIc = new ImageIcon("..//practiceImg//cookieTest.png");
	Image img = imageIc.getImage();	
	int imgY = 5;
	
	int field = 250; 
	
	boolean fall = false;
	boolean jump = false;
	
	int doubleJump = 0; //���� ī��Ʈ(2�� ��������)
	
	static long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}

	
	//����
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

	//������ �޼���
	public DoubleJumpBackground() {
		initialize();
	}

	//gui �ڵ�
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

				//�����ϴ� ������
				@Override
				public void run() {
					while(true) {
						int foot = imgY + img.getHeight(null); //�̹����� Y��ġ + �̹����� ���� = �߹ٴ� ��ġ
						
						//�߹ٴ��� ������ ���� ������ �۵�
						if(jump == false && foot < field && fall == false) { //����X, ���߿� ����, �߶�X�� ��
							fall = true;
							System.out.println("���Ͻ���");
							long t1 = getTime(); //���� �ð��� ������
							long t2;
							int set = 1; //ó�� ���Ϸ�(0~10)���� �׽�Ʈ �غ���
							
							while(foot < field) { //���� ���� ��� ������
								t2 = getTime()-t1;
								int fallY = set + (int) ((t2)/60); //���Ϸ��� �ø���.
								
								if(foot + fallY >= field) {
									imgY = field - img.getHeight(null);
									break;
								} else {
									imgY = imgY + fallY;
								}
								
								foot = imgY + img.getHeight(null);
								repaint();
								
								if(jump == true) { //�������ٰ� ���������ϸ� ���� ����
									break;
								}
								try {
									Thread.sleep(10);
								} catch(InterruptedException e) {
									e.printStackTrace();
								}
							}
							
							fall = false;
							
							if(jump == false) { //���� ���� �ְ� �������� �ƴҶ� ���������� 0���� ����
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
			
			//���� ������
			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 32 && doubleJump < 2) {
						new Thread(new Runnable() {
							public void run() {
								doubleJump++;
								int nowJump = doubleJump; //�̹� ������ �׳� �������� ������������ ����
								jump = true; //���������� ���� ����
								
								if(doubleJump == 1) {
									System.out.println("����");
								} else if (doubleJump == 2) {
									System.out.println("��������");
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
									
									if(nowJump != doubleJump) { //������ �ѹ� �� �Ǹ� ù��° ������ �����
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
			
			//��潺����
			new Thread(new Runnable() {
				public void run() {
					while(true) {
						backX--; //backX�� 1�� ����
						back2X--;
						
						//���� backX�� ��ǥ���� �̹����� ���̺��� ������ �̹��� ������ ��ǥ�� �̵�
						//ex. �̹����� X���� -100�� �Ǹ� ȭ�鿡�� ���̻� �Ⱥ��� -> �ι�° �̹����� ��ǥ�� 0�� �� �� -> �׶� ó�� ������ x��ǥ�� �̹����� ������ 100���� ����� �ι�° �̹����� �ڷ� ���� ��
						if(backX < -(backImg.getWidth(null))) {
							backX = backImg.getWidth(null);
						}
						//���� back2X�� ��ǥ���� �̹����� ���̺��� ������ �̹��� ������ ��ǥ�� �̵�
						if(back2X < -(backImg.getWidth(null))) {
							back2X = backImg.getWidth(null);
						}
						
						repaint(); //�ٽ� �׸�
						try {
							Thread.sleep(10); //0.1�� ����
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
				
			}).start();
			
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backImg, backX, 0, this) ; //1�� �׸�, ����� �׸��� ��
			g.drawImage(backImg, back2X, 0, this); //2�� �׸�
			g.drawImage(img, 100, imgY, this);
		}
	}
	
}
