package components.collision;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Az oszt�ly felel�s az �tk�z� r�szek nyilv�ntart�s��rt. A Collision oszt�ly ezt haszn�lja.
 * 
 * 
 * @author �cs �d�m
 *
 */
public class BoundingPart {
	private List<Rectangle> rects = new ArrayList<Rectangle>();

	/**
	 * l�trehozza az objektumot egy t�glalappal
	 * @param rect
	 */
	public BoundingPart(Rectangle rect) {
		rects.add(rect);
	}

	/**
	 * l�trehozza az objektumot t�glalapok egy list�j�val
	 * @param rects
	 */
	public BoundingPart(List<Rectangle> rects) {
		this.rects = rects;
	}

	/**
	 * visszaadja a meghat�roz� t�glalapokat
	 * @return
	 */
	public List<Rectangle> getRects() {
		return rects;
	}

	/**
	 * kivesz egy t�glalapot az �tk�z�k k�z�l
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
