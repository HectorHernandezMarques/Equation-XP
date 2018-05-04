package es.xp.ejercice01.equiation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ConstantTest {

	@ParameterizedTest
	@ValueSource(floats = { 5, 10 })
	void constructorTest(float value) {
		Constant constant = new Constant(value);
		
		assertEquals(value, constant.getValue());
	}

	@ParameterizedTest
	@ValueSource(floats = { 5, 10 })
	void equalsTest(float value) {
		Constant constant1 = new Constant(value);
		Constant constant2 = new Constant(value);
		
		assertEquals(constant1, constant2);
	}

	@ParameterizedTest
	@ValueSource(floats = { 5, 10 })
	void cloneTest(float value) {
		Constant constant1 = new Constant(value);
		Constant constant2 = constant1.clone();
		
		assertFalse(constant1 == constant2);
		assertEquals(constant1, constant2);
	}
	
}
