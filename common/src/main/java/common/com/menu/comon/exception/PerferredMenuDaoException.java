package commoncom.pmenu.comon.exception;

public class PerferredMenuDaoException extends PerferredMenuException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4576550533073003340L;
	
	public PerferredMenuDaoException(Integer code, String en, String cn) {
		super(code, en, cn);
	}

	public PerferredMenuDaoException(ExceptionMessage message) {
		super(message);
				
	}
}
