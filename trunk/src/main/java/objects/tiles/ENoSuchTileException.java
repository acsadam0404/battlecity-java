package objects.tiles;

/**
 * 
 * @author �cs �d�m
 *
 */
public class ENoSuchTileException extends Exception {
	@Override
	public String toString() {
		return "Nincs ilyen Tile regisztr�lva.";
	}
}
