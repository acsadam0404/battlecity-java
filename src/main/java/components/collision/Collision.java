package components.collision;

import game.Config;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import math.Vector2;
import objects.Sprite;

/**
 * �tk�z�svizsg�latokat megval�s�t� oszt�ly.
 * 
 * @author �cs �d�m
 * @since 2012.09.21
 * 
 */
public final class Collision {
	/**
	 *  private constructor
	 */
	private Collision() {
		super();
	}

	/**
	 * Megmondja k�t BoundingPart objektumr�l, hogy �tk�znek-e.
	 * 
	 * @param bp1
	 * @param bp2
	 * @return
	 */
	public static boolean isColliding(BoundingPart bp1, BoundingPart bp2) {
		if (bp1 == null || bp2 == null) {
			return false;
		}

		List<Rectangle> rects1 = bp1.getRects();
		List<Rectangle> rects2 = bp2.getRects();
		for (Rectangle r1 : rects1) {
			for (Rectangle r2 : rects2) {
				if (r1.intersects(r2)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Megmondja egy objektumr�l, hogy a k�perny�n k�v�l van-e. A Config oszt�ly
	 * be�ll�t�sait haszn�lja.
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isOutOfScreen(ICollidable obj) {
		return isOutOfScreen(obj, null);
	}

	/**
	 * Megmondja egy objektumr�l hogy az offset-el megadott poz�ci�v�ltoz�s ut�n
	 * a k�perny�n k�v�l lesz-e. A Config oszt�ly be�ll�t�sait haszn�lja.
	 * 
	 * @param obj
	 * @param offset
	 * @return
	 */
	public static boolean isOutOfScreen(ICollidable obj, Vector2 offset) {
		BoundingPart bp = obj.getBoundingPart();
		for (Rectangle rect : bp.getRects()) {
			if (offset != null) {
				rect.x += offset.getX();
				rect.y += offset.getY();
			}

			if (rect.getX() < Config.SCREEN_OFFSET_X) {
				return true;
			}
			if (rect.getX() + rect.getWidth() > Config.SCREEN_WIDTH + Config.SCREEN_OFFSET_X) {
				return true;
			}
			if (rect.getY() < Config.SCREEN_OFFSET_Y) {
				return true;
			}
			if (rect.getY() + rect.getHeight() > Config.SCREEN_HEIGHT + Config.SCREEN_OFFSET_Y) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Megmondja k�t t�glalapr�l, hogy �tk�znek-e.
	 * 
	 * @param rect1
	 * @param rect2
	 * @return
	 */
	public static boolean intersects(Rectangle rect1, Rectangle rect2) {
		if (rect1 != null && rect2 != null) {
			return rect1.intersects(rect2);
		}
		return false;
	}

	/**
	 * megmondja egy spriter�l, hogy �tk�zik e a list�ban felsorolt t�bbi sprite egyik�vel. Mivel egyszerre t�bbel is �tk�zhet
	 * visszaad egy list�t az �tk�z� sprite-okr�l
	 * 
	 * @param obj
	 * @param others
	 * @return
	 */
	public static List<Sprite> intersects(Sprite obj, List<Sprite> others) {
		List<Sprite> collidingSprites = new ArrayList<Sprite>();
		BoundingPart objBound = obj.getBoundingPart();
		for (Sprite other : others) {
			if (obj != other && isColliding(objBound, other.getBoundingPart())) {
				collidingSprites.add(other);
			}
		}
		return collidingSprites;
	}

	/**
	 * megmondja, hogy egy boundingpart �tk�zik-e a list�ban lev� sprite-ok valamelyik�vel.
	 * Visszaadja az �ppen vele �tk�z� sprite-ok list�j�t. 
	 * A met�dusra az�rt van sz�ks�g, hogy ne csak Sprite-ok tudjanak �tk�z�st vizsg�lni, hanem b�rmi aminek van boundingpartja
	 * 
	 * @param obj
	 * @param others
	 * @return
	 */
	public static List<Sprite> intersects(BoundingPart obj, List<Sprite> others) {
		List<Sprite> collidingSprites = new ArrayList<Sprite>();
		for (Sprite other : others) {
			if (isColliding(obj, other.getBoundingPart())) {
				collidingSprites.add(other);
			}
		}
		return collidingSprites;
	}

	public static List<Sprite> intersects(Rectangle obj, List<Sprite> others) {
		List<Sprite> collidingSprites = new ArrayList<Sprite>();
		for (Sprite other : others) {
			List<Rectangle> otherRects = other.getBoundingPart().getRects();
			for (Rectangle rect : otherRects) {
				if (intersects(obj, rect)) {
					collidingSprites.add(other);
				}
			}
		}
		return collidingSprites;
	}

	/**
	 * Megmondja egy spriter�l, hogy a jelenlegi helyzet�t�l offset-el eltolva �tk�zne-e valamivel a list�ban lev� sprite-ok k�z�l.
	 * Ha igen, visszaadja a lehets�ges �tk�z� sprite-okat.
	 * 
	 * @param obj
	 * @param others
	 * @return
	 */
	public static List<Sprite> intersects(Sprite obj, List<Sprite> others, Vector2 offset) {
		List<Sprite> collidingSprites = new ArrayList<Sprite>();
		List<Rectangle> rects = obj.getBoundingPart().getRects();
		for (Rectangle rect : rects) {
			Rectangle offsetRect = new Rectangle(rect.x + offset.getX(), rect.y + offset.getY(), rect.width, rect.height);
			for (Sprite other : others) {
				if (obj != other && other.isCollidable()) {
					List<Rectangle> otherRects = other.getBoundingPart().getRects();
					for (Rectangle otherRect : otherRects) {
						if (intersects(offsetRect, otherRect)) {
							collidingSprites.add(other);
						}
					}
				}
			}
		}

		return collidingSprites;
	}
}
