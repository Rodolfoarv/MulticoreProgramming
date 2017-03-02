/*-------------------------------------------------------------------
 * Práctica #3: Streaming
 * Fecha: 14-Feb-2017
 * Autor:
 *          A01169701 Rodolfo A. Ramirez Valenzuela
 *          A01371743 Luis E. Ballinas Aguilar
 *-------------------------------------------------------------------*/


import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.stream.*;
import java.util.function.Consumer;

public class CountingSort {
	
	// Method that creates a random array with a given size and seed for pseudo random numbers
	public static int[] makeRandomArray(int arraySize, long seed){
        int[] array = new int[arraySize];
        Random random = new Random(seed);
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt();
        }
        return array;
	}
	
	// Method that will return false if the array is not sorted.
    public static boolean isSorted(int[] array) {
        for (int i = 0, n = array.length - 1; i < n; i++) {
            if (array[i] > array[i + 1]) { // If the current element is bigger than the next index then that means the array is not sorted
                return false;
            }
        }
        return true; // The array is sorted
    }
    
    public static void printSortOutput(String version, int[] array, Consumer<int[]> c){
    	System.out.println(version);
        System.out.printf("The array is sorted: %b\n", isSorted(array));
        Instant start = Instant.now();
        c.accept(array);
        Instant end = Instant.now();
        Duration delta = Duration.between(start, end);
        System.out.printf("The array is sorted: %b\n", isSorted(array));
        System.out.printf("The time for the %s is: %.4f\n\n",version,delta.toMillis()/ 1000.0);
    }

    public static void sequentialCountSort(int arr[]) {
        int arrayLength = arr.length;
        int[] temp = new int[arrayLength];

        for (int i = 0; i < arrayLength; i++) {
            int count = 0;
            for (int j = 0; j < arrayLength; j++) {
                if (arr[j] < arr[i]) {
                    count++;
                } else if (arr[i] == arr[j] && j < i) {
                    count++;
                }
            }
            temp[count] = arr[i];
        }
        
        System.arraycopy(temp, 0, arr, 0, arrayLength);
    }

    public static void streamCountSort(int a[]) {
        final int arrayLength = a.length;
        final int[] temp = new int[arrayLength];

        IntStream
            .range(0, arrayLength)
            .parallel()
            .forEach(i -> {
                int count = 0;
                for (int j = 0; j < arrayLength; j++) {
                    if (a[j] < a[i]) {
                        count++;
                    } else if (a[i] == a[j] && j < i) {
                        count++;
                    }
                }
                temp[count] = a[i];
            });
        
        System.arraycopy(temp, 0, a, 0, arrayLength);
    }

    public static void main(String[] args) {
    	
    	System.out.println("COUNTING SORT:  \n\nNote: The array will not be printed in disorder and order so it will not affect the time comparison\n");
        int n = 10000;
        long seed = 30;
        int[] sequentialArray = makeRandomArray(n, seed);
        int[] streamArray = makeRandomArray(n, seed);
        printSortOutput("Sequential Counting Sort", sequentialArray, CountingSort::sequentialCountSort);
        printSortOutput("Stream Counting Sort", streamArray, CountingSort::streamCountSort);
    }
}