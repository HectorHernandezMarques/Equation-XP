package es.xp.ejercice01.equation;

import java.util.List;

public class ExpressionBuilder {
	private Expression expression;

	public ExpressionBuilder() {
		this.expression = new Expression();
	}

	public ExpressionBuilder add(Expression Expression) {
		this.expression.add(Expression);
		return this;
	}

	public ExpressionBuilder add(Term term) {
		this.expression.add(term);
		return this;
	}

	public ExpressionBuilder add(List<Term> terms) {
		for (Term term : terms) {
			this.expression.add(term);
		}
		return this;
	}

	public Expression build() {
		Expression result = this.expression;
		this.expression = new Expression();
		return result;
	}

}
