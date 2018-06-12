package es.xp.ejercice01.equation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import es.xp.ejercice01.equation.Constant;
import es.xp.ejercice01.equation.Term;

public class TermTest {

	@ParameterizedTest
	@ValueSource(ints = { 5, 10 })
	public void multiplyBy10Test(int value) {
		Term term = new Constant(value);

		assertEquals(new Constant(10 * value), term.multiply(new Fraction(10)));
		assertEquals(new Constant(value), term);
	}

	@Test
	public void hasNameTestWithVariable() {
		Term term = new Variable(5, "x");
		assertTrue(term.hasName("x"));
	}
	
	@Test
	public void notHasNameTestWithVariable() {
		Term term = new Variable(5, "x");
		assertFalse(term.hasName("z"));
	}

	@Test
	public void hasNameTestWithConstant() {
		Term term = new Constant(5);
		assertFalse(term.hasName("x"));
	}

	@Test
	public void hasSetNameTest() {
		Term term = new Variable(5, "x");
		Set<String> names = new HashSet<String>();
		names.add("x");
		names.add("y");
		assertTrue(term.hasName(names));
	}
	
	@Test
	public void notHasSetNameTest() {
		Term term = new Variable(5, "z");
		Set<String> names = new HashSet<String>();
		names.add("x");
		names.add("y");
		assertFalse(term.hasName(names));
	}

	@Test
	public void equalTestWithConstant() {
		Term term = new Constant(5);
		assertEquals(new Constant(5), term);
	}
	
	@Test
	public void equalTestWithVariable() {
		Term term = new Variable(5, "x");
		assertEquals(new Variable(5, "x"), term);
	}

	@Test
	public void toStringTestWithConstantPositive() {
		Term term = new Constant(5);
		assertEquals("(5/1)", term.toString());
	}
	
	@Test
	public void toStringTestWithVariablePositive() {
		Term term = new Variable(5, "x");
		assertEquals("(5/1)x", term.toString());
	}

}
