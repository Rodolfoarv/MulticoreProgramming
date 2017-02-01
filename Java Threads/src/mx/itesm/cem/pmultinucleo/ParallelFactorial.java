// Calcula el factorial, versión paralela.
// Permite cambiar fácilmente el número de threads utilizados.

package mx.itesm.cem.pmultinucleo;

import java.math.BigInteger;

public class ParallelFactorial implements Runnable {

    private int start;
    private int end;
    private BigInteger result = BigInteger.ONE;

    public ParallelFactorial(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long timeStart = System.currentTimeMillis();

        int n = 250_000;
        int numThreads = 16;
        if (n % numThreads != 0) {
            System.err.println(n + " no es exactamente divisible entre " + numThreads);
            System.exit(1);
        }
        int sliceSize = n / numThreads;
        ParallelFactorial[] pf = new ParallelFactorial[numThreads];
        Thread[] t = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            pf[i] = new ParallelFactorial(i * sliceSize + 1, (i + 1) * sliceSize);
            t[i] = new Thread(pf[i]);
            t[i].start();
        }
        
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < numThreads; i++) {
            t[i].join();
            result = result.multiply(pf[i].result);
        }        

        long timeEnd = System.currentTimeMillis();

        System.out.printf("Resultado = %d, tiempo = %.4f", result.bitCount(), (timeEnd - timeStart) / 1000.0);
    }
}
