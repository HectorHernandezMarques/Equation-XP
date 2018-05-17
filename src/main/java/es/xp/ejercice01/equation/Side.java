package es.xp.ejercice01.equation;

public enum Side {
	RIGHT,
	LEFT;
	
	public Side next() {
		return this == RIGHT ? LEFT
							 : RIGHT;
	}
}
