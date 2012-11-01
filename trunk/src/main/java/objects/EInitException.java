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
	public EInitException(Exception ex) {
		message =  ex.getMessage();
	}
	
	public EInitException() {
		message = "Hiba lépett fel az inicializáláskor";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
