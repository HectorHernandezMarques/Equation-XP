package es.xp.ejercice01.equiation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TermTest {
	
	@ParameterizedTest
	@ValueSource(floats = { 5, 10 })
	public void multiplyBy10Test(float value) {
		Term term = new Constant(value);
		term.multiply(10);
		
		assertEquals(new Constant(10*value), term);
	}
	
	@Test
	public void hasNameTest() {
		Term term = new Constant(5);
		assertFalse(term.hasName("x"));
	}	
	
	@Test
	public void hasSetNameTest() {
		Term term = new Constant(5);
		Set<String> names = new HashSet<String>();
		names.add("x");
		assertFalse(term.hasName(names));
	}
	
	@Test
	public void equalTest() {
		Term term = new Constant(5);
		assertEquals(new Constant(5), term);
	}

	@Test
	public void toStringTest() {
		Term term = new Constant(5);
		assertEquals("5.0", term.toString());
	}
	
	
	
}
