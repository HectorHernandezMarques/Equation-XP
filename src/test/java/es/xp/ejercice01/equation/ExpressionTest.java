package es.xp.ejercice01.equation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import es.xp.ejercice01.equation.Constant;
import es.xp.ejercice01.equation.Expression;
import es.xp.ejercice01.equation.Variable;

public class ExpressionTest {

	private Expression createExpression() {
		expressionBuilder.add(new Constant(1)).add(new Constant(5))
											  .add(new Variable(2, "x")).add(new Variable(3, "x"))
											  .add(new Variable(4, "y"));
		return expressionBuilder.build();
	}

	private ExpressionBuilder expressionBuilder;

	public ExpressionTest() {
		this.expressionBuilder = new ExpressionBuilder();
	}
	
	@Test
	void addTermTest() {
		Expression expression = this.createExpression();
		assertEquals(new Fraction(6), expression.getValue());
		assertEquals(new Fraction(5), expression.getValue("x"));
		assertEquals(new Fraction(4), expression.getValue("y"));

	}

	@Test
	void addExpressionTest() {
		Expression expression = this.createExpression();
		this.expressionBuilder = new ExpressionBuilder();
		Expression expAux = this.expressionBuilder.add(new Variable(6, "y"))
				  								  .build();
		expression.add(expAux);

		assertEquals(new Fraction(6), expression.getValue());
		assertEquals(new Fraction(5), expression.getValue("x"));
		assertEquals(new Fraction(10), expression.getValue("y"));
	}

	@ParameterizedTest
	@ValueSource(floats = { 5, 10 })
	void multiplyTest(float value) {
		Expression expression = this.createExpression();
		expression.multiply(new Fraction(value));
		assertEquals(new Fraction(6 * value), expression.getValue());
		assertEquals(new Fraction(5 * value), expression.getValue("x"));
		assertEquals(new Fraction(4 * value), expression.getValue("y"));

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
	void cloneTest() {
		Expression expression = this.createExpression();
		Expression expAux = expression.clone();
		assertEquals(expression, expAux);
		assertFalse(expression == expAux);
		
		expAux.multiply(new Fraction(5));
		assertNotEquals(expression, expAux);
	}

	@Test
	void toStringTest() {
		Expression expression = this.createExpression();
		assertEquals("+(1/1)+(5/1)+(2/1)x+(3/1)x+(4/1)y", expression.toString());
	}

}
