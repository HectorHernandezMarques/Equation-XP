package es.xp.ejercice01.equation;

import java.util.Iterator;

public class ReductionMethod extends SolutionMethod {

	@Override
	public void resolve() {
		assert this.nameSet.size() == 2;
		Iterator<String> nameIterator = this.nameSet.iterator();
		String firstName = nameIterator.next();
		String secondName = nameIterator.next();
		
		this.reduce(firstName);
		
		this.copyLastEquations(0);
		
		this.simplifyValue(secondName);
		
		this.setSolution(secondName, this.getLastEquation(0));
		
		this.subtituteValue(1, secondName, this.getSolutionValue(secondName));
		
		this.copyLastEquations(0);

		this.simplifyValue(firstName);
		
		this.setSolution(firstName, this.getLastEquation(0));
	}
	
	private void reduce(String name) {
		Fraction value1 = this.getLastEquation(1).getValue(name);
		Fraction value2 = this.getLastEquation(0).getValue(name);
		
		this.copyLastEquations(2);
		this.setLastEquation(1, this.getLastEquation(1).multiply(value2));
		this.setLastEquation(0, this.getLastEquation(0).multiply(value1.multiply(-1)));
		this.copyLastEquations(1);
		this.setLastEquation(0, this.getLastEquation(0).add(this.getLastEquation(2)));
		this.copyLastEquations(1);
		this.getLastEquation(0).simplifyAll();
	}

}
