package Server;

import Client.User;
import Front.UiSignin;
import com.sun.tools.javah.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Created by amirpez on 11/11/17.
 */
public class MultithreadedServer extends Thread{
    private Socket clientSocket;
    private Server server;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean shouldRun = true;
    private Controller controller = new Controller(server);
    private User user;

    public MultithreadedServer(Server server , Socket socket){
        super("ServerConnectionThread");
        this.clientSocket = socket;
        this.server = server;
    }
//
    public void sendStringToClient(String msg){
        try
        {
            dos.writeUTF(msg);
            dos.flush();
        }
        catch (Exception e)
        {
            System.out.println("PROBLEM <><><><> MultiThreadedServer >>>>> sentStringToClient");
        }
    }

//    public void sendStringToAllClients(String text) {
//        for(int index = 0; index < connections.size(); index++) {
//            MultithreadedServer serverThread = connections.get(index);
//            serverThread.sendStringToClient(text);
//        }
//    }

    public void run() {
        try {
            dos = new DataOutputStream(clientSocket.getOutputStream());
            dis = new DataInputStream(clientSocket.getInputStream());

            sendStringToClient(allUsersProtocol());

            while(shouldRun) {
                while(dis.available() == 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

//                String textIn = dis.readUTF();
//                sendStringToAllClients(textIn);
                getFromClient();
            }


//            dis.close();
//            dos.close();
//            clientSocket.close();

        }
        catch (IOException e)
        {
            System.out.println("ERROR <><><><><>< MultiThreadedServer >>> run()");
            e.printStackTrace();
            System.exit(0);
        }
    }

//    public void sendOnlineUsersToClient() {
//        String onlineUsersProtocol = server.sendOnlineUsersToServerThread();
//        sendStringToClient(onlineUsersProtocol);
//
//    }

    public void getFromClient() {
                    try
                    {
                    System.out.println("AAaaa");
                    controller.identifiear(dis.readUTF(),server);
                    System.out.println("bbbbb");
                    }
                    catch (IOException e)
                    {
                        System.out.println("Problem , MultiThreadedServer >>> getFromClient");
                        e.printStackTrace();
                    }
            }

    public String allUsersProtocol(){
        List<String> users = server.getServerDao().getListOfFile();
        String usersProt = "users" ;
        for ( int i=0 ; i<users.size() ; i++){
            usersProt += "#"+ users.get(i);
        }
        System.out.println("Pass it to Client >> "+usersProt );
        return usersProt;
    }
}

