package components.animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.IGameLoop;

/**
 * 
 * @author Ács Ádám
 * 
 */
public class Animation implements IGameLoop {
	protected ArrayList<Scene> scenes = new ArrayList<Scene>();

	protected int sceneIndex;
	protected long movieTime;
	protected long totalTime;
	protected Graphics2D g2d;
	protected int count;
	protected int maxCount;
	protected boolean running = true;
	protected AffineTransform affineTransform;
	protected IAnimatable container;

	public Animation(IAnimatable container) {
		this(container, -1);
	}

	/**
	 * XXX ez félkész lesz akkor
	 * így is létrehozhatjuk, de késõbb adni kell neki egy IAnimatable obkektumot, hogy mûködjön 
	 */
	public Animation() {
		this(null);
	}

	public Animation(int maxCount) {
		this(null);
		this.maxCount = maxCount;
	}

	public Animation(IAnimatable container, int maxCount) {
		this.container = container;
		this.maxCount = maxCount;
	}

	public void setContainer(IAnimatable container) {
		this.container = container;
	}

	/** 
	 * hozzáad egy jelenetet a jelenet listához
	 * a jelenet tulajdonképpen egy kép és egy idõtartam
	 */
	public synchronized void addScene(BufferedImage i, long t) {
		totalTime += t;
		scenes.add(new Scene(i, totalTime));
	}

	/**
	 * beállítja az animációt az elejére
	 */
	public synchronized final void start() {
		movieTime = 0;
		sceneIndex = 0;
		running = true;
		scenes.get(0).pic = scenes.get(0).getPicCopy();
	}

	public void stop() {
		running = false;
		scenes.get(0).pic = null;
	}

	/**
	 * visszaadja a képet
	 * 
	 * @return
	 */
	public synchronized BufferedImage getImage() {
		if (scenes.size() == 0) {
			return null;
		}
		return getScene(sceneIndex).pic;
	}

	/**
	 * visszaadja a scene lista x. elemét
	 * @param x
	 * @return
	 */
	protected Scene getScene(int x) {
		return scenes.get(x);
	}

	@Override
	public void init() {
		count = 0;
		start();
	}

	@Override
	public void update(long gameTime) {
		if (running) {
			if (scenes.size() > 1) {
				movieTime += gameTime;

				if (movieTime >= totalTime) {
					count++;
					if (count == maxCount) {
						stop();
					}
					movieTime = 0;
					sceneIndex = 0;
				}

				while (movieTime > getScene(sceneIndex).endTime) {
					sceneIndex++;
				}
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		if (getImage() != null) {
			if (g2d == null && g != null) {
				g2d = (Graphics2D) g;
			}

			AffineTransform originalTransform = g2d.getTransform();

			BufferedImage source = getImage();

			if (affineTransform != null) {
				g2d.setTransform(affineTransform);
			}

			g.drawImage(source, container.getPos().getX(), container.getPos().getY(), null);

			g2d.setTransform(originalTransform);
		}
	}

	public void rotate(double theta) {
		BufferedImage source = getImage();
		if (source != null) {
			AffineTransform at = AffineTransform.getRotateInstance(theta, container.getPos().getX() + source.getWidth() / 2, container.getPos().getY() + source.getHeight() / 2);
			setAffineTransform(at);
		}
	}

	public final int getMaxCount() {
		return maxCount;
	}

	public final void setMaxCount(int maxCount) {
		if (maxCount < 1) {
			throw new IllegalArgumentException("maxcount cant be < 1");
		}

		this.maxCount = maxCount;
	}

	public final boolean isRunning() {
		return running;
	}

	public final void setAffineTransform(AffineTransform affineTransform) {
		this.affineTransform = affineTransform;
	}

	public double getImageCenterX() {
		return container.getPos().getX() + getImage().getWidth() / 2;
	}

	public double getImageCenterY() {
		return container.getPos().getY() + getImage().getHeight() / 2;
	}
}
