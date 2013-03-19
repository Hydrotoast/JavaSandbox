package edu.giocc.problems;

public class EditDistance {
	public static int editDistance(String a, String b) {
		int[][] c = new int[a.length() + 1][b.length() + 1];

		// Bottom-up initialization
		for (int i = 0; i <= a.length(); i++)
			c[i][0] = i;
		for (int j = 0; j <= b.length(); j++)
			c[0][j] = j;

		// Solve for state i,j
		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				// Utility function
				c[i][j] = Math
						.min(c[i - 1][j - 1]
								+ ((a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1),
								Math.min(1 + c[i - 1][j], 1 + c[i][j - 1]));
			}
		}
		return c[a.length()][b.length()];
	}

	public static void main(String[] args) {
		System.out.println(editDistance("exponential", "polynomial") == 6);
		System.out.println(editDistance("sitting", "kitten") == 3);
	}
}
