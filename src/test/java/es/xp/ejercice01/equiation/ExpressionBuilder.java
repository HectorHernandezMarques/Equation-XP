package es.xp.ejercice01.equiation;

import java.util.List;

public class ExpressionBuilder {
	private Expression exp;

	public ExpressionBuilder() {
		this.exp = new Expression();
	}

	public ExpressionBuilder add(Expression expression) {
		exp.add(expression);
		return this;
	}

	public ExpressionBuilder add(Term term) {
		exp.add(term);
		return this;
	}

	public ExpressionBuilder add(List<Term> terms) {
		for (Term term : terms) {
			exp.add(term);
		}
		return this;
	}

	public Expression build() {
		Expression result = exp.clone();
		exp = new Expression();
		return result;
	}

}
