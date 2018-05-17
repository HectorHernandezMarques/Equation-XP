package es.xp.ejercice01.equation;

import java.util.List;

public class EquationBuilder {
	private Equation equation;

	public EquationBuilder() {
		this.equation = new Equation();
	}

	public EquationBuilder add(Equation equation) {
		this.equation.add(equation);
		return this;
	}

	public EquationBuilder add(Term term) {
		this.equation.add(term);
		return this;
	}

	public EquationBuilder add(Side side, Term term) {
		this.equation.add(side, term);
		return this;
	}

	public EquationBuilder add(List<Term> terms) {
		for (Term term : terms) {
			this.equation.add(term);
		}
		return this;
	}

	public EquationBuilder add(Side side, List<Term> terms) {
		for (Term term : terms) {
			this.equation.add(side, term);
		}
		return this;
	}

	public Equation build() {
		return this.equation.clone();
	}

}
