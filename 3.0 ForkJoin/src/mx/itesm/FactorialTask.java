package mx.itesm;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<BigInteger> {
	
	// It is recommended to use these two 
	// FOrk join will divide the problem in many subsets ofthe problem, usually a half.
	
	
	private final int start;
	private final int end;
	
	// We define a threshold
	
	private static final int UMBRAL = 1000;
	
	public FactorialTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected BigInteger compute() {
		if (end - start < UMBRAL){
			BigInteger resultado = BigInteger.ONE;
			for (int i = start; i < end; i++) {
				resultado = resultado.multiply(BigInteger.valueOf(i));
			}
			return resultado;
		}else {
			int mid = (start + end) >>> 1;
			System.out.println(mid);
			FactorialTask t1,t2;
			t1 = new FactorialTask(start, mid);
			t2 = new FactorialTask(mid, end);
			
			// Concurrent
			t1.fork(); // Fork runs it in another thread 
			return t2.compute().multiply(t1.join()); // The join is part of the object Factorial Task, which is Recursive Task 			
		}
	}
	
	public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
		int n = 1_000_000;
		
		FactorialTask task = new FactorialTask(2, n+1);
		ForkJoinPool pool = new ForkJoinPool();
		BigInteger resultado = pool.invoke(task);
		
        long timeEnd = System.currentTimeMillis();

        System.out.printf("Resultado = %d, tiempo = %.4f", resultado.bitCount(), (timeEnd - timeStart) / 1000.0);
		
	}

}
