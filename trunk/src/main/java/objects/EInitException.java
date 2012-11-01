package objects;

/**
 * Inicializ�l�skor a f�jlokban ny�lk�l a program, ez nem mindig siker�l, akkor ilyet dobunk. 
 * K�s�bb ezt elkaphatja a f�program �s kidobhat valami error screen-t.
 * 
 * @author �cs �d�m
 *
 */
public class EInitException extends Exception {
	protected String message;
	public EInitException(Exception ex) {
		message =  ex.getMessage();
	}
	
	public EInitException() {
		message = "Hiba l�pett fel az inicializ�l�skor";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
