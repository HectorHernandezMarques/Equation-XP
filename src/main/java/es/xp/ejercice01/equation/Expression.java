package es.xp.ejercice01.equation;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class Expression {
	private List<Term> terms;

	private boolean empty() {
		return this.terms.size() == 0;
	}

	public Expression() {
		this.terms = new ArrayList<Term>();
	}

	public void add(Term term) {
		this.terms.add(term.clone());
	}

	public void add(Expression expression) {
		for (Term term : expression.terms) {
			this.add(term);
		}
	}

	public Expression multiply(Fraction fraction) {
		Expression result = new Expression();
		for (Term term : this.terms) {
			result.add(term.multiply(fraction));
		}
		return result;
	}

	public void simplify() {
		Fraction newSimplifyedValue = Fraction.ZERO();
		List<Term> newTerms = new ArrayList<Term>();
		for (Term term : this.terms) {
			if (term.hasName(this.getNameSet())) {
				newTerms.add(term);
			} else {
				newSimplifyedValue = newSimplifyedValue.add(term.getValue());
			}
		}
		if (!newSimplifyedValue.equals(Fraction.ZERO())) {
			newTerms.add(new Constant(newSimplifyedValue));
		}
		this.terms = newTerms;
	}

	public void simplify(String name) {
		List<Term> newTerms = new ArrayList<Term>();
		for (Term term : this.terms) {
			if (!term.hasName(name)) {
				newTerms.add(term);
			}
		}
		if (!this.getValue(name).equals(Fraction.ZERO())) {
			newTerms.add(new Variable(this.getValue(name), name));
		}
		this.terms = newTerms;
	}

	public void simplifyAll() {
		for (String name : this.getNameSet()) {
			this.simplify(name);
		}
		this.simplify();
	}

	public Fraction getValue(String name) {
		Fraction result = Fraction.ZERO();
		for (Term term : this.terms) {
			if (term.hasName(name)) {
				result = result.add(term.getValue());
			}
		}
		return result;
	}

	public Fraction getValue() {
		Fraction result = Fraction.ZERO();
		for (Term term : this.terms) {
			if (!term.hasName(this.getNameSet())) {
				result = result.add(term.getValue());
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
			if (term.getValue().getNum() != 0) {
				if (term.getValue().getNum() < 0) {
					result += "";
				} else {
					result += "+";
				}
				result += term.toString();
			}
		}
		return result;
	}

	public void apply(String name, Fraction value) {
		List<Term> newTerms = new ArrayList<Term>();
		for (Term term : this.terms) {
			if (term.hasName(name)) {
				newTerms.add(new Constant(term.getValue().multiply(value)));
			}
			else {
				newTerms.add(term);
			}
		}
		this.terms = newTerms;
	}

}
