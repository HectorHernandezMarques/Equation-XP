package es.xp.ejercice01.equation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SolutionMethod {

	protected List<Equation> equations;
	protected Set<String> nameSet;
	protected Map<String, Equation> solutions;

	public SolutionMethod() {
		this.solutions = new HashMap<String, Equation>();
		this.nameSet = new HashSet<String>();
	}

	public void set(List<Equation> equations) {
		this.equations = equations;
		for (Equation equation : equations) {
			this.nameSet.addAll(equation.getNameSet());
		}
	}

	public abstract void resolve();

	protected Map<String, Equation> getSolutions() {
		return this.solutions;
	}

	protected void copyBefore(int before) {
		int index = this.equations.size() - before;
		this.add(this.get(index).clone());
	}

	protected void copyBefore() {
		this.copyBefore(1);
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

	protected Equation getLast(int before) {
		int index = this.equations.size() - before;
		return this.equations.get(index);
	}

	protected Equation getLast() {
		return this.getLast(1);
	}

	protected Set<String> getNameSet() {
		return this.nameSet;
	}

	protected void setSolution(String firstName, Equation equation) {
		this.solutions.put(firstName, equation);
	}
}
