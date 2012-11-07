package components.collision;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Az osztály felelõs az ütközõ részek nyilvántartásáért. A Collision osztály ezt használja.
 * 
 * IMPROVE kör és shape alakú objektumok, ne csak rect
 * 
 * @author Ács Ádám
 *
 */
public class BoundingPart {
	private List<Rectangle> rects = new ArrayList<Rectangle>();

	public BoundingPart(Rectangle rect) {
		rects.add(rect);
	}

	public BoundingPart(List<Rectangle> rects) {
		this.rects = rects;
	}

	public List<Rectangle> getRects() {
		return rects;
	}

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
