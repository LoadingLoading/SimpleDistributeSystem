import java.io.IOException;
import java.net.ServerSocket;

/**
 * A simple class that opens a server socket, and prints each message received
 * to the console.
 * @author Graeme Stevenson (graeme.stevenson@ucd.ie)
 */
public class TCPSocketServer {

   /**
    * Accept this many connections.
    */
   private int my_backlog = 5;

   int order;

   /**
    * The server socket.
    */
   private ServerSocket my_serverSocket;

   /**
    * Create the server socket.
    * @param a_port the port that the server socket should listen on.
    */
   public TCPSocketServer(int a_port) {
      try {
         my_serverSocket = new ServerSocket(a_port, my_backlog);
         System.out.println("TCP socket listening on port " + a_port);
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } catch (SecurityException se) {
         se.printStackTrace();
      }
   }

   /**
    * Method that listens on the server socket forever and prints each incoming
    * message to the console.
    */

   public void listen() {
      while (true) {
         try {

            new Thread(new Connection(my_serverSocket.accept())).start();
            order+=1;
            System.out.println("order: " + order);





         } catch (IOException ioe) {
            ioe.printStackTrace();
         } catch (SecurityException se) {
            se.printStackTrace();
         }
      }
   }

   /**
    * Parse the arguments to the program and create the server socket.
    * @param args the program arguments. Should take the form &lt;port&gt;
    */
   public static void main(String[] args) {
      int port = 0;

      // Check we have the right number of arguments and parse
      if (args.length == 1) {
         try {
            port = Integer.valueOf(args[0]);

         } catch (NumberFormatException nfe) {
            System.err.println("The value provided for the port is not an integer");
            nfe.printStackTrace();
         }
         // Create the server
         TCPSocketServer server = new TCPSocketServer(port);
         // Listen on the server socket. This will run until the program is
         // killed.
         server.listen();
      } else {
         System.out.println("Usage: java TCPSocketServer <port>");
      }

   }
} // end class

