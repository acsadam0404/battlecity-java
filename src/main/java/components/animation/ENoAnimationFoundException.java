package components.animation;

/**
 * 
 * @author Ács Ádám
 *
 */
public class ENoAnimationFoundException extends RuntimeException {
	private String message;
	public ENoAnimationFoundException(String name) {
		message = "Nem található ilyen néven animáció: " + name;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
