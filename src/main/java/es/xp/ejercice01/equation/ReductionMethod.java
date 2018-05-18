package es.xp.ejercice01.equation;

import java.util.Iterator;
import java.util.Set;

public class ReductionMethod extends SolutionMethod {
	
	@Override
	public void resolve() {
		assert super.nameSet.size() == 2;
		Iterator<String> nameIterator = super.nameSet.iterator();
		String firstName = nameIterator.next();
		String secondName = nameIterator.next();
		float value1 = this.getLast(2).getValue(firstName);
		float value2 = this.getLast().getValue(firstName);
		this.copyBefore(2);
		this.getLast().multiply(value2);
		this.copyBefore(2);
		this.getLast().multiply(-value1);
		this.copyBefore();
		this.getLast().add(this.getLast(3));
		this.copyBefore();
		this.getLast().simplify(Side.LEFT, firstName);
		this.copyBefore();
		this.getLast().simplify(Side.LEFT, secondName);
		this.copyBefore();
		this.getLast().simplify(Side.RIGHT);
		this.copyBefore();
		this.getLast().multiply(1 / this.getLast(2).getValue(secondName));
		this.setSolution(secondName, this.getLast());
		this.copyBefore(9);
		this.getLast().apply(secondName, this.getLast(2).getValue(Side.RIGHT));
		this.copyBefore();
		this.getLast().add(new Constant(-this.getLast(2).getValue(Side.LEFT)));
		this.copyBefore();
		this.getLast().simplify(Side.LEFT);
		this.copyBefore();
		this.getLast().simplify(Side.RIGHT);
		this.copyBefore();
		this.getLast().multiply(1 / this.getLast(2).getValue(firstName));
		this.setSolution(firstName, this.getLast());
	}

	void copyBefore(int before) {
		int index = this.equations.size() - before;
		this.add(this.get(index).clone());
	}

	void copyBefore() {
		this.copyBefore(1);
	}

	private Equation get(int index) {
		return this.equations.get(index);
	}

	public void add(Equation equation) {
		this.equations.add(equation);
		for (String name : equation.getNameSet()) {
			super.nameSet.add(name);
		}
	}

	Equation getLast(int before) {
		int index = this.equations.size() - before;
		return this.equations.get(index);
	}

	Equation getLast() {
		return this.getLast(1);
	}

	public Set<String> getNameSet() {
		return super.nameSet;
	}

	void setSolution(String firstName, Equation equation) {
		super.solutions.put(firstName, equation);
	}

}
