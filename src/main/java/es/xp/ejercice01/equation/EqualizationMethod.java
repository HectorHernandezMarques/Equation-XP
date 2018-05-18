package es.xp.ejercice01.equation;

import java.util.Iterator;

public class EqualizationMethod extends SolutionMethod {

	@Override
	public void resolve() {
		assert equationSystem.getNameSet().size() == 2;
		Iterator<String> nameIterator = equationSystem.getNameSet().iterator();
		String firstName = nameIterator.next();	
		String secondName = nameIterator.next();
		equationSystem.copyBefore(2);
		equationSystem.getLast().multiply(1/equationSystem.getLast(3).getValue(firstName));
		equationSystem.copyBefore(2);
		equationSystem.getLast().multiply(1/equationSystem.getLast(3).getValue(firstName));
		equationSystem.copyBefore(2);
		equationSystem.getLast().add(new Variable(-equationSystem.getLast(3).getValue(secondName),secondName));
		equationSystem.copyBefore(2);
		equationSystem.getLast().add(new Variable(-equationSystem.getLast(3).getValue(secondName),secondName));
		equationSystem.copyBefore(2);
		equationSystem.getLast().simplify(Side.LEFT, secondName);
		equationSystem.copyBefore(2);
		equationSystem.getLast().simplify(Side.LEFT, secondName);
		equationSystem.copyBefore();
		equationSystem.getLast().invert();
		equationSystem.getLast().add(equationSystem.getLast(3));
		equationSystem.getLast().add(new Variable(-1,firstName));
		equationSystem.getLast().simplify(Side.LEFT, firstName);
		equationSystem.getLast().simplify(Side.RIGHT, firstName);
		equationSystem.copyBefore();
		equationSystem.getLast().add(new Constant(-equationSystem.getLast().getValue(Side.LEFT)));
		equationSystem.copyBefore();
		equationSystem.getLast().simplify(Side.LEFT);
		equationSystem.copyBefore();
		equationSystem.getLast().add(new Variable(-equationSystem.getLast().getValue(secondName),secondName));
		equationSystem.copyBefore();
		equationSystem.getLast().simplify(Side.LEFT, secondName);
		equationSystem.getLast().simplify(Side.RIGHT, secondName);
		equationSystem.copyBefore();
		equationSystem.getLast().simplify(Side.RIGHT);
		equationSystem.copyBefore();
		equationSystem.getLast().multiply(1/equationSystem.getLast().getValue(secondName));
		equationSystem.copyBefore(9);
		System.out.println(equationSystem.getLast(2).getValue(Side.RIGHT));
		equationSystem.getLast().apply(secondName, equationSystem.getLast(2).getValue(Side.RIGHT));
	}	

}
