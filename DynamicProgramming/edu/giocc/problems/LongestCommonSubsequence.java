package edu.giocc.problems;

// Start		4:09 AM
// End			4:16 AM
// Elapsed Time	7 min.

public class LongestCommonSubsequence {
	// Objective function
	public static int LCS(String a, String b) {
		int[][] c = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i <= a.length(); i++)
			c[i][0] = 0;
		for (int j = 0; j <= b.length(); j++)
			c[0][j] = 0;

		// Solve for state i,j
		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				// Utility function
				if (a.charAt(i - 1) == b.charAt(j - 1))
					c[i][j] = c[i - 1][j - 1] + 1;
				else
					c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
			}
		}
		return c[a.length()][b.length()];
	}

	public static void main(String[] args) {
		System.out.println(LCS("exponential", "polynomial") == 6);
		System.out.println(LCS("program", "pangram") == 5);
	}
}
