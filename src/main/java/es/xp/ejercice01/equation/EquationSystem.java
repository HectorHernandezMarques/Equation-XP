package es.xp.ejercice01.equation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EquationSystem {

	//TODO private
	List<Equation> equationList;
	
	private Set<String> nameSet;
	
	private Map<String, Equation> solutions;
	
	private SolutionMethod solutionMethod;
	
	public EquationSystem(){
		this.equationList = new ArrayList<Equation>();
		this.nameSet = new HashSet<String>();
		this.solutions = new HashMap<String, Equation>();
	}
	
	public void add(Equation equation) {
		this.equationList.add(equation);
		for(String name : equation.getNameSet()){
			this.nameSet.add(name);
		}
	}
	
	public void set(SolutionMethod solutionMethod){
		this.solutionMethod = solutionMethod;
		this.solutionMethod.set(this.equationList);
		this.solutionMethod.set(this.nameSet);
		this.solutionMethod.setSolutions(this.solutions);
	}
	
	public void resolve(){
		this.solutionMethod.resolve();
	}

	public Set<String> getNameSet() {
		return nameSet;
	}
	
	void setSolution(String firstName, Equation equation) {
		this.solutions.put(firstName, equation);
	}
	
	public float getSolution(String name){
		return this.solutions.get(name).getValue(Side.RIGHT);
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

