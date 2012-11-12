package components.collision;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Az osztály felelõs az ütközõ részek nyilvántartásáért. A Collision osztály ezt használja.
 * 
 * 
 * @author Ács Ádám
 *
 */
public class BoundingPart {
	private List<Rectangle> rects = new ArrayList<Rectangle>();

	/**
	 * létrehozza az objektumot egy téglalappal
	 * @param rect
	 */
	public BoundingPart(Rectangle rect) {
		rects.add(rect);
	}

	/**
	 * létrehozza az objektumot téglalapok egy listájával
	 * @param rects
	 */
	public BoundingPart(List<Rectangle> rects) {
		this.rects = rects;
	}

	/**
	 * visszaadja a meghatározó téglalapokat
	 * @return
	 */
	public List<Rectangle> getRects() {
		return rects;
	}

	/**
	 * kivesz egy téglalapot az ütközõk közül
	 * @param rect
	 */
	public void removeRect(Rectangle rect) {
		if (rects != null && rect != null) {
			for (int i = rects.size() - 1; i >= 0; i--) {
				if (rects.get(i).equals(rect)) {
					rects.remove(i);
				}
			}
		}
	}
}
