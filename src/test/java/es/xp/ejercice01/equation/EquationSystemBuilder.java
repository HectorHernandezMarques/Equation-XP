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

	public EquationSystemBuilder term(float value, String name) {
		equationBuilder.term(value, name);
		return this;
	}

	public EquationSystemBuilder term(float value) {
		equationBuilder.term(value);
		return this;
	}
	
	public EquationSystemBuilder equals() {
		equationBuilder.equals();
		return this;
	}
	
	public EquationSystem build() {
		this.equation();
		return equationSystem;
	}

}
