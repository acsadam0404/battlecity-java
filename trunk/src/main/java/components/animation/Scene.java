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

	public Scene(BufferedImage pic, long endTime) {
		this.pic = pic;
		this.picCopy = pic;
		this.endTime = endTime;
	}
	
	public final BufferedImage getPicCopy() {
		return picCopy;
	}
}