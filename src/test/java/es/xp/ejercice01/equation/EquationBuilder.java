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
		this.equation = this.equation.add(side, term);
		return this;
	}

	public EquationBuilder add(List<Term> terms) {
		for (Term term : terms) {
			this.equation = this.equation.add(side, term);
		}
		return this;
	}

	public EquationBuilder term(int num, String name) {
		return this.term(num, 1, name);
	}
	
	public EquationBuilder term(int num, int den, String name) {
		return this.add(new Variable(num, den, name));
	}
	
	public EquationBuilder term(int num) {
		return this.term(num, 1);
	}
	
	public EquationBuilder term(int num, int den) {
		return this.add(new Constant(num, den));
	}

	public Equation build() {
		Equation result = this.equation;
		this.equation = new Equation();
		return result;
	}

	public void equals() {
		this.side = side.next();
	}

}
