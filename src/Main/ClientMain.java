package Main;

import Client.Client;
import Client.MultithreadedClient;
import Front.UISignUp;

/**
 * Created by amirpez on 11/12/17.
 */
public class ClientMain {

    public static void main(String[] args) {
        Client client = new Client() ;
        MultithreadedClient c = client.getClientThread();
        UISignUp uiSignUp = new UISignUp(client);
    }
}
