package Main;

import Server.Server;
import ServerDao.ServerDao;

/**
 * Created by amirpez on 11/12/17.
 */
public class ServerMain {

    public static void main(String[] args) {
        ServerDao serverDao = new ServerDao();
        Server server = new Server();
        server.startServer();
    }
}
