package comp410tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import LinkedList_A1.LinkedListImpl;
import LinkedList_A1.Node;
import LinkedList_A1.LIST_Interface;

public class Assignment1Tests {
	LIST_Interface L = new LinkedListImpl();
	
	@Test
	public void simpleInsortTest() {
		// This test is on the assignment details
		L.clear();
		
		L.insort(14);
		compareListToArray( new double[] { 14 } );
		L.insort(21);
		compareListToArray( new double[] { 14, 21 } );
		L.insort(5);
		compareListToArray( new double[] { 5, 14, 21 } );
		L.insort(17);
		compareListToArray( new double[] { 5, 14, 17, 21 } );
		L.insort(67);
		compareListToArray( new double[] { 5, 14, 17, 21, 67 } );
		
		L.clear();
		compareListToArray( new double[0] );
		
		L.insort(10);
		compareListToArray( new double[] { 10 } );
		L.insort(15);
		compareListToArray( new double[] { 10, 15 } );
		L.insort(15);
		compareListToArray( new double[] { 10, 15, 15 } );
		L.insort(20);
		compareListToArray( new double[] { 10, 15, 15, 20 } );
		L.insort(15);
		compareListToArray( new double[] { 10, 15, 15, 15, 20 } );
		L.insort(10);
		compareListToArray( new double[] { 10, 10, 15, 15, 15, 20 } );
	}
	
	@Test
	public void insortInsertRemoveTest() {
		L.clear();
		
		L.insort(14);
		compareListToArray( new double[] { 14 } );
		L.insort(21);
		compareListToArray( new double[] { 14, 21 } );
		L.insert(10,1);
		compareListToArray( new double[] { 14, 10, 21 } );
		L.remove(2);
		compareListToArray( new double[] { 14, 10 } );
		L.insort(8);
		compareListToArray( new double[] { 8, 14, 10 } );
		L.insert(20,3);
		compareListToArray( new double[] { 8, 14, 10, 20 } );
		L.insort(17);
		compareListToArray( new double[] { 8, 14, 10, 17, 20 } );
		L.insort(14);
		compareListToArray( new double[] { 8, 14, 10, 14, 17, 20 } );
		
		assertFalse( L.remove( 40 ) );
		compareListToArray( new double[] { 8, 14, 10, 14, 17, 20 } );
		assertTrue( L.remove(0) );
		compareListToArray( new double[] { 14, 10, 14, 17, 20 } );
		assertTrue( L.remove(1) );
		compareListToArray( new double[] { 14, 14, 17, 20 } );
		assertTrue( L.remove(0) );
		compareListToArray( new double[] { 14, 17, 20 } );
		assertTrue( L.remove(2) );
		compareListToArray( new double[] { 14, 17 } );
		assertTrue( L.remove(1) );
		compareListToArray( new double[] { 14 } );
		assertTrue( L.remove(0) );
		compareListToArray( new double[] {} );
		
		assertFalse( L.remove(0) );
		compareListToArray( new double[] {} );
		assertFalse( L.remove(1) );
		compareListToArray( new double[] {} );
		
		assertEquals( 0, L.size() );
	}
	
	@Test
	public void insortsGoAfterCheck() {
		L.clear();
		
		L.insort( 10 );
		compareListToArray( new double[] { 10 } );
		L.insort( 15 );
		compareListToArray( new double[] { 10, 15 } );
		
		// Get the Node
		Node node15 = ((LinkedListImpl)L).getLast();
		
		L.insort( 15 );
		
		// That node15 must still be in position 2
		assertEquals( node15, ((LinkedListImpl)L).getLast().prev );
	}
	
	@Test
	public void stupidIndexTest() {
		L.clear();
		
		assertFalse(L.insert( 10,  3 ) );
		compareListToArray( new double[] { } );
		assertFalse( L.insert( 10,  2 ) );
		compareListToArray( new double[] { } );
		assertFalse( L.insert( 10,  1 ) );
		compareListToArray( new double[] { } );
		
		// make a list
		L.insert( 3,  0 );
		compareListToArray( new double[] { 3 } );
		L.insert( 2,  0 );
		compareListToArray( new double[] { 2, 3 } );
		L.insert( 1,  0 );
		
		compareListToArray( new double[] { 1, 2, 3 } );
		
		// then do stupid stuff
		assertFalse( L.insert( 4,  4 ) );
		compareListToArray( new double[] { 1, 2, 3 } );
		assertFalse( L.insert( 4,  5 ) );
		compareListToArray( new double[] { 1, 2, 3 } );
		
		assertTrue( L.insert( 4,  3 ) );
		
		compareListToArray( new double[] { 1, 2, 3, 4 } );
		
		assertEquals( Double.NaN, L.get(4) );
	}
	
	@Test
	public void negativeInsortTest() {
		
		L.insort(-3.4);
		L.insort(-2.9);
		L.insort(-1.5);
		L.insort(-.5);
		L.insort(-4.9);
		// List should be -4.9 -3.4 -2.9 -1.5 -.5
		compareListToArray( new double[] {-4.9,-3.4,-2.9,-1.5,-.5});
		
		assertFalse(L.remove(-1));
		
		L.insert(-2.3, 1);
		L.insert(-6.6, 0);
		compareListToArray( new double[] {-6.6,-4.9,-2.3,-3.4,-2.9,-1.5,-.5});
		
		L.insort(-2.2);
		compareListToArray( new double[] {-6.6,-4.9,-2.3,-3.4,-2.9,-2.2,-1.5,-.5});
		
	}
	
	private void compareListToArray(double checkarray[] ) {
		assertEquals(L.size(), checkarray.length);
		
		// Check using get
		for( int i = 0; i < checkarray.length; ++i ) {
			assertEquals( L.get(i), checkarray[i] );
		}
		
		// Check manually
		Node head = ((LinkedListImpl)L).getRoot();
		Node tail = ((LinkedListImpl)L).getLast();
		Node cur = head;
		
		// if size is 0, then head and tail must be null
		if( checkarray.length == 0 ) {
			assertEquals(head,null);
			assertEquals(tail,null);
		} else if( checkarray.length == 1 ) {
			// size 1, and they should be equal
			assertEquals(head,tail);
		} else {
			// size > 1, should not be equal
			assertNotEquals(head,tail);
		}
		
		for( int i = 0; i < checkarray.length; ++i ) {
			assertEquals( cur.getData(), checkarray[i] );
			cur = cur.next;
		}
		
		assertEquals( cur, null );
		
		int checks = 0;
		cur = head;
		
		while( cur != null ) {
			assertEquals( cur.getData(), checkarray[checks++] );
			cur = cur.next;
		}
		
		assertEquals( checks, checkarray.length );
		assertEquals( cur, null );
	}
}
