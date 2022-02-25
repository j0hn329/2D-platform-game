package game.Levels;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Level {
	
	protected LevelManager levelManager;
	
	public Level(LevelManager levelManager) {
		this.levelManager = levelManager;
	}
	
	public abstract void init();
	public abstract void draw(Graphics2D g);
	public abstract void update(long elapsed);
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	public abstract void mouseClicked(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseEntered(MouseEvent e);
	public abstract void mouseExited(MouseEvent e);
	public abstract void mousePressed(MouseEvent e);
}
