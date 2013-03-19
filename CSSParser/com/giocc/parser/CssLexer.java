package com.giocc.parser;

import java.util.NoSuchElementException;

public class CssLexer {
	private static final char LBRACE = '{';
	private static final char RBRACE = '}';
	private static final char COMMA = ',';
	private static final char COLON = ':';
	private static final char SEMICOLON = ';';

	private String src;
	private int srcIndex;

	public CssLexer(String src) {
		this.src = src.trim();
		srcIndex = 0;
	}

	public String nextToken() throws NoSuchElementException {
		int start = srcIndex,
			end = srcIndex;
		while (hasNext()) {
			switch (src.charAt(srcIndex)) {
			case LBRACE:
			case RBRACE:
			case COLON:
			case SEMICOLON:
				srcIndex++;
				return new String(src.substring(start, end));
			default:
				srcIndex++;
			}
		}
		
		throw new NoSuchElementException();
	}

	public boolean hasNext() {
		return srcIndex != src.length() - 1;
	}
}
