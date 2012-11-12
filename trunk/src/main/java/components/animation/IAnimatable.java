package components.animation;

import math.Vector2;

/**
 * Az animációnak a megjelenéshez szüksége van egy pozícióra. Ezért, ha egy
 * objektum animál, akkor implementálnia kell ezt az interfacet.
 * 
 * @author Ács Ádám
 * 
 */
public interface IAnimatable {
	public Vector2 getPos();
}
