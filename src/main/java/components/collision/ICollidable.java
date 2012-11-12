package components.collision;

/**
 * Az �tk�z�sek vizsg�lat�hoz implement�lni kell ezt az interfacet.
 * @author �cs �d�m
 *
 */
public interface ICollidable {
	/**
	 * visszaadja az �tk�z� r�szt
	 * @return
	 */
	public BoundingPart getBoundingPart();
	
	/**
	 * megmondja egy objektumr�l, hogy �tk�zhet-e
	 * @return
	 */
	public boolean isCollidable();
	
	/**
	 * megmondja egy objektumr�l, hogy a goly� �tmehet-e rajta
	 * @return
	 */
	public boolean isPassableByBullet();

}
