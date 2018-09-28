/**
 * A simple class that opens a socket, sends a message to the server, and
 * terminates.
 * @author Graeme Stevenson (graeme.stevenson@ucd.ie)
 */
public class TCPSocketClient {

   /**
    * The server host name.
    */
   public String my_serverHost;

   /**
    * The server port.
    */
   public int my_serverPort;

   /**
    * Sets the client up with the server details.
    * @param the_serverHost the server host name.
    * @param the_serverPort the server port.
    */
   public TCPSocketClient(String the_serverHost, int the_serverPort) {
      my_serverHost = the_serverHost;
      my_serverPort = the_serverPort;
   }

   /**
    * Parse the arguments to the program, create a socket, and send a message.
    * @param args the program arguments. Should take the form &lt;host&gt;
    *           &lt;port&gt; &lt;message&gt; &lt;message&gt;
    */
   public static void main(String[] args) {

      String host = null;
      int port = 0;


      // Check we have the right number of arguments and parse
      if (args.length == 2) {
         host = args[0];
         try {
            port = Integer.valueOf(args[1]);
         } catch (NumberFormatException nfe) {
            System.err.println("The value provided for the port is not an integer");
            nfe.printStackTrace();
         }


         // Create the client
         EchoClient client = new EchoClient(host, port);
         // Send a message using the client
         try{
            //connect it
            client.connect();

            for(int a=1;a<=100;a++){
               int[] R=new int[3];
               int r1 = (int)(Math.random()*100);
               int r2 = (int)(Math.random()*100);
               R[0]=r1+1;
               R[1]=r2+1;
               R[2]=a;
               client.sendMessage(R);
            }

         }catch (Exception e) {
            throw e;
         } finally {
            client.close();
         }

      } else {
         System.out.println("Usage: java TCPSocketClient <host> <port> <message>");
      }

   }
} // end class

