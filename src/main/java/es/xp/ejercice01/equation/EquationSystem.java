package es.xp.ejercice01.equation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EquationSystem {

	private List<Equation> equationList;
	
	private SolutionMethod solutionMethod;
	
	public EquationSystem(){
		this.equationList = new ArrayList<Equation>();
	}
	
	public void add(Equation equation) {
		this.equationList.add(equation);
	}
	
	public void set(SolutionMethod solutionMethod){
		this.solutionMethod = solutionMethod;
		this.solutionMethod.set(this.equationList);
	}
	
	public void resolve(){
		this.solutionMethod.resolve();
	}

	public Set<String> getNameSet() {
		return this.solutionMethod.getNameSet();
	}
	
	public float getSolution(String name){
		return this.solutionMethod.getSolutions().get(name).getValue(Side.RIGHT);
	}
	
	public boolean equal(EquationSystem equationSystem){
		if (this.equationList.size()!= equationSystem.equationList.size()){
			return false;
		}
		for(int i=0; i<this.equationList.size(); i++){
			if (!this.equationList.get(i).equals(equationSystem.equationList.get(i)))
				return false;
		}
		return true;
	}
	
	public String toString() {
		String result = "\n";
		for(int i=0; i<this.equationList.size(); i++){
			result += this.equationList.get(i) + "\n";
		}
		return result;
	}
	
}

