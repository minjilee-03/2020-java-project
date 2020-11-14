package src;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	private boolean musicOn = false; 
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File("..\\music\\" +name);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() {
		isLoop = false;
		System.out.println("À½¾Ç ´Ý¾Æ¾ßÇØ");
		player.close();
		this.interrupt(); 
	}
	
	@Override
	public void run() {
		try {	
			do {
				isLoop = true;
				player.play();
				System.out.println("À½¾Ç ½ÇÇà");
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);
		} catch (Exception e) { 
			System.out.println(e.getMessage());
			
		}
	}
}