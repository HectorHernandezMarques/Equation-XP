package es.xp.ejercice01.equation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import es.xp.ejercice01.equation.Variable;

class VariableTest {

	@ParameterizedTest
	@ValueSource(strings = { "x", "y" })
	void twoParametersConstructorTest(String name) {
		Variable variable = new Variable(5, name);
		assertEquals(new Fraction(5), variable.getValue());
		assertEquals(name, variable.getName());

	}
	
	@ParameterizedTest
	@ValueSource(strings = { "x", "y" })
	void threeParametersConstructorTest(String name) {
		Variable variable = new Variable(5, 2, name);
		assertEquals(new Fraction(5, 2), variable.getValue());
		assertEquals(name, variable.getName());

	}

	@ParameterizedTest
	@ValueSource(strings = { "x", "y" })
	public void hasNameTest(String name) {
		Variable variable = new Variable(5, name);
		assertTrue(variable.hasName(name));
	}

	@ParameterizedTest
	@ValueSource(strings = { "x", "y" })
	public void notHasNameTest(String name) {
		Variable variable = new Variable(5, name);
		assertFalse(variable.hasName("z"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "x", "y" })
	public void hasSetNameTest(String name) {
		Variable variable = new Variable(5, name);
		Set<String> names = new HashSet<String>();
		names.add(name);
		assertTrue(variable.hasName(names));
	}

	@ParameterizedTest
	@ValueSource(strings = { "x", "y" })
	public void notHasSetNameTest(String name) {
		Variable variable = new Variable(5, name);
		Set<String> names = new HashSet<String>();
		names.add("z");
		assertFalse(variable.hasName(names));
	}

	@ParameterizedTest
	@ValueSource(strings = { "x", "y" })
	void equalsTest(String name) {
		Variable variable1 = new Variable(5, name);
		Variable variable2 = new Variable(5, name);

		assertEquals(variable1, variable2);
	}

	@ParameterizedTest
	@ValueSource(strings = { "x", "y" })
	void cloneTest(String name) {
		Variable variable1 = new Variable(5, name);
		Variable variable2 = variable1.clone();

		assertFalse(variable1 == variable2);
		assertEquals(variable1, variable2);
		
		assertNotEquals(variable2, variable1.multiply(new Fraction(5)));
	}

	@ParameterizedTest
	@ValueSource(strings = { "x", "y" })
	public void toStringTestWithPositiveValue(String name) {
		Variable variable = new Variable(5, name);
		assertEquals("(5/1)" + name, variable.toString());
	}

	@ParameterizedTest
	@ValueSource(strings = { "x", "y" })
	public void toStringTestWithNegativeValue(String name) {
		Variable variable = new Variable(-5, name);
		assertEquals("(-5/1)" + name, variable.toString());
	}

}
