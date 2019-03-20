package commoncom.pmenu.comon.exception;

public class PerferredMenuException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4576550533073003340L;
	protected ExceptionMessage message ;
	
	public PerferredMenuException(Integer code, String en, String cn) {
		this(new ExceptionMessage(code, en, cn));
	}

	public PerferredMenuException(ExceptionMessage message) {
		super(message.getEn());
		this.message = message;
				
	}
	
	public ExceptionMessage getMsMessage() {
		return this.message;
	}
}
