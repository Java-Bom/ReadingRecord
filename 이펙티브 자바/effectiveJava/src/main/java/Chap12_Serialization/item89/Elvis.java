package Chap12_Serialization.item89;

import java.io.Serializable;
import java.util.Arrays;

public class Elvis implements Serializable {
	public static final Elvis INSTANCE = new Elvis(); // transient를 붙여주지 않았다.
	private Elvis(){}
	private String[] favoriteSongs = {"a", "b"};
	public void printFavorites(){
		System.out.println(Arrays.toString(favoriteSongs));
	}
	private Object readResolve(){
		return INSTANCE;
	}
}