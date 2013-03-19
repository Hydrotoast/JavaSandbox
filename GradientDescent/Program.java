
public class Program {
	public static double x_old = 0;
	public static double x_new = 6;
	public static double eps = (double) 0.01;
	public static double precision = (double) 0.0001;
	
	public static double directional_derivative(double x) {
		return 4 * Math.pow(x, 3) - 9 * Math.pow(x, 2);
	}
	
	public static void main(String[] args) {
		while (Math.abs(x_new - x_old) > precision) {
			x_old = x_new;
			x_new = x_old - eps * directional_derivative(x_old);
		}
		
		System.out.println("Local Minimum at " + x_new);
	}
}
