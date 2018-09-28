import java.io.*;
import java.net.Socket;

public class EchoClient {
    private String host;
    private int port;
    private Socket mySocket;

    private DataInputStream is;
    private DataOutputStream os;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void connect() {
        try {
            mySocket = new Socket(host, port);
            os = new DataOutputStream(mySocket.getOutputStream());
            is = new DataInputStream(mySocket.getInputStream());
        } catch (IOException e) { e.printStackTrace(); }
    }


    public void sendMessage(int[] R) {
        try {
            os.writeBytes(R[0] + "\n"+R[1]+"\n"+R[2]+"\n");
            System.out.println(is.readLine());
        } catch (IOException e) { e.printStackTrace(); }
    }


    public void close() {
        try {
            os.close();
            is.close();
            mySocket.close();
        } catch (IOException e) { e.printStackTrace(); }
    }


}