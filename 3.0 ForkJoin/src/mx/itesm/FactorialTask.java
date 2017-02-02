package mx.itesm;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<BigInteger> {
	
	// It is recommended to use these two 
	
	private final int start;
	private final int end;
	
	public FactorialTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected BigInteger compute() {
		// TODO Auto-generated method stub
		return null;
	}

}
