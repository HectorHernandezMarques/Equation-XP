package es.xp.ejercice01.equation;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class EquationSystemTest {

	@Test
	public void reductionMethodTest() {
		double precission = 0.00001;
		String name1 = "x";
		String name2 = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(2,name1).term(4,name2).equals().term(4)
			.equation().term(5,name1).term(-9,name2).equals().term(-2)
			.build();
		equationSystem.set(new ReductionMethod());
		equationSystem.resolve();
		System.out.println(equationSystem.toString());
		assertEquals("(14/19)", equationSystem.getSolution(name1).toString());
		assertEquals("(12/19)", equationSystem.getSolution(name2).toString());
	}

}
