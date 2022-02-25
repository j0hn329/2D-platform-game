package game.Entity;

import game.Engine.*;

public class Player extends Sprite {
	
	protected int lives;
	protected int health;
	protected int maxHealth;
	protected boolean dead;

	 public Player(Animation anim) {
		 super(anim);
		 lives = 3;
		 maxHealth = health = 6;
	}
	 
	 public int getLives() {
		 return lives;
	 }
	 
	 public int getHealth() {
		 return health;
	 }
	 
	 public void setLives(int l) {
		 lives = l;
	 }
	 
	 public void setHealth(int h) {
		 health = h;
	 }
	 
	 public void hit(int damage) {
		 health -= damage;
		 if(health < 0) health = 0;
		 if(health == 0) dead = true;	
	}
	 
	 public void setDead() {
		 health = 0;
	 }
	 
	 public void loseLife() {
		 lives--;
	 }
	 
	 public void reset() {
		 health = maxHealth;
	}
 }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


