package components.animation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnimationFactory {
	protected static int width;
	protected static int height;
	protected static int count;
	protected static int perrow;
	protected static int rows;
	protected static int cols;
	protected static int posX;
	protected static int posY;
	protected static String mode;
	protected static String extension;
	protected static String fileName;
	protected static List<BufferedImage> images = new ArrayList<BufferedImage>();

	public static Animation createAnimation(IAnimatable animatable, String fileName, long time, int maxCount) {
		try {
			images.clear();
			StaXParser parser = new StaXParser();

			parser.readConfig(fileName);

			Animation anim = new Animation(maxCount);
			for (int i = 0; i < images.size(); i++) {
				anim.addScene(images.get(i), time);
			}

			return anim;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Animation createAnimation(IAnimatable animatable, String fileName, long time) {
		return createAnimation(animatable, fileName, time, -1);
	}

}
