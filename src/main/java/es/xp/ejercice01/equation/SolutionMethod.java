package es.xp.ejercice01.equation;

import java.util.List;

public abstract class SolutionMethod {

	protected EquationSystem equationSystem;
	
	protected List<Equation> equations;

	public void set(List<Equation> equations){
		this.equations = equations;
	}
	
	public void set(EquationSystem equationSystem){
		this.equationSystem = equationSystem;
	}

	public abstract void resolve();
}
