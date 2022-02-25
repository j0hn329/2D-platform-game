package game.Entity;

import game.Engine.Animation;
import game.Engine.Sprite;

public class FireAttack extends Sprite {

	protected boolean remove;
	protected int fireDamage;

	public FireAttack(Animation anim) {
		super(anim);
		fireDamage = 1;
		remove = false;
	}
	
	public int getFireDamage() {
		return fireDamage;
	}
	
	public boolean remove() {
		return remove;
	}
}
