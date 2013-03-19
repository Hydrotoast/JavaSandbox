package edu.giocc.tests;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.giocc.lexer.Lexer;
import edu.giocc.lexer.Lexer.Token;

public class LexerTests {
	@Test
	public void canTokenizeArithmetic() {
		ArrayList<Token> tokens = Lexer.lex("11+22-33");

		assertEquals("(NUMBER 11)", tokens.remove(0).toString());
		assertEquals("(BINARYOP +)", tokens.remove(0).toString());
		assertEquals("(NUMBER 22)", tokens.remove(0).toString());
		assertEquals("(BINARYOP -)", tokens.remove(0).toString());
		assertEquals("(NUMBER 33)", tokens.remove(0).toString());
	}
	
	@Test
	public void canIgnoreWhitespace() {
		ArrayList<Token> tokens = Lexer.lex("11\t+22\f- 33\r\n");

		assertEquals("(NUMBER 11)", tokens.remove(0).toString());
		assertEquals("(BINARYOP +)", tokens.remove(0).toString());
		assertEquals("(NUMBER 22)", tokens.remove(0).toString());
		assertEquals("(BINARYOP -)", tokens.remove(0).toString());
		assertEquals("(NUMBER 33)", tokens.remove(0).toString());
	}
}
