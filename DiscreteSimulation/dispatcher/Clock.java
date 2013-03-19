package dispatcher;

public class Clock {
	private static int time = 1;
	
	public static int getTime() {
		return time;
	}
	
	public static void setTime(int newTime) {
		time = newTime;
	}
}
