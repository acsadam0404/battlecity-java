package components.animation;

import java.awt.image.BufferedImage;

/**
 * egy képet és egy idõtartamot tárol
 * @author Ács Ádám
 * 2012.07.29.
 */
public class Scene{
	BufferedImage pic;
	long endTime;
	private BufferedImage picCopy; /* azért kell, tudjak pic-nek null-t adni, de ne veszítsem el az értékét */

	/**
	 * egy képpel és intervallummal létrehozza az objektumot és elmenti a kép másolatát
	 * @param pic
	 * @param endTime
	 */
	public Scene(BufferedImage pic, long endTime) {
		this.pic = pic;
		this.picCopy = pic;
		this.endTime = endTime;
	}
	
	/**
	 * visszaadja a kép másolatát
	 * @return
	 */
	public final BufferedImage getPicCopy() {
		return picCopy;
	}
}