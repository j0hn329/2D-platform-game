package game.Entity;

import game.Engine.*;

public class Skeleton extends Enemy {

	public Skeleton(Animation anim) {
		super(anim);
		health = 2;
		damage = 1;
	}
}
