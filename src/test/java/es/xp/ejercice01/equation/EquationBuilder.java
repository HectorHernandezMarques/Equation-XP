package es.xp.ejercice01.equation;

import java.util.List;

public class EquationBuilder {
	private Equation equation;
	private Side side;

	public EquationBuilder() {
		this.equation = new Equation();
		side = Side.LEFT;
	}

	public EquationBuilder add(Term term) {
		this.equation.add(side, term);
		return this;
	}

	public EquationBuilder add(List<Term> terms) {
		for (Term term : terms) {
			this.equation.add(side, term);
		}
		return this;
	}
	
	public EquationBuilder term(float value, String name) {
		this.equation.add(side, new Variable(value, name));
		return this;
	}
	
	public EquationBuilder term(float value) {
		this.equation.add(side, new Constant(value));
		return this;
	}

	public Equation build() {
		return this.equation.clone();
	}

	public void equals() {
		this.side = side.next();
	}

}
