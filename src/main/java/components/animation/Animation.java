package components.animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.IGameLoop;

/**
 * Az animációk megjelenítéséért felelõs osztály. Ha egyszerû képet akarunk, akkor is lehet használni, csak 1 képet kell megadni neki.
 * Létrehozása után bármikor adhatunk neki újabb képeket.
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
	 * így is létrehozhatjuk, de késõbb adni kell neki egy IAnimatable obkektumot, hogy mûködjön 
	 */
	public Animation() {
		this(null); /* XXX félkész objektum lesz */
	}

	/**
	* Az animáció konstruktora, ami megmondja, hogy hányszor játszódjon le az anim
	*/
	public Animation(int maxCount) {
		this(null);
		this.maxCount = maxCount;
	}

	/**
	* konstruktor, ami megmondja, hogy hányszor játszódjon le az anim és a container-t is beállítja
	*/
	public Animation(IAnimatable container, int maxCount) {
		this.container = container;
		this.maxCount = maxCount;
	}

	/**
	 * beállítja az animációhoz tartozó objektumot.
	 * @param container
	 */
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

	/**
	 * leállítja az animációt
	 */
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

	/**
	 * az elejére állítja és elindítja az animációt
	 */
	@Override
	public void init() {
		count = 0;
		start();
	}

	/**
	 * jelenetet léptet és vár.
	 */
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

	/**
	 * kirajzolja az animációt.
	 */
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

	/**
	 * forgatásért felelõs metódus
	 * @param theta
	 */
	public void rotate(double theta) {
		BufferedImage source = getImage();
		if (source != null) {
			AffineTransform at = AffineTransform.getRotateInstance(theta, container.getPos().getX() + source.getWidth() / 2, container.getPos().getY() + source.getHeight() / 2);
			setAffineTransform(at);
		}
	}

	/**
	 * az animáció framejeinek száma
	 * @return
	 */
	public final int getMaxCount() {
		return maxCount;
	}

	/**
	 * beállítja az animáció framejeinek számát (nem lehet kisebb mint 0)
	 * @param maxCount
	 */
	public final void setMaxCount(int maxCount) {
		if (maxCount < 1) {
			throw new IllegalArgumentException("maxcount cant be < 1");
		}

		this.maxCount = maxCount;
	}

	/**
	 * megmondja az animációról, hogy épp fut-e
	 * @return
	 */
	public final boolean isRunning() {
		return running;
	}

	/**
	 * beállít egy affin transzformációt
	 * @param affineTransform
	 */
	public final void setAffineTransform(AffineTransform affineTransform) {
		this.affineTransform = affineTransform;
	}

	/**
	 * visszaadja a kép középpontjának x koordinátáját
	 * @return
	 */
	public double getImageCenterX() {
		return container.getPos().getX() + getImage().getWidth() / 2;
	}

	/**
	 * visszaadja a kép középpontjának y koordinátáját
	 * @return
	 */
	public double getImageCenterY() {
		return container.getPos().getY() + getImage().getHeight() / 2;
	}
}
