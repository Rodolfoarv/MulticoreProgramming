/**
 * @author Ariel Ortiz
 * 
 * A very simple web server.
 * Implements a minuscule subset of RFC 1945 (HTTP/1.0)
 * http://www.ietf.org/rfc/rfc1945.txt 
 */

package mx.itesm.cem.pmultinucleo.http;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Starts the web server and listens to client connections. Individual 
 * connections are processed by Worker instances.
 */
public class WebServer {

    public static final int PORT_NUMBER = 12345;

    private void listen() {
        System.out.printf("Minuscule web server ready. Listening at port %d...%n", 
                PORT_NUMBER);
        
        try (
                ServerSocket servSock = new ServerSocket(PORT_NUMBER)
            ) {
        	
        	ExecutorService pool = Executors.newFixedThreadPool(4);
            
            while (true) {
                Socket sock = servSock.accept();
                Worker w = new Worker(sock);
                pool.execute(() -> w.doWork());
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new WebServer().listen();
    }
}
