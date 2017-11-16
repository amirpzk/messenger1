package Client;

import javafx.scene.control.*;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

/**
 * Created by amirpez on 11/11/17.
 */
public class MultithreadedClient extends Thread {
    private Socket clientSocket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Controller controller;
    private User user;
    private boolean shouldRun = true;
    private boolean loginAccessBoolean;
    private ArrayList<String> onlineUsers;
    private ArrayList<String> userRecievedMesseges;
    private String pm = null ;

//    private ClientGUI gui;

    public MultithreadedClient(Socket clientSocket, Client client) {
        this.clientSocket = clientSocket;
        this.controller = new Controller(this);
        this.onlineUsers = new ArrayList<>();
        this.userRecievedMesseges = new ArrayList<>();
    }

    public String getPm() {
        return pm;
    }

    public boolean isLoginAccessBoolean() {
        return loginAccessBoolean;
    }

    public void setLoginAccessBoolean(boolean loginAccessBoolean) {
        this.loginAccessBoolean = loginAccessBoolean;
    }

    @Override
    public void run() {
        try {

            dos = new DataOutputStream(clientSocket.getOutputStream());
            dis = new DataInputStream(clientSocket.getInputStream());

            while (shouldRun) {
                try {
                    while (dis.available() == 0) {
                        try {
                            Thread.sleep(1);
                        } catch (Exception e) {
                            System.out.println("Problem1 , Class MultiThreadedCient , Method >> run() ");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Problem2 , Class MultiThreadedCient , Method >> run() ");
                }
                controller.identifier(getFromServer());
            }
//            String reply = dis.readUTF();
            controller.identifier(getFromServer());
//            System.out.println("THIS IS MESSEGE " + reply);

        } catch (Exception e) {
            System.out.println("Problem3 , Class MultiThreadedCient , Method >> run() ");
        }
    }

    public String getFromServer() {
        try {
            return dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendStringToServer(String text) {
        try {
            dos.writeUTF(text);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public boolean getLoginAccesFromServer(){
//        boolean isTrue = false ;
//        while (true){
//            try {
//                System.out.println("before begayi");
//
//                System.out.println("after begayi");
////                if ( iisTrue.equalsIgnoreCase("true")){
////                    isTrue = true;
////                }
//                return isTrue ;
//            } catch (IOException e) {
//                System.out.println("CHANCHI IS HERE");
//                e.printStackTrace();
//            }
//        }
//    }

    public void setOnlineUsers(String str) {
        this.onlineUsers.add(str);
        System.out.println(onlineUsers.size() + "size");
    }

    public java.util.List<String> getOnlineUsers() {
        return onlineUsers;
    }

//    public JPanel getOnlinePanel(String user){
//        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JLabel username = new JLabel(user);
//        panel.setSize(468,100);
//        panel.add(username);
//        return panel;
//    }
//
//    public ArrayList<JPanel> makeAndGeOnlinePanels(){
//        ArrayList<JPanel> panels = new ArrayList<>();
//
//        for (int i=0;i<onlineUsers.size();i++){
//           panels.add(getOnlinePanel(this.onlineUsers.get(i)));
//        }
//        return panels;
//    }

    protected void close() {
        try {
            dis.close();
            dos.close();
//            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void makeUser(String username, String user) {
        User user1 = new User(username, user);
        this.user = user1;
    }

    public User getUser() {
        return user;
    }


    public void pmHandler(String str1 , String str2){
        this.pm = str1 + " : " + str2 ;
    }

    public String pmHandlerChat(String pm, String from) {
        String messege = from + " : " + pm;
        userRecievedMesseges.add(messege);
        return messege;
    }

//    public String sendPmToUi() {
//        if (userRecievedMesseges.get(userRecievedMesseges.size())==null){
//
//        }
//        try {
//            return userRecievedMesseges.get(userRecievedMesseges.size());
//        } catch (Exception e) {
//            System.out.println("RAD RAD MDBONZA");
//            return null;
//        }
//    }
}


