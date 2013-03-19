package edu.giocc.problems;

public class RodCutting {
	public static int maxRevenue(int[] priceTable, int length) {
		int[] revenue = new int[length + 1];
		revenue[0] = 0;
		revenue[1] = 1;

		// Solve previous states
		// i depends on i - 1
		for (int i = 2; i <= length; i++) {
			revenue[i] = priceTable[i];
			
			// Utility function for maximizing revenue for length i
			for (int j = 0; j <= i; j++)
				revenue[i] = Math.max(revenue[i], revenue[i - j]
						+ priceTable[j]);
		}

		return revenue[length];
	}

	public static void main(String[] args) {
		int[] priceTable = new int[] { 0, 1, 5, 8, 9, 10, 17, 17, 20 };

		System.out.println(maxRevenue(priceTable, 1) == 1);
		System.out.println(maxRevenue(priceTable, 2) == 5);
		System.out.println(maxRevenue(priceTable, 3) == 8);
		System.out.println(maxRevenue(priceTable, 4) == 10);
	}
}
