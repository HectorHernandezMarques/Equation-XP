package es.xp.ejercice01.equation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

// toString

public class FractionTest {

	@Test
	public void constructorTest() {
		assertEquals("(1/2)", new Fraction(0.5f).toString());
		assertEquals("(3/1)", new Fraction(3.0f).toString());
	}
	
	@Test
	void cloneTest() {
		Fraction fraction1 = new Fraction(1, 2);
		Fraction fraction2 = fraction1.clone();

		assertFalse(fraction1 == fraction2);
		assertEquals(fraction1, fraction2);
		fraction1 = fraction1.multiply(new Fraction(5));
		assertNotEquals(fraction1, fraction2);
	}

	@Test
	public void multiplyTest() {
		assertEquals(new Fraction(8, 7), new Fraction(2, 7).multiply(4));
		assertEquals(new Fraction(3, 2), new Fraction(1, 4).multiply(6));
	}

	@Test
	public void multiplyNegativeTest() {
		assertEquals(new Fraction(-8, 7), new Fraction(2, 7).multiply(-4));
	}

	@Test
	public void addTest() {
		assertEquals(new Fraction(13, 12), new Fraction(1, 3).add(new Fraction(3, 4)));
		assertEquals(new Fraction(1, 2), new Fraction(1, 4).add(new Fraction(1, 4)));
	}
}
