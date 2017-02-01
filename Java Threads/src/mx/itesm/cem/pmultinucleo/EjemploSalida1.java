// Utiliza syncronización para alternar las líneas de 
// salida de los dos thread.

package mx.itesm.cem.pmultinucleo;

public class EjemploSalida1 implements Runnable {
    
    private static Thread[] arreglo = new Thread[2];
    private static int indice;
    private static Object comun = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (comun) {
                Thread actual = Thread.currentThread();
                while (arreglo[indice] != actual) {
                    try {
                        comun.wait();
                    } catch (InterruptedException e) {
                        // never happens
                    }
                }
                System.out.println(
                        actual.getName() + " " + i);
                indice = (indice + 1) % 2;
                comun.notifyAll();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        EjemploSalida1 es1 = new EjemploSalida1();
        Thread t1 = new Thread(es1);
        EjemploSalida1 es2 = new EjemploSalida1();        
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
