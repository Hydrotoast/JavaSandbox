package com.giocc.parser;
public class CssParser {
	private static enum Symbol {
		SELECTOR, SIMPLE_SELECTOR, IDENT, COMMA, LBRACE, DECL_BLOCK, DECL, DECL_SIMPLE, PROPERTY, COLON, VALUE, SEMICOLON, RBRACE;
	}

	private CssLexer lexer;
	private Symbol curSymbol;

	public CssParser(String src) {
		lexer = new CssLexer(src);
	}

	public void parse() {
		while (lexer.hasNext()) {
			RuleSet();
		}
	}
	
	public void Statement() {
		if (accept(Symbol.IDENT)) {
			getSym();
			expect(Symbol.PROPERTY);
			getSym();
			expect(Symbol.COLON);
			getSym();
			expect(Symbol.VALUE);
		} else {
			error("Bad statement");
		}
	}

	public void Declaration() {
		if (accept(Symbol.DECL)) {
			Declaration();
			Statement();
			getSym();
			expect(Symbol.SEMICOLON);
			getSym();
			expect(Symbol.RBRACE);
		} else if (accept(Symbol.DECL_SIMPLE)) {
			Statement();
		} else
			error("Bad declaration");
	}

	public void Selector() {
		if (accept(Symbol.SELECTOR)) {
			Selector();
			getSym();
			expect(Symbol.IDENT);
		} else if (accept(Symbol.SIMPLE_SELECTOR)) {
			getSym();
			expect(Symbol.IDENT);
		} else
			error("Bad selector");
	}

	public void RuleSet() {
		Selector();
		expect(Symbol.LBRACE);
		Declaration();
	}

	private boolean accept(Symbol symbol) {
		return curSymbol.equals(symbol);
	}

	private boolean expect(Symbol symbol) {
		if (accept(symbol))
			return true;

		getSym();
		return false;
	}

	private void getSym() {
		curSymbol = Symbol.valueOf(lexer.nextToken());
	}

	private void error(String message) {
		System.err.println(message);
	}
}
