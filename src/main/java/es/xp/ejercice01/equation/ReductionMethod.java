package es.xp.ejercice01.equation;

import java.util.Iterator;
import java.util.ListIterator;

public class ReductionMethod extends SolutionMethod {

	@Override
	public void resolve() {
		assert this.nameSet.size() == 2;
		Iterator<String> nameIterator = this.nameSet.iterator();
		String firstName = nameIterator.next();
		String secondName = nameIterator.next();
		
		this.reduce(firstName);
		
		this.copyLast();
		this.setLast(this.getLast().multiply(this.getLast(1).getValue(secondName).getInvert()));
		this.setSolution(secondName, this.getLast());
		this.copyFirst();
		this.getLast().apply(secondName, this.getSolutionValue(secondName));
		this.copyLast();
		this.setLast(this.getLast().add(new Constant(this.getLast(1).getValue(Side.LEFT).multiply(-1))));
		this.copyLast();
		this.getLast().simplify(Side.LEFT);
		this.copyLast();
		this.getLast().simplify(Side.RIGHT);
		this.copyLast();
		this.setLast(this.getLast().multiply(this.getLast(1).getValue(firstName).getInvert()));
		this.setSolution(firstName, this.getLast());
	}


	private void reduce(String name) {
		Fraction value1 = this.getLast(1).getValue(name);
		Fraction value2 = this.getLast().getValue(name);
		
		this.copyLast(2);
		this.setLast(1, this.getLast(1).multiply(value2));
		this.setLast(this.getLast().multiply(value1.multiply(-1)));
		this.copyLast();
		this.setLast(this.getLast().add(this.getLast(2)));
		this.copyLast();
		this.getLast().simplifyAll();
	}


	private void setLast(Equation equation) {
		this.setLast(0, equation);
	}
	
	private void setLast(int index, Equation equation) {
		assert 0 <= index && index < this.equations.size();
		ListIterator<Equation> it = this.equations.listIterator(this.equations.size() - index - 1);
		it.next();
		
		it.set(equation);
	}

}
