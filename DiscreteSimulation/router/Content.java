package router;

/**
 * Content contains the data necessary for all messages.
 * 
 * @author Gio Carlo Cielo
 */
public interface Content {
	public Class<? extends Content> getType();
}
