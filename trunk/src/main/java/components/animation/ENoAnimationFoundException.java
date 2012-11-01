package components.animation;

/**
 * 
 * @author �cs �d�m
 *
 */
public class ENoAnimationFoundException extends RuntimeException {
	private String message;
	public ENoAnimationFoundException(String name) {
		message = "Nem tal�lhat� ilyen n�ven anim�ci�: " + name;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
