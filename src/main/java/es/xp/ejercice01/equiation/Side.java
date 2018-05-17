package es.xp.ejercice01.equiation;

public enum Side {
	RIGHT,
	LEFT;
	
	public Side next() {
		return this == RIGHT ? LEFT
							 : RIGHT;
	}
}
