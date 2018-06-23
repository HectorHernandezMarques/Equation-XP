package es.xp.ejercice01.equation;

import java.util.Iterator;

public class EqualizationMethod extends SolutionMethod {

	@Override
	public void resolve() {
		assert this.nameSet.size() == 2;
		Iterator<String> nameIterator = this.nameSet.iterator();
		String firstName = nameIterator.next();
		String secondName = nameIterator.next();
		
		this.copyLastEquations(2);
		this.moveLastEquation(0, Side.LEFT, firstName);
		this.moveLastEquation(0, Side.RIGHT, secondName);
		this.moveLastEquation(0, Side.RIGHT);
		this.simplifyValueLastEquation(0, firstName);
		this.moveLastEquation(1, Side.LEFT, firstName);
		this.moveLastEquation(1, Side.RIGHT, secondName);
		this.moveLastEquation(1, Side.RIGHT);
		this.simplifyValueLastEquation(1, firstName);
		
		Expression firstValueExpression = this.getLastEquation(1).getExpression(Side.RIGHT);
		this.copyLastEquations(1);
		this.getLastEquation(0).apply(firstName, firstValueExpression);

		
//		this.getLastEquation(0).simplifyAll();
		this.moveLastEquation(0, Side.LEFT, secondName);
		this.moveLastEquation(0, Side.RIGHT);
		this.copyLastEquations(1);
		this.simplifyValue(secondName);
//		this.copyLastEquations(1);
//		this.moveLastEquation(0, Side.RIGHT);
//		this.copyLastEquations(1);
//		this.simplifyValue(secondName);
//
//		this.setSolution(secondName, this.getLastEquation(0));
//		
//		this.subtituteValue(1, secondName, this.getSolutionValue(secondName));
//		this.copyLastEquations(1);
//		this.simplifyValue(firstName);
//		this.setSolution(firstName, this.getLastEquation(0));
	}	

}
