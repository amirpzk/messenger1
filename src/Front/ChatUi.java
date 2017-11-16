package Front;

import Client.MultithreadedClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by amirpez on 11/16/17.
 */
public class ChatUi  extends JFrame {
    private JPanel panel1;
    private JTextArea textArea1;
    private JLabel userName;
    private JTextField textField1;
    private JButton sendButton;
    private MultithreadedClient client;

    public ChatUi(String myName, String yourName, MultithreadedClient client) {
        this.client = client;
        this.setContentPane(panel1);
        this.setSize(500, 500);
        this.setLocationByPlatform(true);
        textArea1.setEditable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        userName.setText(yourName);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pmProtocol = "pm#" + myName + "#" + yourName + "#" + textField1.getText();
                client.sendStringToServer(pmProtocol);
            }
        });


        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                if ( client.getPm()!=null) {
                    System.out.println(client.getPm());
                    textArea1.setText(client.getPm());

                }else {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                }
            }
        }; thread.start();
        this.setVisible(true);
    }

}

