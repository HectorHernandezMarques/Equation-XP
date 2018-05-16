package es.xp.ejercice01.equiation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Expression {
	private List<Term> terms;

	public Expression() {
		this.terms = new ArrayList<Term>();
	}

	public void add(Term term) {
		this.terms.add(term);
	}

	public void add(Expression expression) {
		this.terms.addAll(expression.terms);
	}

	public void multiply(float value) {
		for (Term term : this.terms) {
			term.multiply(value);
		}
	}

	public void simplify() {
		float value = 0;
		NamesExpresionAnalyzer analyzer = new NamesExpresionAnalyzer(this.terms);
		List<Term> listAux = new ArrayList<Term>();
		for (Term term : listAux) {
			if (!term.hasName(analyzer.getNameSet())) {
				value += term.getValue();
			}
		}
		listAux.add(new Constant(value));
		this.terms = listAux;
	}

	public void simplify(String name) {
		List<Term> listAux = new ArrayList<Term>();
		for (Term term : this.terms) {
			if (!term.hasName(name))
				listAux.add(term);
		}
		listAux.add(new Variable(this.getValue(name), name));
		this.terms = listAux;
	}

	public float getValue(String name) {
		float result = 0;
		for (Term term : this.terms) {
			if (term.hasName(name)) {
				result += term.getValue();
			}
		}
		return result;
	}

	public float getValue() {
		float result = 0;
		NamesExpresionAnalyzer analyzer = new NamesExpresionAnalyzer(this.terms);
		for (Term term : this.terms) {
			if (!term.hasName(analyzer.getNameSet())) {
				result += term.getValue();
			}
		}
		return result;
	}

	public Set<String> getNameSet() {
		return new NamesExpresionAnalyzer(this.terms).getNameSet();
	}

	public boolean hasName(String name) {
		return this.getNameSet().contains(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expression other = (Expression) obj;
		if (terms == null) {
			if (other.terms != null)
				return false;
		} else if (!terms.equals(other.terms))
			return false;
		return true;
	}

	@Override
	public Expression clone() {
		Expression result = new Expression();
		result.add(this);
		return result;
	}

	@Override
	public String toString() {
		String result = "";
		for (Term term : this.terms) {
			if (term.getValue() < 0) {
				result += "-";
			} else {
				result += "+";
			}
			result += term.getValue();
			if (term.hasName(this.getNameSet())) {
				result += ((Variable) term).getName();
			}
		}
		return result;
	}

}
