package es.xp.ejercice01.equation;

import java.util.Set;

public class Variable extends Term {
	private String name;

	public Variable(Fraction fraction, String name) {
		this(fraction.getNum(), fraction.getDen(), name);
	}

	public Variable(int num, String name) {
		this(num, 1, name);
	}
	
	public Variable(int num, int den, String name) {
		super(num, den);
		this.name = new String(name);
	}

	public String getName() {
		return this.name;
	}

	@Override
	public boolean hasName(String name) {
		return this.name.equals(name);
	}

	@Override
	public boolean hasName(Set<String> names) {
		return names.contains(this.name);
	}

	@Override
	public Term multiply(Fraction fraction) {
		Term result = new Variable(this.getValue().multiply(fraction), this.name);
		result.simplifySignum();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public boolean equal(Term term) {
		return super.equal(term) && term.hasName(this.name);
	}

	@Override
	public Variable clone() {
		return new Variable(this.getNum(), getDen(), new String(this.name));
	}

	@Override
	public String toString() {
		return "" + super.toString() + this.name;
	}

	@Override
	public void dispatch(TermVisitor visitor) {
		visitor.visit(this);
	}

}
