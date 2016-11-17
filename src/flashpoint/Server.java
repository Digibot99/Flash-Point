
package flashpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Server {

    private static final int PORT = 4000;
    
    public static void main(String[] args) throws Exception
    {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }
    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        
        public Handler(Socket _socket) {
            _socket = socket;
        }
        
        public void run() {
            try {
               System.out.println(socket.getInputStream()); 
            } catch (Exception e) {
                try {
                    socket.close();
                } catch (Exception i) {
                }
            }

        }
        
    public static int getPort()
    {
        return(PORT);
    }
    }
}
