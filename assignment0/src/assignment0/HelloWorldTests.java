package assignment0;

import static org.junit.Assert.*;

import org.junit.Test;

public class HelloWorldTests {

	HelloWorld helloworld = new HelloWorld();
	
	@Test
	public void testSay_it() {
		assertEquals("hello world", helloworld.say_it());
	}

	@Test
	public void testSay_it_loud() {
		assertEquals("HELLO WORLD", helloworld.say_it_loud());
	}

}
