package es.xp.ejercice01.equation;

import java.util.Iterator;

public class SubtitutionMethod extends SolutionMethod {

	@Override
	public void resolve() {
		assert this.nameSet.size() == 2;
		Iterator<String> nameIterator = this.nameSet.iterator();
		String firstName = nameIterator.next();
		String secondName = nameIterator.next();
		
		this.copyLastEquations(1);
		this.moveLastEquation(0, Side.RIGHT, secondName);
		this.moveLastEquation(0, Side.RIGHT);
		this.copyLastEquations(1);
		this.simplifyValue(firstName);
		
		Expression firstValueExpression = this.getLastEquation(0).getExpression(Side.RIGHT);
		
		this.copyOriginalEquation(0);
		this.copyLastEquations(1);
		this.getLastEquation(0).apply(firstName, firstValueExpression);

		this.copyLastEquations(1);
		this.getLastEquation(0).simplifyAll();
		this.copyLastEquations(1);
		this.moveLastEquation(0, Side.RIGHT);
		this.copyLastEquations(1);
		this.simplifyValue(secondName);

		this.setSolution(secondName, this.getLastEquation(0));
		
		this.subtituteValue(1, secondName, this.getSolutionValue(secondName));
		this.copyLastEquations(1);
		this.simplifyValue(firstName);
		this.setSolution(firstName, this.getLastEquation(0));
	}

}
