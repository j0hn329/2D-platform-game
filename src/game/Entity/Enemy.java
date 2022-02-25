package game.Entity;

import game.Engine.*;

public class Enemy extends Sprite {
	
	protected int health;
	protected int damage;
	protected boolean dead;
	protected boolean remove;

	public Enemy(Animation anim) {
		super(anim);
		remove = false;
		dead = false;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public boolean remove() {
		return remove;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void hit(int damage) {
		health -= damage;
		if(health == 0) { dead = true; }
		if(dead) { remove = true; }
	}
}
