package components.collision;

/**
 * Az ütközések vizsgálatához implementálni kell ezt az interfacet.
 * @author Ács Ádám
 *
 */
public interface ICollidable {
	/**
	 * visszaadja az ütközõ részt
	 * @return
	 */
	public BoundingPart getBoundingPart();
	
	/**
	 * megmondja egy objektumról, hogy ütközhet-e
	 * @return
	 */
	public boolean isCollidable();
	
	/**
	 * megmondja egy objektumról, hogy a golyó átmehet-e rajta
	 * @return
	 */
	public boolean isPassableByBullet();

}
