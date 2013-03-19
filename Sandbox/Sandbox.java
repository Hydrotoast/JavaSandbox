public class Sandbox {
	public static class LN {
		private int value;
		private LN next;

		public LN(int i, LN n) {
			value = i;
			next = n;
		}

		public String toString() {
			return "" + value;
		}
	}
	
	public static void report(LN l, LN t1, LN t2) {
		LN node = l;
		System.out.print("L: ");
		while (node != null) {
			System.out.print(node + " ");
			node = node.next;
		}
		System.out.println();
		
		node = t1;
		System.out.print("t1: ");
		while (node != null) {
			System.out.print(node + " ");
			node = node.next;
		}
		System.out.println();
		
		node = t2;
		System.out.print("t2: ");
		while (node != null) {
			System.out.print(node + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void mystery(LN l) {
		LN t1 = null;
		LN t2 = null;
		while (l.next != null && l.next.next != null) {
			t1 = l.next;
			t2 = t1.next;
			t1.next = t2.next;
			t2.next = t1;
			l.next = t2;
			l = t1;

			report(l, t1, t2);
		}
	}

	public static void main(String[] args) {
		LN l = new LN(3, new LN(5, new LN(2, new LN(4, new LN(8,
				new LN(7, null))))));
		LN node = l;
		System.out.print("Original: ");
		while (node != null) {
			System.out.print(node + " ");
			node = node.next;
		}
		System.out.println();

		mystery(l);
	}
}
