package game.Levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import game.core.game;

public class LevelOver extends Level {
	
	private Image background;
	private Font font;
	private Font fontTwo;
	
	public LevelOver(LevelManager levelManager) {
		super(levelManager);
		init();
	}

	public void init() { 	
		
		background = new ImageIcon("images/Background/background.png").getImage();
		font = new Font("Arial", Font.PLAIN, 24);
		fontTwo = new Font("Arial", Font.PLAIN, 20);
	}

	public void update(long elapsed) {	}

	public void draw(Graphics2D g) {
		
		g.drawImage(background, 0, 0, game.WIDTH, game.HEIGHT, null);
		g.setFont(font);
		
		g.setColor(Color.WHITE);
		g.drawString("Congratulations you have beaten the game!",30, 220);
		g.setFont(fontTwo);
		g.setColor(Color.MAGENTA);
		g.drawString("Press Enter to return to Main Menu", 100, 340);
			
	}
	
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		
		if(k == KeyEvent.VK_ENTER){
			levelManager.setLevel(LevelManager.levelMenu);
		}
		else if(k == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}
	
	public void keyReleased(KeyEvent e) { } 
	public void mouseClicked(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }

}

