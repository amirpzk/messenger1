package Client;

import java.net.Socket;

/**
 * Created by amirpez on 11/11/17.
 */
public class Client implements ImpClient {
    Socket clientSocket;
    public MultithreadedClient clientThread;

    @Override
    public void startClient() {
        try
        {
            this.clientSocket = new Socket("127.0.0.1",7001);
            this.clientThread = new MultithreadedClient(clientSocket,this);
            clientThread.start();

        }
        catch (Exception e)
        {

        }
    }

    public MultithreadedClient getClientThread(){
        return clientThread;
    }

    private void listenForInput(String input){
        while (true)
        {
            clientThread.sendStringToServer(input);
        }
//        clientThread.close();
    }

    @Override
    public void sendToServer(String text) {
        clientThread.sendStringToServer(text);
    }

    public void closeStreams() {
        clientThread.close();
    }

}
