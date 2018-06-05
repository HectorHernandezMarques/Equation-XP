package es.xp.ejercice01.equation;

public class Constant extends Term {

	public Constant(Fraction fraction) {
		this(fraction.getNum(), fraction.getDen());
	}
	
	public Constant(int num) {
		this(num, 1);
	}

	public Constant(int num, int den) {
		super(num, den);
	}

	@Override
	public boolean equal(Term term) {
		return super.equal(this);
	}

	@Override
	public Constant clone() {
		return new Constant(this.getNum(), this.getDen());
	}

	@Override
	public void dispatch(TermVisitor visitor) {
		visitor.visit(this);
	}

}
