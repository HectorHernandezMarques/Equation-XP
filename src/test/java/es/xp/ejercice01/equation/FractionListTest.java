package es.xp.ejercice01.equation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FractionListTest {
	//AddFirtsElement 		DONE
	//RemoveFirstElement	DONE
	//LengthOfList
	//ContainsThisElement
	
	@Test
	public void addFirtsElementTest() {
		FractionList list = new FractionList();
		Fraction fraction = new Fraction(1, 2);
		list.addFirts(fraction);
		assertTrue(list.containsThis(fraction));
		assertEquals(1, list.length());
	}
	
	@Test
	public void removeFirstElement() {
		FractionList list = new FractionList();
		Fraction fraction1 = new Fraction(1, 2);
		Fraction fraction2 = new Fraction(2, 3);
		list.addFirts(fraction1);
		list.addFirts(fraction2);
		assertEquals(fraction2,list.removeFirstElement());
		assertFalse(list.containsThis(fraction2));
		assertTrue(list.containsThis(fraction1));
		assertEquals(1, list.length());
	}
	

}
