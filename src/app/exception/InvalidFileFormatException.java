package app.exception;

public class InvalidFileFormatException extends Exception {

	private static final long serialVersionUID = -4649086203802798584L;

	public InvalidFileFormatException() {
		super();
	}

	public InvalidFileFormatException(String message) {
		super(message);
	}

}
