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

	public EquationBuilder term(int num, String name) {
		this.term(num, 1, name);
		return this;
	}
	
	public EquationBuilder term(int num, int den, String name) {
		this.equation.add(side, new Variable(num, den, name));
		return this;
	}
	
	public EquationBuilder term(int num) {
		this.term(num, 1);
		return this;
	}
	
	public EquationBuilder term(int num, int den) {
		this.equation.add(side, new Constant(num, den));
		return this;
	}

	public Equation build() {
		return this.equation.clone();
	}

	public void equals() {
		this.side = side.next();
	}

}
