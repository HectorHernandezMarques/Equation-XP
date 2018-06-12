package es.xp.ejercice01.equation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Equation {
	
	Map<Side, Expression> expressions;
	
	public Equation() {
		this.expressions = new HashMap<Side, Expression>();
		this.expressions.put(Side.LEFT, new Expression());
		this.expressions.put(Side.RIGHT, new Expression());
	}
	
	private Equation(Map<Side, Expression> expressions) {
		this();
		this.expressions.get(Side.LEFT).add(expressions.get(Side.LEFT));
		this.expressions.get(Side.RIGHT).add(expressions.get(Side.RIGHT));
	}
	
	public Equation add(Side side, Term term) {
		Equation result = this.clone();
		result.expressions.get(side).add(term);
		return result;
	}
	
	public Equation add(Term term) {
		Equation result = this.clone();
		for (Expression expression : result.expressions.values()) {
			expression.add(term);
		}
		return result;
	}
	
	public Equation add(Equation equation) {
		Equation result = this.clone();
		result.expressions.get(Side.LEFT).add(equation.expressions.get(Side.LEFT).clone());
		result.expressions.get(Side.RIGHT).add(equation.expressions.get(Side.RIGHT).clone());
		return result;
	}
	
	public Equation multiply(Fraction fraction) {
		Equation result = new Equation();
		result.expressions.get(Side.LEFT).add(this.expressions.get(Side.LEFT).multiply(fraction));
		result.expressions.get(Side.RIGHT).add(this.expressions.get(Side.RIGHT).multiply(fraction));
		return result;
	}

	public Fraction getValue(Side side, String name) {
		return this.expressions.get(side).getValue(name);
	}
	
	public Fraction getValue(String name) {
		for (Expression expression : this.expressions.values()) {
			if(expression.hasName(name)) {
				return expression.getValue(name);
			}
		}
		return Fraction.ZERO();
	}
	
	public Fraction getValue(Side side) {
		return this.expressions.get(side).getValue();
	}

	
	public void simplify(String name) {
		for (Expression expression : this.expressions.values()) {
			expression.simplify(name);
		}
	}
	
	public void simplify(Side side, String name) {
		this.expressions.get(side).simplify(name);
	}
	
	public void simplify() {
		for (Expression expression : this.expressions.values()) {
			expression.simplify();
		}
	}
	
	public void simplify(Side side) {
		this.expressions.get(side).simplify();
	}
	
	public void simplifyAll() {
		for (Expression expression : this.expressions.values()) {
			expression.simplifyAll();
		}
	}
	
	public void simplifyAll(Side side) {
		this.expressions.get(side).simplifyAll();
	}

	public Set<String> getNameSet() {
		Set<String> result = new HashSet<String>();
		for (Expression expression : this.expressions.values()) {
			result.addAll(expression.getNameSet());
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equation other = (Equation) obj;
		if (expressions == null) {
			if (other.expressions != null)
				return false;
		} else if (!expressions.equals(other.expressions))
			return false;
		return true;
	}

	@Override
	protected Equation clone() {
		return new Equation(this.expressions);
	}

	@Override
	public String toString() {
		return this.expressions.get(Side.LEFT).toString() + "=" + this.expressions.get(Side.RIGHT).toString();
	}

	public void apply(String name, Fraction value) {
		for (Expression expression : this.expressions.values()) {
			expression.apply(name, value);
		}
	}
	
	public void invert() {
		Map<Side, Expression> expressions = new HashMap<Side, Expression>();
		expressions.put(Side.LEFT, this.expressions.get(Side.RIGHT));
		expressions.put(Side.RIGHT, this.expressions.get(Side.LEFT));
		
		this.expressions = expressions;
	}
}
