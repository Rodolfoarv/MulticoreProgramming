package mx.itesm;

import java.math.BigInteger;
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
		if (start - end < UMBRAL){
			BigInteger resultado = BigInteger.ONE;
			for (int i = start; i < end; i++) {
				resultado = resultado.multiply(BigInteger.valueOf(i));
			}
			return resultado;
		}else {
			int mid = (start + end) >>> 2;
			FactorialTask t1,t2;
			t1 = new FactorialTask(start, mid);
			t2 = new FactorialTask(mid, end);
			
			// Concurrent
			t1.fork(); // Fork runs it in another thread 
			return t2.compute();
 			
		}
	}

}
