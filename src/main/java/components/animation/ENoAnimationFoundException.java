package components.animation;

/**
 * Ha az animációk xml fáljai nem léteznek, ilyen exception-t dobunk.
 * @author Ács Ádám
 *
 */
public class ENoAnimationFoundException extends RuntimeException {
	private String message;
	
	/**
	 * konstruktor ami egy stack trace-t kap
	 * @param name
	 */
	public ENoAnimationFoundException(String name) {
		message = "Nem található ilyen néven animáció: " + name;
	}
	
	/**
	 * visszaadja az üzenetet
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
