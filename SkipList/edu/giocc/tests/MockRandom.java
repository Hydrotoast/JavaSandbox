package edu.giocc.tests;

import java.util.ArrayList;
import java.util.Random;

public class MockRandom extends Random {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2196843626311881333L;

	private ArrayList<Integer> results;
	private int nextNum;

	public MockRandom(ArrayList<Integer> results) {
		this.results = results;
		nextNum = 0;
	}

	public int nextInt() {
		int nextResult = Math.abs(results.get(nextNum));
		nextNum = ++nextNum % results.size();
		return nextResult;
	}

	public int nextInt(int n) {
		return Math.abs(nextInt());
	}
}
