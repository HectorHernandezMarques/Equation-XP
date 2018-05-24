package es.xp.ejercice01.equation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// toString

public class FractionTest {

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
