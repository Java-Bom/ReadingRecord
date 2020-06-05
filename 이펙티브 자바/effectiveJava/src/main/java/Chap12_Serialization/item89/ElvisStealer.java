package Chap12_Serialization.item89;

import java.io.Serializable;

public class ElvisStealer implements Serializable {
	static Elvis impersonator;
	private Elvis payload;

	private Object readResolve(){
		impersonator = payload;
		return new String[] {"A Fool Such as I"};
	}
	private static final long serialVersionUID
}
