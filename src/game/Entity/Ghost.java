package game.Entity;

import game.Engine.*;

public class Ghost extends Enemy {

	public Ghost(Animation anim) {
		super(anim);
		health = 2;
		damage = 1;
	}
}
