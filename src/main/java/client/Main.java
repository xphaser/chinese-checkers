package client;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main {
    private static final String address = "localhost";
    private static final int port = 3000;
    
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.connect(address, port);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        client.run();
    }
}
