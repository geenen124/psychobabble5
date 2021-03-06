package junitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import libraryClasses.Attacker;
import libraryClasses.Player;
import libraryClasses.Positions;
import libraryClasses.Standings;

import org.junit.Test;

public class PositionsTest {

	@Test
	public void testPositions() {
		Player[] expected = new Player[11];
		Positions p = new Positions();
		Player[] result = p.getPositionArray();
		assertEquals(expected, result);
	}
	
	@Test
	public void testContains() {
		Positions p = new Positions();
		Player[] array1 = new Player[11];
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		array1[0]=attacker;
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, true, 88, 96, 45, 80);
		p.setPositionArray(array1);
		assertTrue(p.contains(attacker));
		assertFalse(p.contains(attacker2));
	}
	
	@Test
	public void testToString() {
		Positions p = new Positions();
		Player[] array1 = new Player[11];
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		array1[0]=attacker;
		for (int i=1;i<11;i++) {
			array1[i]=null;
		}
		p.setPositionArray(array1);
		String expected = "Positions [1=OOPBoy,2=null,3=null,4=null,5=null,6=null,7=null,8=null,9=null,10=null,11=null,]";
		assertEquals(expected, p.toString());
	}
	
	@Test
	public void testRemove() {
		Positions p = new Positions();
		Player[] array1 = new Player[11];
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		array1[1]=attacker;
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, true, 88, 96, 45, 80);

		array1[0]=null;
		array1[2]=attacker2;
		p.setPositionArray(array1);
		p.remove(attacker2);
		assertTrue(p.contains(attacker));
		assertFalse(p.contains(attacker2));
	}

	@Test
	public void testPositionsPlayerArray() {
		Player[] array1 = new Player[11];
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		array1[0]=attacker;
		array1[5]=attacker;
		Player[] array2 = new Player[11];
		array2[0]=attacker;
		array2[5]=attacker;
		Positions p = new Positions(array2);
		Player[] result = p.getPositionArray();
		assertEquals(result[0], array1[0]);
		assertEquals(result[5], array1[5]);
	}

	@Test
	public void testGetPositionArray() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		Player[] array = new Player[11];
		array[0]=attacker;
		Positions p = new Positions(array);
		Player[] result = p.getPositionArray();
		assertEquals(result, array);
	}

	@Test
	public void testSetPositionArray() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		Player[] array1 = new Player[11];
		array1[0]=attacker;
		array1[1]=attacker;
		Positions p = new Positions(array1);
		Player[] array2 = new Player[11];
		array2[2]=attacker;
		array2[3]=attacker;
		p.setPositionArray(array2);
		Player[] result = p.getPositionArray();
		assertNull(result[0]);
		assertNotNull(result[2]);
		assertEquals(result[2], attacker);
	}
	
	@Test
	public void testEquals() {
		Positions p = new Positions();
		Positions p2 = new Positions();
		assertTrue(p.equals(p));
		assertFalse(p.equals(null));
		assertFalse(p.equals(new Standings(1, 2, 3, 4, 5, "team1")));
		Player[] array1 = new Player[11];
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		array1[0]=attacker;
		p.setPositionArray(array1);
		assertFalse(p.equals(p2));
		p2.setPositionArray(array1);
		assertTrue(p.equals(p2));
	}

}
