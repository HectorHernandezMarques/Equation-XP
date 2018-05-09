package es.xp.ejercice01.equiation;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ExpressionTest {

	@Test
	void addTermTest() {
		Expression expSUT = create3Expression();
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

	private Expression create3Expression() {
		Term term00 = new Constant(1);
		Term term01 = new Constant(5);
		Term term11 = new Variable(2, "x");
		Term term12 = new Variable(3, "x");
		Term term2 = new Variable(4, "y");
		Expression expSUT = new Expression();
		expSUT.add(term00);
		expSUT.add(term01);
		expSUT.add(term11);
		expSUT.add(term12);
		expSUT.add(term2);
		return expSUT;
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
	void cloneTest() {
		Expression expSUT = create3Expression();
		Expression expAux = expSUT.clone();
		assertEquals(expSUT, expAux);
		assertFalse(expSUT == expAux);
	}

	@Test
	void toStringTest() {
		Expression expSUT = create3Expression();
		assertEquals("+1.0+5.0+2.0x+3.0x+4.0y", expSUT.toString());
	}

}
