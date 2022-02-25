package game.Levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import game.core.game;

public class LevelMenu extends Level {
	
	private Image background;
	private Font font;
	private Font fontTitle;
	private String[] menuOption = {"Start","Help","Quit"};
	private int currentOption = 0;

	public LevelMenu(LevelManager levelManager) {
		super(levelManager);
		init();
	}

	public void init() { 	
		
		background = new ImageIcon("images/Background/background.png").getImage();
		font = new Font("Arial", Font.PLAIN, 20);
		fontTitle = new Font("Arial", Font.PLAIN, 36);
	}

	public void update(long elapsed) {	}

	public void draw(Graphics2D g) {
		
		g.drawImage(background, 0, 0, game.WIDTH, game.HEIGHT, null);
		g.setFont(fontTitle);
		g.drawString("Wizard Island", 135, 190);
		g.setFont(font);
		
		for(int i = 0; i < menuOption.length; i++) {
			
			if(i == currentOption) {
				g.setColor(Color.MAGENTA);
			}
			else {
				g.setColor(Color.WHITE);
			}		
			g.drawString(menuOption[i], 230, 250 + i * 50);
		}
	}
	
	public void option() {
		
		if(currentOption == 0) {
			levelManager.setLevel(LevelManager.levelOne);
		}
		else if(currentOption == 1) {
			levelManager.setLevel(LevelManager.levelHelp);
		}
		else if(currentOption == 2) {
			System.exit(0);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		
		if(k == KeyEvent.VK_ENTER){
			option();
		}
		
		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_W) {
			currentOption--;
			if(currentOption == -1) {
				currentOption = menuOption.length - 1;
			}
		}
		else if(k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S) {
			currentOption++;
			if(currentOption == menuOption.length) {
				currentOption = 0;
			}
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


