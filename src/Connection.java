import java.io.*;
import java.net.Socket;

public class Connection implements Runnable {
    private Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            String msg=is.readLine();
            while(msg != null) {
                // receive the it twice, and then calculate the result
                int a=Integer.parseInt(msg);
                int b=Integer.parseInt(is.readLine());
                int result=a*b;
                String echo = "SERVER ECHO "+is.readLine()+" :  " + result;
                os.writeBytes(echo+"\n");
                msg = is.readLine();
            }

            os.close();
            is.close();
            socket.close();

        } catch (IOException e) { e.printStackTrace(); }
    }
}