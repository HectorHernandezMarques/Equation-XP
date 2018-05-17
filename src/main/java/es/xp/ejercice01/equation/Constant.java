package es.xp.ejercice01.equation;

public class Constant extends Term {

	public Constant(float value) {
		super(value);
	}

	@Override
	public boolean equal(Term term) {
		return super.equal(this);
	}

	@Override
	public Constant clone() {
		return new Constant(this.getValue());
	}

	@Override
	public void dispatch(TermVisitor visitor) {
		visitor.visit(this);
	}

}
