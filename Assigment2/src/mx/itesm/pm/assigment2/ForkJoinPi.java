package mx.itesm.pm.assigment2;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
 
 
public class ForkJoinPi extends RecursiveTask<Double>{
   
    private static final int RECTS = 1_000_000;
    private static final int UMBRAL = 500;
    public static final double WIDTH = 1.0 / RECTS;
    
    private long start, end;
   
   
    public ForkJoinPi(long start, long end){
        this.start = start;
        this.end = end;
    }
   
    @Override
    protected Double compute() {
        if(end - start < UMBRAL){
 
            double mid, height, sum;
            sum = 0;
            for (long i = start; i < end; i++) {
                mid = (i + 0.5) * WIDTH;
                height = 4.0 / (1.0 + mid * mid);
                sum += height;
            }
            return sum;
        }else{
            long mid = (start + end) >>> 1;
            ForkJoinPi t1;
            ForkJoinPi t2;
            t1 = new ForkJoinPi(start, mid);
            t2 = new ForkJoinPi(mid, end);
            t1.fork();
            double r1 = t2.compute();
            double r2 = t1.join();
            return  (r1+r2);
        }
    }
   
        public static void main(String[] args){
        double area;
        long timeStart = System.currentTimeMillis();
        ForkJoinPi task = new ForkJoinPi(0, RECTS );
        ForkJoinPool pool = new ForkJoinPool();
        double sum = pool.invoke(task);
        
        area = WIDTH * sum;
       
        long timeEnd = System.currentTimeMillis();
       
        System.out.printf("Resultado = %f, tiempo = %.4f",
         area, (timeEnd - timeStart)/1000.0); //.4 le manda 4 decimales de punto flotante
    }
   
}
