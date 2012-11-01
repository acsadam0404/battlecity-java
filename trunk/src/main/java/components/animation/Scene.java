package components.animation;

import java.awt.image.BufferedImage;

/**
 * egy k�pet �s egy id�tartamot t�rol
 * @author �cs �d�m
 * 2012.07.29.
 */
public class Scene{
	BufferedImage pic;
	long endTime;
	private BufferedImage picCopy; /* az�rt kell, tudjak pic-nek null-t adni, de ne vesz�tsem el az �rt�k�t */

	public Scene(BufferedImage pic, long endTime) {
		this.pic = pic;
		this.picCopy = pic;
		this.endTime = endTime;
	}
	
	public final BufferedImage getPicCopy() {
		return picCopy;
	}
}