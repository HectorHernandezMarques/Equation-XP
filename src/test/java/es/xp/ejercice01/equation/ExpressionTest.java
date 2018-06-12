package es.xp.ejercice01.equation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ExpressionTest {

	private Expression createExpression() {
		expressionBuilder.add(new Constant(1, 2)).add(new Constant(5))
						 .add(new Variable(2, 3, "x")).add(new Variable(3, 2, "x"))
						 .add(new Variable(4, 5, "y"));
		return expressionBuilder.build();
	}

	private ExpressionBuilder expressionBuilder;

	public ExpressionTest() {
		this.expressionBuilder = new ExpressionBuilder();
	}
	
	@Test
	void addTermTest() {
		Expression expression = this.createExpression();
		assertEquals(new Fraction(11, 2), expression.getValue());
		assertEquals(new Fraction(13, 6), expression.getValue("x"));
		assertEquals(new Fraction(4, 5), expression.getValue("y"));
	}

	@Test
	void addExpressionTest() {
		Expression expression = this.createExpression();
		this.expressionBuilder = new ExpressionBuilder();
		Expression expAux = this.expressionBuilder.add(new Variable(6, "y"))
				  								  .build();
		expression.add(expAux);

		assertEquals(new Fraction(11, 2), expression.getValue());
		assertEquals(new Fraction(13, 6), expression.getValue("x"));
		assertEquals(new Fraction(34, 5), expression.getValue("y"));
	}

	@ParameterizedTest
	@ValueSource(ints = { 5, 10 })
	void multiplyTest(int num) {
		final int den = 4;
		
		Expression expression = this.createExpression().multiply(new Fraction(num, den));
		assertEquals(new Fraction(11 * num, 2 * den), expression.getValue());
		assertEquals(new Fraction(13 * num, 6 * den), expression.getValue("x"));
		assertEquals(new Fraction(4 * num, 5 * den), expression.getValue("y"));

	}

	@Test
	void simplifyVariableTest() {
		Expression expression = this.expressionBuilder.add(new Variable(2, "x")).add(new Variable(3, "x"))
												  .build();
		Expression result = this.expressionBuilder.add(new Variable(5, "x")).build();

		assertNotEquals(result, expression);
		expression.simplify("x");
		assertEquals(result, expression);
	}
	
	@Test
	void simplifyConstantTest() {
		Expression expression = this.expressionBuilder.add(new Variable(2, "x")).add(new Variable(3, "x"))
													  .add(new Constant(4)).add(new Constant(2))
													  .build();
		Expression result = this.expressionBuilder.add(new Variable(2, "x")).add(new Variable(3, "x"))
												  .add(new Constant(6))
												  .build();

		assertNotEquals(result, expression);
		expression.simplify();
		assertEquals(result, expression);
	}
	
	@Test
	void simplifyAllTest() {
		Expression expression = this.expressionBuilder.add(new Variable(2, "x")).add(new Variable(3, "x"))
													  .add(new Variable(4, "y")).add(new Variable(5, "y"))
													  .add(new Constant(4)).add(new Constant(2))
													  .build();
		
		Expression result = this.expressionBuilder.add(new Variable(5, "x"))
												  .add(new Variable(9, "y"))
												  .add(new Constant(6))
												  .build();

		assertNotEquals(result, expression);
		expression.simplifyAll();
		assertEquals(result, expression);
	}

	@Test
	void cloneTest() {
		Expression expression = this.createExpression();
		Expression clone = expression.clone();
		assertEquals(expression, clone);
		assertFalse(expression == clone);
		
		assertNotEquals(expression, clone.multiply(new Fraction(5)));
	}

	@Test
	void toStringTest() {
		Expression expression = this.createExpression();
		assertEquals("+(1/2)+(5/1)+(2/3)x+(3/2)x+(4/5)y", expression.toString());
	}

}
