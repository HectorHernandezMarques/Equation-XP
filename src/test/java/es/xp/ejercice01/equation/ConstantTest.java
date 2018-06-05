package es.xp.ejercice01.equation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import es.xp.ejercice01.equation.Constant;

class ConstantTest {

	@ParameterizedTest
	@ValueSource(ints = { 5, 10 })
	void oneParameterConstructorTest(int num) {
		Constant constant = new Constant(num);

		assertEquals(new Fraction(num), constant.getValue());
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void twoParametersConstructorTest(int den) {
		Constant constant = new Constant(5, den);

		assertEquals(new Fraction(5, den), constant.getValue());
	}

	@ParameterizedTest
	@ValueSource(ints = { 5, 10 })
	void equalsTest(int value) {
		Constant constant = new Constant(value);
		
		assertEquals(new Constant(value), constant);
	}

	@ParameterizedTest
	@ValueSource(ints = { 5, 10 })
	void cloneTest(int value) {
		Constant constant1 = new Constant(value);
		Constant constant2 = constant1.clone();
		
		assertFalse(constant1 == constant2);
		assertEquals(constant1, constant2);
		constant1.multiply(new Fraction(5));

		assertNotEquals(constant1, constant2);
	}
	
}
