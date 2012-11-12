package components.animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.IGameLoop;

/**
 * Az anim�ci�k megjelen�t�s��rt felel�s oszt�ly. Ha egyszer� k�pet akarunk, akkor is lehet haszn�lni, csak 1 k�pet kell megadni neki.
 * L�trehoz�sa ut�n b�rmikor adhatunk neki �jabb k�peket.
 *
 * @author �cs �d�m
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
	 * �gy is l�trehozhatjuk, de k�s�bb adni kell neki egy IAnimatable obkektumot, hogy m�k�dj�n 
	 */
	public Animation() {
		this(null); /* XXX f�lk�sz objektum lesz */
	}

	/**
	* Az anim�ci� konstruktora, ami megmondja, hogy h�nyszor j�tsz�djon le az anim
	*/
	public Animation(int maxCount) {
		this(null);
		this.maxCount = maxCount;
	}

	/**
	* konstruktor, ami megmondja, hogy h�nyszor j�tsz�djon le az anim �s a container-t is be�ll�tja
	*/
	public Animation(IAnimatable container, int maxCount) {
		this.container = container;
		this.maxCount = maxCount;
	}

	/**
	 * be�ll�tja az anim�ci�hoz tartoz� objektumot.
	 * @param container
	 */
	public void setContainer(IAnimatable container) {
		this.container = container;
	}

	/** 
	 * hozz�ad egy jelenetet a jelenet list�hoz
	 * a jelenet tulajdonk�ppen egy k�p �s egy id�tartam
	 */
	public synchronized void addScene(BufferedImage i, long t) {
		totalTime += t;
		scenes.add(new Scene(i, totalTime));
	}

	/**
	 * be�ll�tja az anim�ci�t az elej�re
	 */
	public synchronized final void start() {
		movieTime = 0;
		sceneIndex = 0;
		running = true;
		scenes.get(0).pic = scenes.get(0).getPicCopy();
	}

	/**
	 * le�ll�tja az anim�ci�t
	 */
	public void stop() {
		running = false;
		scenes.get(0).pic = null;
	}

	/**
	 * visszaadja a k�pet
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
	 * visszaadja a scene lista x. elem�t
	 * @param x
	 * @return
	 */
	protected Scene getScene(int x) {
		return scenes.get(x);
	}

	/**
	 * az elej�re �ll�tja �s elind�tja az anim�ci�t
	 */
	@Override
	public void init() {
		count = 0;
		start();
	}

	/**
	 * jelenetet l�ptet �s v�r.
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
	 * kirajzolja az anim�ci�t.
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
	 * forgat�s�rt felel�s met�dus
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
	 * az anim�ci� framejeinek sz�ma
	 * @return
	 */
	public final int getMaxCount() {
		return maxCount;
	}

	/**
	 * be�ll�tja az anim�ci� framejeinek sz�m�t (nem lehet kisebb mint 0)
	 * @param maxCount
	 */
	public final void setMaxCount(int maxCount) {
		if (maxCount < 1) {
			throw new IllegalArgumentException("maxcount cant be < 1");
		}

		this.maxCount = maxCount;
	}

	/**
	 * megmondja az anim�ci�r�l, hogy �pp fut-e
	 * @return
	 */
	public final boolean isRunning() {
		return running;
	}

	/**
	 * be�ll�t egy affin transzform�ci�t
	 * @param affineTransform
	 */
	public final void setAffineTransform(AffineTransform affineTransform) {
		this.affineTransform = affineTransform;
	}

	/**
	 * visszaadja a k�p k�z�ppontj�nak x koordin�t�j�t
	 * @return
	 */
	public double getImageCenterX() {
		return container.getPos().getX() + getImage().getWidth() / 2;
	}

	/**
	 * visszaadja a k�p k�z�ppontj�nak y koordin�t�j�t
	 * @return
	 */
	public double getImageCenterY() {
		return container.getPos().getY() + getImage().getHeight() / 2;
	}
}
