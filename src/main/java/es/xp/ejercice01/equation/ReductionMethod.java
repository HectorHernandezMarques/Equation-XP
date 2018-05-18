package es.xp.ejercice01.equation;

import java.util.Iterator;

public class ReductionMethod extends SolutionMethod {

	@Override
	public void resolve() {
		assert equationSystem.getNameSet().size() == 2;
		Iterator<String> nameIterator = equationSystem.getNameSet().iterator();
		String firstName = nameIterator.next();	
		String secondName = nameIterator.next();
		float value1 = equationSystem.getLast(2).getValue(firstName);
		float value2 = equationSystem.getLast().getValue(firstName);
		equationSystem.copyBefore(2);
		equationSystem.getLast().multiply(value2);
		equationSystem.copyBefore(2);
		equationSystem.getLast().multiply(-value1);
		equationSystem.copyBefore();
		equationSystem.getLast().add(equationSystem.getLast(3));
		equationSystem.copyBefore();
		equationSystem.getLast().simplify(Side.LEFT, firstName);
		equationSystem.copyBefore();
		equationSystem.getLast().simplify(Side.LEFT, secondName);
		equationSystem.copyBefore();
		equationSystem.getLast().simplify(Side.RIGHT);;
		equationSystem.copyBefore();
		equationSystem.getLast().multiply(1/equationSystem.getLast(2).getValue(secondName));
		equationSystem.seStolution(secondName, equationSystem.getLast());
		equationSystem.copyBefore(9);
		equationSystem.getLast().apply(secondName, equationSystem.getLast(2).getValue(Side.RIGHT));
		equationSystem.copyBefore();
		equationSystem.getLast().add(new Constant(-equationSystem.getLast(2).getValue(Side.LEFT)));
		equationSystem.copyBefore();
		equationSystem.getLast().simplify(Side.LEFT);
		equationSystem.copyBefore();
		equationSystem.getLast().simplify(Side.RIGHT);
		equationSystem.copyBefore();
		equationSystem.getLast().multiply(1/equationSystem.getLast(2).getValue(firstName));
		equationSystem.seStolution(firstName, equationSystem.getLast());
	}
	
}
