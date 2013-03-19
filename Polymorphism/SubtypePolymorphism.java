
public class SubtypePolymorphism {
	public static abstract class Super {
		public abstract void overridable();
	}
	
	public static class SubA extends Super {
		public void overridable() {}
		public void invokeOnA() {}
	}
	
	public static class SubB extends Super {
		public void overridable() {}
		public void invokeOnB() {}
	}
}
