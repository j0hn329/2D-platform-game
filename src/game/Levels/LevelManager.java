package game.Levels;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LevelManager {
	
	private Level[] level;
	private int currentLevel;

	public static final int numLevels = 5;
	public static final int levelMenu = 0;
	public static final int levelHelp = 1;
	public static final int levelOne = 2;
	public static final int levelTwo = 3;
	public static final int levelOver = 4;
	
	public LevelManager() {
		
		level = new Level[numLevels];
		currentLevel = levelMenu;
		loadLevel(currentLevel);
	}
	
	public void loadLevel(int LEVEL) {
		
		if(LEVEL == levelMenu) {
			level[LEVEL] = new LevelMenu(this);
		}
		else if(LEVEL == levelOne) {
			level[LEVEL] = new LevelOne(this);
		}
		else if(LEVEL == levelHelp) {
			level[LEVEL] = new LevelHelp(this);
		}
		else if(LEVEL == levelTwo) {
			level[LEVEL] = new LevelTwo(this);
		}
		else if(LEVEL == levelOver) {
			level[LEVEL] = new LevelOver(this);
		}
	}
	
	private void unloadLevel(int LEVEL) {
		level[LEVEL] = null;
	}
	
	public void setLevel(int LEVEL) {
		unloadLevel(currentLevel);
		currentLevel = LEVEL;
		loadLevel(currentLevel);
	}
		
	public void update(long elapsed) {

		if(level[currentLevel] != null) {
			level[currentLevel].update(elapsed);
		}
	}
	
	public void draw(Graphics2D g) {

		if(level[currentLevel] != null) {
			level[currentLevel].draw(g);
		}
	}

	public void keyPressed(KeyEvent e) { level[currentLevel].keyPressed(e); }
	public void keyReleased(KeyEvent e) { level[currentLevel].keyReleased(e); }
	public void mouseClicked(MouseEvent e) { level[currentLevel].mouseClicked(e); }
	public void mouseReleased(MouseEvent e) { level[currentLevel].mouseReleased(e); }
	public void mouseEntered(MouseEvent e) { level[currentLevel].mouseEntered(e); }
	public void mouseExited(MouseEvent e) { level[currentLevel].mouseExited(e); }
	public void mousePressed(MouseEvent e) { level[currentLevel].mousePressed(e); }
}
