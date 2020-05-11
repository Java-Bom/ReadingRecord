package Chap10_Exceptions.item74;

public class HighLevelException extends Exception {
	public HighLevelException(Throwable cause) {
		super(cause);
	}

	public HighLevelException(String message) {
		super(message);
	}
}
