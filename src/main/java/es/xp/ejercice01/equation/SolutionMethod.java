package es.xp.ejercice01.equation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public abstract class SolutionMethod {

	protected List<Equation> equations;
	protected List<Equation> originalEquations;
	protected Set<String> nameSet;
	protected Map<String, Equation> solutions;

	public SolutionMethod() {
		this.originalEquations = new ArrayList<Equation>();
		this.solutions = new HashMap<String, Equation>();
		this.nameSet = new HashSet<String>();
	}
	
	private void prepareEquations() {
		assert this.nameSet.size() > 0;
		for (String name : this.nameSet) {
			this.moveLastEquation(1, Side.LEFT, name);
			this.moveLastEquation(0, Side.LEFT, name);
		}
		this.moveLastEquation(1, Side.RIGHT);
		this.moveLastEquation(0, Side.RIGHT);
	}

	public void set(List<Equation> equations) {
		this.equations = equations;
		for (Equation equation : equations) {
			this.nameSet.addAll(equation.getNameSet());
		}
		this.prepareEquations();
		this.originalEquations.add(this.getLastEquation(1).clone());
		this.originalEquations.add(this.getLastEquation(0).clone());
	}

	public abstract void resolve();

	protected void addEquation(Equation equation) {
		this.equations.add(equation);
		for (String name : equation.getNameSet()) {
			this.nameSet.add(name);
		}
	}

	protected void setLastEquation(int index, Equation equation) {
		assert 0 <= index && index < this.equations.size();
		ListIterator<Equation> it = this.equations.listIterator(this.equations.size() - index - 1);
		it.next();
		
		it.set(equation);
	}

	protected void setSolution(String firstName, Equation equation) {
		this.solutions.put(firstName, equation);
	}

	protected void copyFirstEquation() {
		this.addEquation(this.getFirstEquation(0).clone());
	}

	protected void copyOriginalEquation(int index) {
		this.addEquation(this.getOriginalEquation(index).clone());
	}

	protected void copyLastEquations(int number) {
		List<Equation> equations = new ArrayList<Equation>();
		for (int i = number; i > 0; --i) {
			equations.add(this.getFirstEquation(this.equations.size() - i));
		}
		for (Equation equation : equations) {
			this.addEquation(equation.clone());
		}
	}

	protected Equation getFirstEquation(int index) {
		return this.equations.get(index);
	}

	protected Equation getOriginalEquation(int index) {
		return this.originalEquations.get(index);
	}

	protected Equation getLastEquation(int index) {
		return this.equations.get(this.equations.size() - index - 1);
	}

	protected Map<String, Equation> getSolutions() {
		return this.solutions;
	}

	protected Fraction getSolutionValue(String name) {
		return this.solutions.get(name).getValue(Side.RIGHT);
	}

	protected Set<String> getNameSet() {
		return this.nameSet;
	}

	protected void moveLastEquation(int index, Side side, String name) {
		this.getLastEquation(index).simplifyAll();
		this.setLastEquation(index, this.getLastEquation(index).add(new Variable(this.getLastEquation(index).getValue(side.next(), name).multiply(-1), name)));
		this.getLastEquation(index).simplifyAll();
	}

	protected void moveLastEquation(int index, Side side) {
		this.getLastEquation(index).simplifyAll();
		this.setLastEquation(index, this.getLastEquation(index).add(new Constant(this.getLastEquation(index).getValue(side.next()).multiply(-1))));
		this.getLastEquation(index).simplifyAll();
	}

	protected void subtituteValue(int originalIndex, String name, Fraction value) {
		this.copyOriginalEquation(originalIndex);
		
		this.copyLastEquations(1);
		this.getLastEquation(0).apply(name, new Constant(value));
		this.copyLastEquations(1);
		this.moveLastEquation(0, Side.RIGHT);
	}

	protected void simplifyValue(String name) {
		this.simplifyValueLastEquation(0, name);
	}

	protected void simplifyValueLastEquation(int index, String name) {
		this.setLastEquation(index, this.getLastEquation(index).multiply(this.getLastEquation(index).getValue(name).getInvert()));
	}
	
}
