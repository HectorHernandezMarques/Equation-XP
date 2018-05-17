package es.xp.ejercice01.equation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import es.xp.ejercice01.equation.Constant;
import es.xp.ejercice01.equation.Expression;
import es.xp.ejercice01.equation.Term;
import es.xp.ejercice01.equation.Variable;

public class ExpressionTest {

	private Expression createExpression() {
		ExpressionBuilder expressionBuilder = new ExpressionBuilder();
		expressionBuilder.add(new Constant(1)).add(new Constant(5))
											  .add(new Variable(2, "x")).add(new Variable(3, "x"))
											  .add(new Variable(4, "y"));
		return expressionBuilder.build();
	}

	ExpressionBuilder expressionBuilder;

	@Test
	void addTermTest() {
		Expression expression = this.createExpression();
		assertEquals(6, expression.getValue(), 0.001);
		assertEquals(5, expression.getValue("x"), 0.001);
		assertEquals(4, expression.getValue("y"), 0.001);

	}

	@Test
	void addExpressionTest() {
		Expression expression = this.createExpression();
		this.expressionBuilder = new ExpressionBuilder();
		Expression expAux = this.expressionBuilder.add(new Variable(6, "y"))
				  								  .build();
		expression.add(expAux);

		assertEquals(6, expression.getValue(), 0.001);
		assertEquals(5, expression.getValue("x"), 0.001);
		assertEquals(10, expression.getValue("y"), 0.001);
	}

	@ParameterizedTest
	@ValueSource(floats = { 5, 10 })
	void multiplyTest(float value) {
		Expression expression = this.createExpression();
		expression.multiply(value);
		assertEquals(6 * value, expression.getValue(), 0.001);
		assertEquals(5 * value, expression.getValue("x"), 0.001);
		assertEquals(4 * value, expression.getValue("y"), 0.001);

	}

	@Test
	void simplifyVariableTest() {
		this.expressionBuilder = new ExpressionBuilder();
		Expression expression = this.expressionBuilder.add(new Variable(2, "x")).add(new Variable(3, "x"))
												  .build();
		this.expressionBuilder = new ExpressionBuilder();
		Expression result = this.expressionBuilder.add(new Variable(5, "x")).build();

		assertNotEquals(result, expression);
		expression.simplify("x");
		assertEquals(result, expression);
	}
	
	@Test
	void simplifyConstantTest() {
		this.expressionBuilder = new ExpressionBuilder();
		Expression expression = this.expressionBuilder.add(new Variable(2, "x")).add(new Variable(3, "x"))
													  .add(new Constant(4)).add(new Constant(2))
													  .build();
		this.expressionBuilder = new ExpressionBuilder();
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
		
		expAux.multiply(5);
		assertNotEquals(expression, expAux);
	}

	@Test
	void toStringTest() {
		Expression expression = this.createExpression();
		assertEquals("+1.0+5.0+2.0x+3.0x+4.0y", expression.toString());
	}

}
