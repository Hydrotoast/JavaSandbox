package edu.giocc.problems;

public class KnapsackProblem {
	private static class Item {
		public int weight;
		public int value;

		public Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public String toString() {
			return String.format("{%s, %s}", weight, value);
		}
	}

	// Objective function
	public static int maxValue(Item[] items, int maxWeight) {
		int[] c = new int[maxWeight + 1];
		c[0] = 0;

		// Solving for weight states
		for (int weight = 1; weight < c.length; weight++) {

			// Utility function
			c[weight] = 0;
			for (Item item : items) {
				if (item.weight <= weight
						&& c[weight - item.weight] + item.value > c[weight]) {
					c[weight] = c[weight - item.weight] + item.value;
				}
			}
		}
		return c[maxWeight];
	}

	public static void main(String[] args) {
		Item[] items = new Item[4];
		items[0] = new Item(6, 30);
		items[1] = new Item(3, 14);
		items[2] = new Item(4, 16);
		items[3] = new Item(2, 9);

		System.out.println(maxValue(items, 10) == 48);
	}
}
