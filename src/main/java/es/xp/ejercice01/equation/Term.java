package es.xp.ejercice01.equation;

import java.util.Set;

public abstract class Term {

	private Fraction fraction;

	public Term(int num, int den) {
		this.fraction = new Fraction(num, den);
	}

	public Fraction getValue() {
		return fraction.clone();
	}

	public int getNum() {
		return this.fraction.getNum();
	}

	public int getDen() {
		return this.fraction.getDen();
	}

	public void multiply(Fraction fraction) {
		this.fraction = this.fraction.multiply(fraction);
	}

	public boolean hasName(String string) {
		return false;
	}

	public boolean hasName(Set<String> string) {
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fraction == null) ? 0 : fraction.hashCode());
		return result;
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
		if (fraction == null) {
			if (other.fraction != null)
				return false;
		} else if (!fraction.equals(other.fraction))
			return false;
		return true;
	}

	public boolean equal(Term term) {
		return this.fraction.equal(term.fraction);
	}

	@Override
	abstract public Term clone();

	@Override
	public String toString() {
		return "" + this.fraction.toString();
	}

	abstract public void dispatch(TermVisitor visitor);

}
