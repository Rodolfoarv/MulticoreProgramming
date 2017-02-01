// Utiliza syncronización para generar toda la salida
// del primer thread y luego toda la salida de segundo
// thread.

package mx.itesm.cem.pmultinucleo;

public class EjemploSalida2 implements Runnable {
    
    private static Thread[] arreglo = new Thread[2];
    private static int indice;
    private static Object comun = new Object();

    @Override
    public void run() {
        synchronized (comun) {
            Thread actual = Thread.currentThread();
            while (arreglo[indice] != actual) {
                try {
                    comun.wait();
                } catch (InterruptedException e) {
                    // never happens
                }
            }
            for (int i = 0; i < 100; i++) {
                System.out.println(actual.getName() + " " + i);                
            }
            indice = (indice + 1) % 2;
            comun.notifyAll();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        EjemploSalida2 es1 = new EjemploSalida2();
        Thread t1 = new Thread(es1);
        EjemploSalida2 es2 = new EjemploSalida2();        
        Thread t2 = new Thread(es2);
        arreglo[0] = t1;
        arreglo[1] = t2;
        indice = 0;
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        System.out.println("Ya terminé");
    }
}
