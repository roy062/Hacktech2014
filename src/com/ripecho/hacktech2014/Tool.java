package com.ripecho.hacktech2014;

public enum Tool {
	NULL(0), PENCIL(1), ERASER(2), EYE_DROPPER(3), BUCKET(4);
	
	private final int value;
	private Tool(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
