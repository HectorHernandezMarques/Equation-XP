package es.xp.ejercice01.equiation;

public class Constant extends Term {

	public Constant(float value) {
		super(value);
	}

	@Override
	protected Constant clone() {
		return new Constant(this.getValue());
	}

	@Override
	void dispatch(TermVisitor visitor) {
		visitor.visit(this);
	}
	
}
