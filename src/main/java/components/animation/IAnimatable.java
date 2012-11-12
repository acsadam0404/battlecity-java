package components.animation;

import math.Vector2;

/**
 * Az anim�ci�nak a megjelen�shez sz�ks�ge van egy poz�ci�ra. Ez�rt, ha egy
 * objektum anim�l, akkor implement�lnia kell ezt az interfacet.
 * 
 * @author �cs �d�m
 * 
 */
public interface IAnimatable {
	public Vector2 getPos();
}
