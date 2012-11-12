package objects;

/**
 * Inicializáláskor a fájlokban nyúlkál a program, ez nem mindig sikerül, akkor ilyet dobunk. 
 * Késõbb ezt elkaphatja a fõprogram és kidobhat valami error screen-t.
 * 
 * @author Ács Ádám
 *
 */
public class EInitException extends Exception {
	protected String message;
	
	/**
	 * létrehozza az exceptiont egy másik alapján
	 * @param ex
	 */
	public EInitException(Exception ex) {
		message =  ex.getMessage();
	}
	
	/**
	 * létrehozza az exception-t alapértelmezett üzenettel
	 */
	public EInitException() {
		message = "Hiba lépett fel az inicializáláskor";
	}
	
	/**
	 * visszaadja az üzenetet
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
