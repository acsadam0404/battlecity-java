package objects.tiles;

/**
 * Ha olyan Tile-t k�r�nk, ami m�g nincs regiszt�lva, ilyen kiv�telt fogunk kapni.
 * @author �cs �d�m
 *
 */
public class ENoSuchTileException extends Exception {
	/**
	 * Visszaadja az exception string reprezent�ci�j�t.
	 */
	@Override
	public String toString() {
		return "Nincs ilyen Tile regisztr�lva.";
	}
}
