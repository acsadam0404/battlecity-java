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
	
	/**
	 * l�trehozza az exceptiont egy m�sik alapj�n
	 * @param ex
	 */
	public EInitException(Exception ex) {
		message =  ex.getMessage();
	}
	
	/**
	 * l�trehozza az exception-t alap�rtelmezett �zenettel
	 */
	public EInitException() {
		message = "Hiba l�pett fel az inicializ�l�skor";
	}
	
	/**
	 * visszaadja az �zenetet
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
