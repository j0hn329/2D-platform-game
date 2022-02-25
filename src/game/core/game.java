package game.core;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import game.Audio.Looping;
import game.Engine.GameCore;
import game.Levels.LevelManager;

@SuppressWarnings("serial")
public class game extends GameCore {

	public static int WIDTH = 512;
	public static int HEIGHT = 384;
	
	private LevelManager levelManager;

	public static void main(String[] args) {
		game gct = new game();
	    gct.init();
	    gct.run(false, WIDTH, HEIGHT);
	}
	
    public void init() {
    	levelManager = new LevelManager();
    	Looping soundtrack = new Looping("sounds/background.wav");
    	soundtrack.start();
    }
	 
    public void draw(Graphics2D g) {
    	levelManager.draw(g);
    }
	
    public void update(long elapsed) {
    	levelManager.update(elapsed);
    }
   
    public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) { levelManager.keyPressed(e); }
	public void keyReleased(KeyEvent e) { levelManager.keyReleased(e); }
	
    public void mouseClicked(MouseEvent e) { levelManager.mouseClicked(e); }
    public void mouseReleased(MouseEvent e) { levelManager.mouseReleased(e); }
    public void mouseEntered(MouseEvent e) { levelManager.mouseEntered(e); }
    public void mouseExited(MouseEvent e) { levelManager.mouseExited(e); }
    public void mousePressed(MouseEvent e) { levelManager.mousePressed(e); }
}
