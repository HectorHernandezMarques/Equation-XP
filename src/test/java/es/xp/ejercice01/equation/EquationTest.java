package es.xp.ejercice01.equation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import es.xp.ejercice01.equation.Constant;
import es.xp.ejercice01.equation.Equation;
import es.xp.ejercice01.equation.Expression;
import es.xp.ejercice01.equation.Side;
import es.xp.ejercice01.equation.Variable;

class EquationTest {

	ExpressionBuilder expressionBuilder;
	EquationBuilder equationBuilder;
	
	@Test
	void addTermTest() {
		Equation equation = new Equation();
		equation.add(new Variable(5, "x"));
		equation.add(new Variable(6, "y"));
		equation.add(new Constant(5));
		assertEquals("+(5/1)x+(6/1)y+(5/1)=+(5/1)x+(6/1)y+(5/1)",equation.toString());
	}
	
	@Test
	void addSideTermTest() {
		Equation equation = new Equation();
		equation.add(Side.LEFT, new Variable(6, "y"));
		equation.add(Side.RIGHT, new Variable(5, "x"));
		assertEquals("+(6/1)y=+(5/1)x",equation.toString());
	}

	@Test
	void addEquationTest() {
		Equation equation = new Equation();
		equation.add(Side.LEFT, new Variable(6, "y"));
		equation.add(Side.RIGHT, new Variable(5, "x"));
		Equation equation2 = new Equation();
		equation2.add(new Variable(5, "x"));
		equation2.add(new Variable(6, "y"));
		equation2.add(new Constant(5));
		
		equation.add(equation2);
		assertEquals("+(6/1)y+(5/1)x+(6/1)y+(5/1)=+(5/1)x+(5/1)x+(6/1)y+(5/1)",equation.toString());
	}

	@ParameterizedTest
	@ValueSource(ints = { 5, 10 })
	void multiplyTest(int value) {
		Equation equation = new Equation();
		equation.add(new Variable(5, "x"));
		equation.add(new Variable(6, "y"));
		equation.add(new Constant(5));

		Equation equation2 = new Equation();
		equation2.add(new Variable(5*value, "x"));
		equation2.add(new Variable(6*value, "y"));
		equation2.add(new Constant(5*value));
		
		equation.multiply(new Fraction(value));
		
		assertEquals(equation.toString(),equation2.toString());
	}

	@Test
	void getVariableValue() {
		Equation equation = new Equation();
		equation.add(Side.LEFT, new Variable(6, "x"));
		equation.add(Side.LEFT, new Variable(7, "y"));
		equation.add(Side.LEFT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Variable(5, "x"));
		equation.add(Side.RIGHT, new Variable(1, "x"));
		equation.add(Side.RIGHT, new Constant(2));
		equation.add(Side.RIGHT, new Constant(5));
		
		assertEquals(new Fraction(9), equation.getValue(Side.LEFT, "y"));
		assertEquals(new Fraction(6), equation.getValue(Side.RIGHT, "x"));
	}
	
	@Test
	void getConstantValue() {
		Equation equation = new Equation();
		equation.add(Side.LEFT, new Variable(6, "x"));
		equation.add(Side.LEFT, new Variable(7, "y"));
		equation.add(Side.LEFT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Variable(5, "x"));
		equation.add(Side.RIGHT, new Variable(1, "x"));
		equation.add(Side.RIGHT, new Constant(2));
		equation.add(Side.RIGHT, new Constant(5));
		
		assertEquals(new Fraction(7), equation.getValue(Side.RIGHT));
	}
	
	@Test
	void simplifyVariableTest() {
		Equation equation = new Equation();
		equation.add(Side.LEFT, new Variable(6, "x"));
		equation.add(Side.LEFT, new Variable(7, "y"));
		equation.add(Side.LEFT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Variable(5, "x"));
		equation.add(Side.RIGHT, new Variable(1, "x"));
		equation.add(Side.RIGHT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Constant(2));
		equation.add(Side.RIGHT, new Constant(5));
		
		equation.simplify(Side.LEFT, "x");
		equation.simplify(Side.RIGHT, "y");
		
		assertEquals("+(7/1)y+(2/1)y+(6/1)x=+(5/1)x+(1/1)x+(2/1)+(5/1)+(2/1)y", equation.toString());
		
		equation.simplify(Side.RIGHT, "x");
		equation.simplify(Side.LEFT, "y");
		
		assertEquals("+(6/1)x+(9/1)y=+(2/1)+(5/1)+(2/1)y+(6/1)x", equation.toString());
	}
	
	@Test
	void simplifyConstantTest() {
		Equation equation = new Equation();
		equation.add(Side.LEFT, new Variable(6, "x"));
		equation.add(Side.LEFT, new Variable(7, "y"));
		equation.add(Side.LEFT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Variable(5, "x"));
		equation.add(Side.RIGHT, new Variable(1, "x"));
		equation.add(Side.RIGHT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Constant(2));
		equation.add(Side.RIGHT, new Constant(5));
		
		equation.simplify(Side.LEFT);
		assertEquals("+(6/1)x+(7/1)y+(2/1)y=+(5/1)x+(1/1)x+(2/1)y+(2/1)+(5/1)", equation.toString());
	}

	private Expression createExpression() {
		this.expressionBuilder = new ExpressionBuilder();
		Expression expSUT = this.expressionBuilder.add(new Variable(2, "x")).add(new Variable(3, "x"))
												  .add(new Variable(4, "y"))
												  .add(new Constant(4)).add(new Constant(2))
												  .build();
		return expSUT;
	}

	@Test
	void cloneTest() {
		Equation equation = new Equation();
		equation.add(Side.LEFT, new Variable(6, "x"));
		equation.add(Side.LEFT, new Variable(7, "y"));
		equation.add(Side.LEFT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Variable(5, "x"));
		equation.add(Side.RIGHT, new Variable(1, "x"));
		equation.add(Side.RIGHT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Constant(2));
		equation.add(Side.RIGHT, new Constant(5));
		
		Equation equationClon = equation.clone();
		assertEquals(equation, equationClon);
		assertFalse(equation == equationClon);
		equation.multiply(new Fraction(5));
		assertNotEquals(equation, equationClon);
	}

	@Test
	void toStringTest() {
		Equation equation = new Equation();
		equation.add(Side.LEFT, new Variable(6, "x"));
		equation.add(Side.LEFT, new Variable(7, "y"));
		equation.add(Side.LEFT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Variable(5, "x"));
		equation.add(Side.RIGHT, new Variable(1, "x"));
		equation.add(Side.RIGHT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Constant(2));
		equation.add(Side.RIGHT, new Constant(5));
		
		assertEquals("+(6/1)x+(7/1)y+(2/1)y=+(5/1)x+(1/1)x+(2/1)y+(2/1)+(5/1)", equation.toString());
	}
	
	@Test
	void invertionTest() {
		Equation equation = new Equation();
		equation.add(Side.LEFT, new Variable(6, "x"));
		equation.add(Side.LEFT, new Variable(7, "y"));
		equation.add(Side.LEFT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Variable(5, "x"));
		equation.add(Side.RIGHT, new Variable(1, "x"));
		equation.add(Side.RIGHT, new Variable(2, "y"));
		equation.add(Side.RIGHT, new Constant(2));
		equation.add(Side.RIGHT, new Constant(5));
		
		equation.invert();
		
		assertEquals("+(5/1)x+(1/1)x+(2/1)y+(2/1)+(5/1)=+(6/1)x+(7/1)y+(2/1)y", equation.toString());
	}

}
