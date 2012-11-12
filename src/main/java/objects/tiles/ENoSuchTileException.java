package objects.tiles;

/**
 * Ha olyan Tile-t kérünk, ami még nincs regisztálva, ilyen kivételt fogunk kapni.
 * @author Ács Ádám
 *
 */
public class ENoSuchTileException extends Exception {
	/**
	 * Visszaadja az exception string reprezentációját.
	 */
	@Override
	public String toString() {
		return "Nincs ilyen Tile regisztrálva.";
	}
}
