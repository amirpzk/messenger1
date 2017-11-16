package Front;

import Client.MultithreadedClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

/**
 * Created by amirpez on 11/16/17.
 */
public class UiMessenger extends JFrame{

    private MultithreadedClient client;
    private java.util.List<String> list = new ArrayList<>();

    public UiMessenger(MultithreadedClient client) throws HeadlessException {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(468,780);
        this.client = client;
        this.list = client.getOnlineUsers();
        setOnlinePanels();
        this.setVisible(true);
//        this.onlinePanels = client.makeAndGeOnlinePanels();
//        setOnlinePanels();
    }

    public void setOnlinePanels(){
        for (int i=0;i<list.size();i++){
            this.add(getOnlinePanel(list.get(i)));
        }
    }

        public JPanel getOnlinePanel(String user) {

            JPanel panel = new JPanel();
            panel.setOpaque(true);
            panel.setBackground(new Color(255, 255, 255));
            panel.setLayout(new FlowLayout());
            JLabel username = new JLabel(user);
            panel.setPreferredSize(new Dimension(468,100));
            panel.add(username);
            panel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ChatUi chatUi = new ChatUi(client.getUser().getUsername(),user,client);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            return panel;
        }

}
