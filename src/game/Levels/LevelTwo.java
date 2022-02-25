package game.Levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import game.core.game;
import game.Audio.Echo;
import game.Engine.*;
import game.Entity.*;
import game.Levels.Level;
import game.Levels.LevelManager;

public class LevelTwo extends Level {
	
	//background
	private Image background;
	private Image treesBack;
	private Image treesFore;
	
	//HUD
	private Image heart;
	private Image health;
	private Image diamondIcon;
	private Image window;

	//tiles
	private TileMap tmap2;
	private TileMap tmapBackdrop2;
	private int tileSize = 32;

	//animation
	private Animation player_idle;
	private Animation player_right;
	private Animation player_left;
	private Animation player_jump;
	private Animation player_attack;
	private Animation skeleton_right;
	private Animation skeleton_left;
	private Animation fire_attack;
	private Animation consum;
	private Animation level_flag;
	
	//movement 
	private boolean left = false;
	private boolean right = false; 
	private boolean jumping = false; 
	private boolean falling = false;
	private boolean attacking = false;

	//offsets
	private int xOFF;
	private int yOFF;
	
	//collisions
	private int currentRow;
	private int currentColumn;
	private float xtemp;
	private float ytemp;
	private float xc;
	private float yc;
	
	//entities
	private Player player;
	private Skeleton skeleton;
	private Sprite flag;
	private ArrayList<Enemy> enemies;
	private ArrayList<FireAttack> fireAttack;
	private ArrayList<Consumables> consumables;
	
	private int score = 0;
	private float gravity = 0.001f;
	private boolean paused = false;
	private int mouseX;
	private int mouseY;
	
	public LevelTwo(LevelManager levelManager) {
		super(levelManager);
		init();
	}

	public void init() {
		
		background = new ImageIcon("images/Background/Background2.png").getImage();
     	treesBack = new ImageIcon("images/Background/trees_back.png").getImage();
		treesFore = new ImageIcon("images/Background/trees_fore.png").getImage();
		heart = new ImageIcon("images/HUD/heart.png").getImage();
		health = new ImageIcon("images/HUD/health.png").getImage();
		diamondIcon = new ImageIcon("images/HUD/diamondIcon.png").getImage();
		window = new ImageIcon("images/HUD/window.png").getImage();
		
		tmap2 = new TileMap();
		tmap2.loadMap("maps", "map2.txt");
		tmapBackdrop2 = new TileMap();
		tmapBackdrop2.loadMap("maps", "backdropMap2.txt");
	
		
		player_idle = new Animation();
		player_idle.loadAnimationFromSheet("images/Sprite/idle.PNG", 1, 1, 60);
	    player_right = new Animation();
	    player_right.loadAnimationFromSheet("images/Sprite/run_right.PNG", 4, 1, 60);
	    player_left = new Animation();
	    player_left.loadAnimationFromSheet("images/Sprite/run_left.PNG", 4, 1, 60);
	    player_jump = new Animation();
	    player_jump.loadAnimationFromSheet("images/Sprite/jump.PNG", 1, 1, 60);
	    player_attack = new Animation();
	    player_attack.loadAnimationFromSheet("images/Sprite/attack.PNG", 1, 1, 60);
	     
	    player = new Player(player_idle);
	    player.setAnimationSpeed(1.0f);
	    player.setVelocityX(0.0f);
	    player.setVelocityY(0.0f);
	    player.setX(64);
	    player.setY(250);
	    player.setHealth(player.getHealth());
	    player.setLives(player.getLives());
	    player.show();
	    
	    addConsumables();
		addEnemies();
		fireProjectile();
		
		level_flag = new Animation();
		level_flag.loadAnimationFromSheet("images/Sprite/flag.PNG", 1, 1, 60);
		flag = new Sprite(level_flag);
		flag.setVelocityX(0);
		flag.setVelocityY(0);
		flag.setX(2015);
		flag.setY(240);	
		flag.show();	
	}
	
	public void addConsumables() {
		
		consumables = new ArrayList<Consumables>();
		
		Point[] position = new Point[] {
				new Point(1968,48),
				new Point(1360,48),
				new Point(848,240),
				new Point(688,144),
				new Point(1008,80),
				new Point(1104,240),
				new Point(336,208),
				new Point(1648,208),
			};
		
		consum = new Animation();
		consum.loadAnimationFromSheet("images/Sprite/consumable.png", 1, 1, 60);
		
		for(int i = 0; i < position.length; i++) {
			
			Consumables diamond = new Consumables(consum);
			diamond.setX(position[i].x);
			diamond.setY(position[i].y);
			diamond.show();
			consumables.add(diamond);
			
		}
	}
	
	public void addEnemies() {
		
		enemies = new ArrayList<Enemy>();
		
		Point[] position = new Point[] {
				new Point(480,270),
				new Point(930,140),
				new Point(1200,270),
				new Point(1760,270),
				new Point(1900,110)
			};
		
		skeleton_right = new Animation();
		skeleton_right.loadAnimationFromSheet("images/Sprite/skeleton.png", 4, 1, 60);
		skeleton_left = new Animation();
		skeleton_left.loadAnimationFromSheet("images/Sprite/skeleton_left.png", 4, 1, 60);
		skeleton_left.setAnimationSpeed(0.3f);
	
		for(int i = 0; i < position.length; i++) {
			
			skeleton = new Skeleton(skeleton_right);
			skeleton.setAnimationSpeed(0.3f);
			skeleton.setX(position[i].x);
			skeleton.setY(position[i].y);
			skeleton.setVelocityX(0.00f);
			skeleton.setVelocityY(0.0f);
			skeleton.show();
			enemies.add(skeleton);	
		}

	}
	
	public void checkSprites(long elapsed) {
		
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
		
			if(spriteBounds(player).intersects(spriteBounds(e))) {
				player.hit(e.getDamage());
				if(player.getHealth() == 0) { dead(); }
				
				if(right) {
					player.setVelocityX(-1.1f);
				}
			    if(left) {
					player.setVelocityX(1.1f);
				}
				if(jumping) {
					player.setVelocityY(1.1f);
				}
				if(falling) {
					player.setVelocityX(-1.1f);
				}
				Sound damage = new Sound("sounds/damage.wav");
				damage.start();
				
			}
			
			for(int j = 0; j < fireAttack.size(); j++) {
				FireAttack fa = fireAttack.get(j);
				if(fireBounds(fa).intersects(spriteBounds(e))) {
					fireAttack.remove(j);
					j--;
					e.hit(fa.getFireDamage());
				}
			}
		}
		
		for(int i = 0; i < consumables.size(); i++) {
			 Consumables c = consumables.get(i);
			 
			 if(spriteBounds(player).intersects(spriteBounds(c))) {
				 score++;
				 consumables.remove(i);
				 i--;
				 Sound collect = new Sound("sounds/collect.wav");
				 collect.start();
			}
		}
		
		if(spriteBounds(player).intersects(spriteBounds(flag))) {
			Echo portal = new Echo("sounds/portal.wav");
			portal.start();
			levelManager.setLevel(LevelManager.levelOver);
		}
	}
	
	public void fireProjectile() {
	        System.out.println(getMouseX() + "," + getMouseY());
	       
	        fireAttack = new ArrayList<FireAttack>();
	        
	        float dest_x = getMouseX();
	        float dest_y = getMouseY();
	        float temp_x = player.getX() + xOFF;
	        float temp_y = player.getY() + yOFF;
	             
	        fire_attack = new Animation();
	        fire_attack.loadAnimationFromSheet("images/Sprite/fireAttack.png",1, 1, 60);
	         
	        Velocity velocity = new Velocity();
	        velocity.setVelocity(0.0, 0.0);

	        FireAttack fire = new FireAttack(fire_attack);
	        fire.setX(temp_x);
	        fire.setY(temp_y);
	        float deltaX = dest_x - temp_x;
	        float detlaY = dest_y - temp_y;
	        double rad = Math.atan2(detlaY, deltaX);
	        double angle = rad * (180 / Math.PI);
	        velocity.setVelocity(0.5f, angle);
	        fire.setVelocityX((float)velocity.getdx());
	        fire.setVelocityY((float)velocity.getdy());
	        fire.show();
	        
	        if(dest_x > 0 && dest_y > 0) {
	        	fireAttack.add(fire); 
	        	Sound fireball = new Sound("sounds/fireball.wav");
		     	fireball.start();
		    }   
	        System.out.println(fire.getVelocityX()+"."+fire.getVelocityY());
	    }
	
	public void playerMovement(long elapsed) {
	   player.setVelocityY(player.getVelocityY()+(gravity*elapsed));
		
		if(right) {
			player.setVelocityX(0.1f);
			player.setAnimation(player_right);
		} 
		else if(left) {
			player.setVelocityX(-0.1f);
			player.setAnimation(player_left);
		}
		else {
			if(player.getVelocityX() > 0) {
				player.setVelocityX(0);
				player.setAnimation(player_idle);
			}
			if(player.getVelocityX() < 0) {
				player.setVelocityX(0);
				player.setAnimation(player_idle);
			}
		} 
		
		if(jumping && !falling) {
			player.setVelocityY(-2.3f);
			player.setAnimation(player_jump);
			player.setAnimationSpeed(0.03f);
			Echo jump = new Echo("sounds/jump.wav");
	     	jump.start();
			falling = true;
		}
		else if(falling) {
			player.setVelocityY(0.1f);
		}
		
		if(attacking) {
			 player.setAnimation(player_attack);
			 fireProjectile();
		 }
		 else {
			 if(!attacking && !left && !right && !jumping) {
				 player.setAnimation(player_idle);
			 }
		 }
	 }
	
	public void update(long elapsed) {
		
		if(paused) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			player.update(elapsed);
			playerMovement(elapsed);
			collision(player, elapsed);
			checkSprites(elapsed); 
			//enemyMovement(elapsed);
			
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = enemies.get(i);
				e.update(elapsed);
				collision(e, elapsed);
				
				if(e.getVelocityX() == 0 && e.getAnimation().equals(skeleton_right)) {
					e.setVelocityX(-0.05f);
					e.setAnimation(skeleton_left);
				}
				if(e.getVelocityX() == 0 && e.getAnimation().equals(skeleton_left)) {
					e.setVelocityX(0.05f);
					e.setAnimation(skeleton_right);
				}
				if(e.isDead()) {
					Sound enemyDeath = new Sound("sounds/enemyDeath.wav");
					enemyDeath.start();
					enemies.remove(i);
					i--;
				}
			}
		
		    for (int i = 0; i < fireAttack.size(); i++) {
		    		FireAttack fa = fireAttack.get(i);
		  			fa.update(elapsed);
//		  			collision(fa, elapsed);
//		  			if(fa.getVelocityX() == 0 && fa.getVelocityY() == 0) {
//		  				fireAttack.remove(i);
//		  				i--;
//		  			}
		    }
		    
		    for (int i = 0; i < consumables.size(); i++) {
	    		Consumables c = consumables.get(i);
	  			c.update(elapsed);
		    }	
			
		}
   }
	

	public void draw(Graphics2D g) {
		
		xOFF = game.WIDTH/2 - (int)(player.getX());
        xOFF = Math.min(xOFF, 0);
        xOFF = Math.max(game.WIDTH - tmap2.getPixelWidth(), xOFF);

        yOFF = game.HEIGHT/2 - (int)(player.getY());
        yOFF = Math.min(yOFF, 0);
        yOFF = Math.max(game.HEIGHT - tmap2.getPixelHeight(), yOFF);
        
        int moveScaleBG = (int) (xOFF * 0.5);
        int moveScaleTB = (int) (xOFF * 1.0);
        int moveScaleTF = (int) (xOFF * 1.5);
        
        for(int x = moveScaleBG; x < game.WIDTH * 4; x += game.WIDTH) {
        	g.drawImage(background, x, yOFF, game.WIDTH, game.HEIGHT, null);
        }
        for(int x = moveScaleTB; x < game.WIDTH * 4; x += game.WIDTH) {
        	g.drawImage(treesBack, x, yOFF, game.WIDTH, game.HEIGHT - 32, null);
        }
        for(int x = moveScaleTF; x < game.WIDTH * 4; x += game.WIDTH) {
        	g.drawImage(treesFore, x, yOFF, game.WIDTH, game.HEIGHT - 32, null);
        }
        
        tmap2.draw(g, xOFF, yOFF);
        tmapBackdrop2.draw(g, xOFF, yOFF);
       
	    player.setOffsets(xOFF, yOFF);
		player.draw(g);
		g.setColor(Color.yellow);
//		g.draw(spriteBounds(player));
		
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.setOffsets(xOFF, yOFF);
			e.draw(g);
//			g.draw(spriteBounds(e));
		}
		
		for(int i = 0; i < fireAttack.size(); i++) {
			FireAttack f = fireAttack.get(i);
			f.draw(g);
//			g.draw(fireBounds(f));
		}
		
		for(int i = 0; i < consumables.size(); i++) {
			Consumables c = consumables.get(i);
			c.setOffsets(xOFF, yOFF);
			c.draw(g);
//			g.draw(spriteBounds(c));
			}
		
		for(int i = 0; i < player.getLives(); i++) {
			g.drawImage(heart, 10 + i * 20, 35, null);
		}
		
		for(int i = 0; i < player.getHealth(); i++) {
			g.drawImage(health, 10 + i * 20, 55, null);
		}
		
		flag.setOffsets(xOFF, yOFF);
		flag.draw(g);
		
		g.drawImage(window, 415, 38, null);
	    g.drawImage(diamondIcon, 396, 20, null);
	    g.setFont(new Font("Arial", Font.PLAIN, 15));
		g.drawString(" " + score, 455, 59);
		
		
		if(paused) {
			Color transparant = new Color(128, 128, 128, 127);
			g.setColor(transparant);
			g.fillRect(0, 0, game.WIDTH, game.HEIGHT);
			g.setColor(Color.WHITE);
		    g.setFont(new Font("Arial", Font.PLAIN, 24));
			g.drawString("Game Paused", 180, 140);
			g.setFont(new Font("Arial", Font.PLAIN, 16));
			g.drawString("Press Enter to Resume Game", 150, 200);
		}
	}

	public void collision(Sprite s, long elapsed) {
				
		  if (s.getX() < 0) {
	            s.setVelocityX(Math.abs(s.getVelocityX()));
	        }
	        else if (s.getX() + s.getWidth() >= tmap2.getPixelWidth())
	        {
	            s.setVelocityX(-Math.abs(s.getVelocityX()));
	        }
	        if (s.getY() < 0) {
	            s.setVelocityY(Math.abs(s.getVelocityY()));
	        }
	        else if (s.getY() + s.getHeight() >= tmap2.getPixelHeight())
	        {
	            s.setVelocityY(-Math.abs(s.getVelocityY()));
	        }
	
	        currentColumn = (int) s.getX() / tileSize;
			currentRow = (int) s.getY() / tileSize;
			
			xtemp = s.getX();
			ytemp = s.getY();
			
		    yc = tmap2.getTileYC(currentColumn,currentRow);
			xc = tmap2.getTileXC(currentColumn,currentRow);
		
			int leftTile = (int) xtemp /  tileSize;
			int rightTile = (int)(xtemp + s.getWidth()) / tileSize;
			int topTile = (int) ytemp / tileSize;
			int bottomTile = (int)(ytemp + s.getHeight()) / tileSize;
			
			char topLeft = tmap2.getTileChar(leftTile,topTile);
			String topLeftTile = String.valueOf(topLeft);
			
			char topRight = tmap2.getTileChar(rightTile,topTile);
			String topRightTile = String.valueOf(topRight);
			
			char bottomRight = tmap2.getTileChar(rightTile,bottomTile);
			String bottomRightTile = String.valueOf(bottomRight);
			
			char bottomLeft = tmap2.getTileChar(leftTile,bottomTile);
			String bottomLeftTile = String.valueOf(bottomLeft);
			
			if(s.getVelocityX() > 0) {
				if(!topRightTile.equals(".") && !bottomRightTile.equals(".")) {
					s.setVelocityX(0);
					s.setX(xc + (s.getWidth() / 2));
				}
			}
			
			if(s.getVelocityX() < 0) {
				if(!topLeftTile.equals(".") && !bottomLeftTile.equals(".")) {
					s.setVelocityX(0);
					s.setX((xc + tileSize + (tileSize - s.getWidth())) - (s.getWidth() / 2));
					
				}
			}
			
			if(s.getVelocityY() > 0) {
				if(!bottomLeftTile.equals(".") || !bottomRightTile.equals(".")) {
					s.setVelocityY(0);
					falling = false;
					s.setY(yc + (s.getHeight() / 2));
		
				}
			}
			
			if(s.getVelocityY() < 0) {
				if(!topLeftTile.equals(".") || !topRightTile.equals(".")) {
					s.setVelocityY(0);
					s.setY(yc + tileSize - (s.getHeight() / 2));
				}
			}
		
			
			if(s.equals(player)) {
					if(bottomLeftTile.equals("b") || bottomRightTile.equals("b")) {
						Sound lavaDeath = new Sound("sounds/lavaDeath.wav");
						lavaDeath.start();
						dead();
					}
			}
			
	}
	
	public void dead() {
		player.setDead();
		
		if(player.getLives() == 0) {
		    Sound gameOver = new Sound("sounds/gameOver.wav");
			gameOver.start();
			levelManager.setLevel(LevelManager.levelMenu);	
		}
		else {
			player.loseLife();
			reset();
		}
	}
	
	public void reset() {
		player.stop();
		player.reset();
		player.setX(64);
		player.setY(250);
		score = 0;
		addEnemies();
	    addConsumables();
	}


	public Rectangle.Float spriteBounds(Sprite s) {
		return new Rectangle.Float((s.getX()) + xOFF,
							 (s.getY()) + yOFF,
							 s.getWidth(), s.getHeight());
		} 
	
	public Rectangle.Float fireBounds(Sprite s) {
		return new Rectangle.Float((s.getX()),
							 (s.getY()),
							 s.getWidth(), s.getHeight());
		} 
	
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A) { left = true; }
		if(k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D) { right = true; }
		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_W) { jumping = true;  }
		if(k == KeyEvent.VK_ESCAPE) { System.exit(0); }
		if(k == KeyEvent.VK_P) { paused = true; }
	}
	
	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A) { left = false; }
		if(k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D) { right = false; }
		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_W) { jumping = false; }
		if(k == KeyEvent.VK_ENTER) { paused = false; }
	}
	
    public int getMouseX() { return mouseX; }
    public int getMouseY() { return mouseY; }
	    
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
	    mouseY = e.getY();
	    attacking = true;
	}
	
	public void mouseReleased(MouseEvent e) { attacking = false; }   
	public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
		   
}
