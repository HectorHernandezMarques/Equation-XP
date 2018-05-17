package es.xp.ejercice01.equiation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EquationTest {

	ExpressionBuilder expressionBuilder;
	
	@Test
	void addTermTest() {
		Ekuation
		assertEquals(6, expSUT.getValue(), 0.001);
		assertEquals(5, expSUT.getValue("x"), 0.001);
		assertEquals(4, expSUT.getValue("y"), 0.001);

	}

	@Test
	void addExpressionTest() {
		Expression expSUT = create3Expression();
		Term term = new Variable(6, "y");
		Expression expAux = new Expression();
		expAux.add(term);
		expSUT.add(expAux);

		assertEquals(6, expSUT.getValue(), 0.001);
		assertEquals(5, expSUT.getValue("x"), 0.001);
		assertEquals(10, expSUT.getValue("y"), 0.001);
	}

	@ParameterizedTest
	@ValueSource(floats = { 5, 10 })
	void multiplyTest(float value) {
		Expression expSUT = create3Expression();
		expSUT.multiply(value);
		assertEquals(6 * value, expSUT.getValue(), 0.001);
		assertEquals(5 * value, expSUT.getValue("x"), 0.001);
		assertEquals(4 * value, expSUT.getValue("y"), 0.001);

	}

	@Test
	void simplifyVariableTest() {
		this.expressionBuilder = new ExpressionBuilder();
		Expression expSUT = this.expressionBuilder.add(new Variable(2, "x")).add(new Variable(3, "x")).build();
		Expression result = this.expressionBuilder.add(new Variable(5, "x")).build();

		assertNotEquals(result, expSUT);
		expSUT.simplify("x");
		assertEquals(result, expSUT);
	}
	
	@Test
	void simplifyConstantTest() {
		this.expressionBuilder = new ExpressionBuilder();
		Expression expSUT = this.expressionBuilder.add(new Variable(2, "x")).add(new Variable(3, "x")).add(new Constant(4)).add(new Constant(2)).build();
		Expression result = this.expressionBuilder.add(new Variable(2, "x")).add(new Variable(3, "x")).add(new Constant(6)).build();

		assertNotEquals(result, expSUT);
		expSUT.simplify();
		assertEquals(result, expSUT);
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
		Expression expSUT = create3Expression();
		Expression expAux = expSUT.clone();
		assertEquals(expSUT, expAux);
		assertFalse(expSUT == expAux);
		expAux.multiply(5);
		assertNotEquals(expSUT, expAux);
	}

	@Test
	void toStringTest() {
		Expression expSUT = create3Expression();
		assertEquals("+1.0+5.0+2.0x+3.0x+4.0y", expSUT.toString());
	}

}
