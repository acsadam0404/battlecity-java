package components.collision;

import game.Config;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import math.Vector2;
import objects.Sprite;

/**
 * Ütközésvizsgálatokat megvalósító osztály.
 * 
 * @author Ács Ádám
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
	 * Megmondja két BoundingPart objektumról, hogy ütköznek-e.
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
	 * Megmondja egy objektumról, hogy a képernyõn kívül van-e. A Config osztály
	 * beállításait használja.
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isOutOfScreen(ICollidable obj) {
		return isOutOfScreen(obj, null);
	}

	/**
	 * Megmondja egy objektumról hogy az offset-el megadott pozícióváltozás után
	 * a képernyõn kívül lesz-e. A Config osztály beállításait használja.
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
	 * Megmondja két téglalapról, hogy ütköznek-e.
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
	 * megmondja egy spriteról, hogy ütközik e a listában felsorolt többi sprite egyikével. Mivel egyszerre többel is ütközhet
	 * visszaad egy listát az ütközõ sprite-okról
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
	 * megmondja, hogy egy boundingpart ütközik-e a listában levõ sprite-ok valamelyikével.
	 * Visszaadja az éppen vele ütközõ sprite-ok listáját. 
	 * A metódusra azért van szükség, hogy ne csak Sprite-ok tudjanak ütközést vizsgálni, hanem bármi aminek van boundingpartja
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
	 * Megmondja egy spriteról, hogy a jelenlegi helyzetétõl offset-el eltolva ütközne-e valamivel a listában levõ sprite-ok közül.
	 * Ha igen, visszaadja a lehetséges ütközõ sprite-okat.
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
