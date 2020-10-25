import java.awt.Image;

public class Jelly {
	
		private Image img;
		private int x;
		private int y;
		private int width;
		private int height;
		
		public Jelly() { }
		public Jelly(Image img, int x, int y, int w, int h) {
			this.img = img;
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
		}
		
		public Image getImg() { return img; }
		public void setImg(Image img) { this.img = img; }
		public int getX() { return x; }
		public void setX(int x) { this.x = x; }
		public int getY() { return y; }
		public void setY(int y) { this.y = y; }
		public int getWidth() { return width; }
		public void setWidth(int width) { this.width = width; }
		public int getHeight() { return height; }
		public void setHeight(int height) { this.height = height; }
			
}
