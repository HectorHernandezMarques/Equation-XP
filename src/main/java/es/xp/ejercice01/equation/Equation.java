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
		this.expressions = new HashMap<Side, Expression>();
	}
	
	public void add(Side side, Term term) {
		this.expressions.get(side).add(term);
	}
	
	public void add(Term term) {
		for (Expression expression : this.expressions.values()) {
			expression.add(term);
		}
	}
	
	public void add(Equation equation) {
		this.expressions.get(Side.LEFT).add(equation.expressions.get(Side.LEFT));
		this.expressions.get(Side.RIGHT).add(equation.expressions.get(Side.RIGHT));
	}
	
	public void multiply(float value) {
		for (Expression expression : this.expressions.values()) {
			expression.multiply(value);
		}
	}
	
	public float getValue(Side side, String name) {
		return this.expressions.get(side).getValue(name);
	}
	
	public float getValue(Side side) {
		return this.expressions.get(side).getValue();
	}
	
	public void simplify(Side side, String name) {
		this.expressions.get(side).simplify(name);
	}
	
	public void simplify(Side side) {
		this.expressions.get(side).simplify();
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
		Equation result = new Equation();
		result.add(this);
		return result;
	}

	@Override
	public String toString() {
		return this.expressions.get(Side.LEFT).toString() + "=" + this.expressions.get(Side.RIGHT).toString();
	}

	public void invert() {
		this.multiply(-1);
	}
}
