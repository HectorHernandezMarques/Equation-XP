package es.xp.ejercice01.equation;

public class EquationSystemBuilder {
	
	private EquationSystem equationSystem;
	
	private EquationBuilder equationBuilder;
	
	public EquationSystemBuilder(){
		this.equationSystem = new EquationSystem();
	}
	
	public EquationSystemBuilder equation() {
		if (this.equationBuilder != null) {
			this.equationSystem.add(this.equationBuilder.build());
		}
		this.equationBuilder = new EquationBuilder();
		return this;
	}
	
	public EquationSystemBuilder term(int num, String name) {
		return this.term(num, 1, name);
	}

	public EquationSystemBuilder term(int num, int den, String name) {
		this.equationBuilder.term(num, den, name);
		return this;
	}

	public EquationSystemBuilder term(int num) {
		return this.term(num, 1);
	}

	public EquationSystemBuilder term(int num, int den) {
		this.equationBuilder.term(num, den);
		return this;
	}
	
	public EquationSystemBuilder equals() {
		this.equationBuilder.equals();
		return this;
	}
	
	public EquationSystem build() {
		this.equation();
		return equationSystem;
	}

}
