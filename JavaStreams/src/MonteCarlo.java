/*-------------------------------------------------------------------
 * Práctica #3: Streaming
 * Fecha: 14-Feb-2017
 * Autor:
 *          A01169701 Rodolfo A. Ramirez Valenzuela
 *          A01371743 Luis E. Ballinas Aguilar
 *-------------------------------------------------------------------*/

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;


public class MonteCarlo {
	
    public static double random(long i) {
    	// Generar dos números aleatorios entre -1 y 1.
        double x = ThreadLocalRandom.current().nextDouble() * 2 - 1;
        double y = ThreadLocalRandom.current().nextDouble() * 2 - 1;
        double h = x * x + y * y;

        if (h <= 1) {
            return 1.0;
        }
        return 0.0;
    }
    
    public static double streamMonteCarlo(int n){
    	double calculation;
    	calculation = LongStream
    					.range(0,n)
    					.parallel()
    					.mapToDouble(MonteCarlo::random)
    					.sum() / n * 4;
    	return calculation;
    }

    public static void sequentialMonteCarlo(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            // Generar dos números aleatorios entre -1 y 1.
            double x = ThreadLocalRandom.current().nextDouble() * 2 - 1;
            double y = ThreadLocalRandom.current().nextDouble() * 2 - 1;
 
            // Aplicar teorema de Pitágoras.
            double h = x * x + y * y;
 
            // Verificar si el tiro cayó dentro del círculo.
            if (h <= 1) {
                sum++;
            }
        }
        double result =  4 * ((double) sum / n);
    }
    
    public static void main(String[] args) {
		int n = 100_000_000;
        Instant start = Instant.now();
        streamMonteCarlo(n);
        Instant end = Instant.now();
        Duration delta = Duration.between(start, end);
        System.out.printf("The time for the Stream Version is: %.4f\n\n",delta.toMillis()/ 1000.0);
        
        start = Instant.now();
        sequentialMonteCarlo(n);
        end = Instant.now();
        delta = Duration.between(start, end);
        System.out.printf("The time for the Sequential Version is: %.4f\n\n",delta.toMillis()/ 1000.0);
	}
    
    

}
