// Calcula el factorial, versión secuencial.

package mx.itesm.cem.pmultinucleo;

import java.math.BigInteger;

public class SequentialFactorial {

    public static void main(String[] args) {

        long timeStart = System.currentTimeMillis();

        BigInteger result = BigInteger.ONE;
        int n = 250_000;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        long timeEnd = System.currentTimeMillis();

        System.out.printf("Resultado = %d, tiempo = %.4f", 
            result.bitCount(), (timeEnd - timeStart) / 1000.0);
    }
}
