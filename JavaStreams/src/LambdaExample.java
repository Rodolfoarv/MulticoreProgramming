import java.util.function.IntUnaryOperator;

@FunctionalInterface
interface MyFunInter {
	public abstract int f(int x);
	
}


public class LambdaExample {
	
	public static void main(String[] args) {
		MyFunInter g = x-> x+1;
		
		
		MyFunInter h = new MyFunInter() {
			
			@Override
			public int f(int x) {
				// TODO Auto-generated method stub
				return x+1;
			}
		};
		
		IntUnaryOperator i = x -> x+1;
		
		System.out.println(h.f(6));
		System.out.println(g.f(5));	
		System.out.println(i.applyAsInt(7));
	}
	
	
	
	
}
