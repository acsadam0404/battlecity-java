package components.animation;

/**
 * Ha az anim�ci�k xml f�ljai nem l�teznek, ilyen exception-t dobunk.
 * @author �cs �d�m
 *
 */
public class ENoAnimationFoundException extends RuntimeException {
	private String message;
	
	/**
	 * konstruktor ami egy stack trace-t kap
	 * @param name
	 */
	public ENoAnimationFoundException(String name) {
		message = "Nem tal�lhat� ilyen n�ven anim�ci�: " + name;
	}
	
	/**
	 * visszaadja az �zenetet
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
