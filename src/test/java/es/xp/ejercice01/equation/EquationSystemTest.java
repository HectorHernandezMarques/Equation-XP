package es.xp.ejercice01.equation;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class EquationSystemTest {

	@Test
	public void reductionMethodTest() {
		String x = "x";
		String y = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(2,x).term(4,y).equals().term(4)
			.equation().term(5,x).term(-9,y).equals().term(-2)
			.build();
		equationSystem.set(new ReductionMethod());
		equationSystem.resolve();
		assertEquals("(14/19)", equationSystem.getSolution(x).toString());
		assertEquals("(12/19)", equationSystem.getSolution(y).toString());
	}
	
	@Test
	public void reductionMethodTest2() {
		String x = "x";
		String y = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
				.equation().term(2,x).equals().term(4).term(-4,y)
				.equation().term(-9,y).equals().term(-2).term(-5,x)
				.build();
		equationSystem.set(new ReductionMethod());
		equationSystem.resolve();
		assertEquals("(14/19)", equationSystem.getSolution(x).toString());
		assertEquals("(12/19)", equationSystem.getSolution(y).toString());
	}
	
	@Test
	public void subtitutionMethodTest() {
		String x = "x";
		String y = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
				.equation().term(2,x).equals().term(4).term(-4,y)
				.equation().term(-9,y).equals().term(-2).term(-5,x)
				.build();
		equationSystem.set(new SubtitutionMethod());
		equationSystem.resolve();
		System.out.println(equationSystem.toString());
		assertEquals("(14/19)", equationSystem.getSolution(x).toString());
		assertEquals("(12/19)", equationSystem.getSolution(y).toString());
	}
	
	@Test
	public void equalizationMethodTest() {
		String x = "x";
		String y = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
				.equation().term(2,x).equals().term(4).term(-4,y)
				.equation().term(-9,y).equals().term(-2).term(-5,x)
				.build();
		equationSystem.set(new EqualizationMethod());
		equationSystem.resolve();
		System.out.println(equationSystem.toString());
		assertEquals("(14/19)", equationSystem.getSolution(x).toString());
		assertEquals("(12/19)", equationSystem.getSolution(y).toString());
	}

}
