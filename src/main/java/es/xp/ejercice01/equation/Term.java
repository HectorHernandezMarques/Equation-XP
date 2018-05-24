package es.xp.ejercice01.equation;

import java.util.Set;

public abstract class Term {

	private float value;
	private Fraction fraction;

	public Term(float value) {
		this.value = value;
		this.fraction = new Fraction((int)value, 1);
	}

	public float getValue() {
		return value;
	}

	public void multiply(float value) {
		int value2 = (int)value;
		this.fraction.multiply(value2);
		this.value *= value;
	}

	public boolean hasName(String string) {
		return false;
	}

	public boolean hasName(Set<String> string) {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Term other = (Term) obj;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}

	public boolean equal(Term term) {
		return term.value == this.value;
	}

	@Override
	abstract public Term clone();

	@Override
	public String toString() {
		return "" + value;
	}

	abstract public void dispatch(TermVisitor visitor);

}
