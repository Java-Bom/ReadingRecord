package Chap10_Exceptions.item74;

import org.junit.jupiter.api.Test;

class LowLevelExceptionTest {
	@Test
	void name2() throws HighLevelException {
		try {
			throwException();
		} catch (LowLevelException e) {
			throw new HighLevelException("highLevelException Message");
		}
	}

	@Test
	void name() throws Throwable {
		try {
			throw new LowLevelException();
		} catch (LowLevelException e) {
			throw new HighLevelException("message").initCause(e);
		}
	}

	void throwException() throws LowLevelException {
		throw new LowLevelException();
	}
}