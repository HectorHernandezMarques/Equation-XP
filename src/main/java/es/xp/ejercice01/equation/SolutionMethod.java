package es.xp.ejercice01.equation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SolutionMethod {

	protected List<Equation> equations;
	protected List<Equation> initialEquations;
	protected Set<String> nameSet;
	protected Map<String, Equation> solutions;

	public SolutionMethod() {
		this.initialEquations = new ArrayList<Equation>();
		this.solutions = new HashMap<String, Equation>();
		this.nameSet = new HashSet<String>();
	}

	public void set(List<Equation> equations) {
		this.equations = equations;
		for (Equation equation : equations) {
			this.nameSet.addAll(equation.getNameSet());
			this.initialEquations.add(equation.clone());
		}
	}

	public abstract void resolve();

	protected Map<String, Equation> getSolutions() {
		return this.solutions;
	}
	
	protected void copyFirst() {
		this.add(this.get(0).clone());
	}

	protected void copyLast(int before) {
		List<Equation> equations = new ArrayList<Equation>();
		for (int i = before; i > 0; --i) {
			equations.add(this.get(this.equations.size() - i));
		}
		for (Equation equation : equations) {
			this.add(equation.clone());
		}
	}

	protected void copyLast() {
		this.copyLast(1);
	}

	protected Equation get(int index) {
		return this.equations.get(index);
	}

	protected void add(Equation equation) {
		this.equations.add(equation);
		for (String name : equation.getNameSet()) {
			this.nameSet.add(name);
		}
	}

	protected Equation getLast(int index) {
		return this.equations.get(this.equations.size() - index - 1);
	}

	protected Equation getLast() {
		return this.getLast(0);
	}

	protected Set<String> getNameSet() {
		return this.nameSet;
	}

	protected void setSolution(String firstName, Equation equation) {
		this.solutions.put(firstName, equation);
	}

	protected Fraction getSolutionValue(String name) {
		return this.solutions.get(name).getValue(Side.RIGHT);
	}
}
