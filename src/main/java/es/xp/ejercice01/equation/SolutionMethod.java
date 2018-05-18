package es.xp.ejercice01.equation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SolutionMethod {
	
	protected List<Equation> equations;
	protected Set<String> nameSet;
	protected Map<String, Equation> solutions;

	public SolutionMethod() {
		this.solutions = new HashMap<String, Equation>();
	}
	
	public void set(List<Equation> equations) {
		this.equations = equations;
	}

	public abstract void resolve();

	public void set(Set<String> nameSet) {
		this.nameSet = nameSet;
	}

	void setSolutions(Map<String, Equation> solutions) {
		this.solutions = solutions;
	}
	
	Map<String, Equation> getSolutions() {
		return this.solutions;
	}
}
