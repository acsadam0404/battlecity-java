package objects.tiles;

/**
 * 
 * @author Ács Ádám
 *
 */
public class ENoSuchTileException extends Exception {
	@Override
	public String toString() {
		return "Nincs ilyen Tile regisztrálva.";
	}
}
