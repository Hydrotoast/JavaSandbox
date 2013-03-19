package com.giocc.parser;

public class CssBuilder {
	private static final String IDENT_MATCH = "[_a-zA-Z][a-zA-Z0-9]*";
	private static final String LBRACE = "{";
	private static final String RBRACE = "}";
	
	private static CssToken build(String token) {
		if (token.matches(IDENT_MATCH)) {
			return buildIdentToken();
		} else if (token.matches(LBRACE)) {
			return buildLBraceToken();
		} else if (token.matches(RBRACE)) {
			return buildRBraceToken();
		} else
			// TODO: Throw a decent exception
			return null;
	}
	
	private static IdentToken buildIdentToken() {
		return new IdentToken();
	}
	
	private static LBraceToken buildLBraceToken() {
		return new LBraceToken();
	}
	
	private static RBraceToken buildRBraceToken() {
		return new RBraceToken();
	}
}
