package game;

/**
 * Az implementáló objektumoknak van kezdeti állapota, amit vissza lehet állítani.
 * 
 * @author Ács Ádám
 *
 */
public interface IResetable {
	/**
	 * visszaállítja az objektumot a kezdõ állapotra
	 */
	public void reset();
}
