mport java.io.BufferedReader;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class MyServer implements Observable {
    public final static int PORT = 8290;
    private volatile List<Observer> clients = new ArrayList<>();

    public void start() {
        System.out.println("==START SERVER==");
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = null;
            while (true) {
                if (socket == null) {
                    socket = serverSocket.accept();
                } else {
                    ClientEntity clientEntity = new ClientEntity(socket, this);
                    new Thread(clientEntity).start();
                    socket = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addObserver(Observer o) {
        clients.add(o);
    }

    @Override
    public void stopObserver(Observer o) {
        /**/
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer client : clients) {
            client.notifyObserver(message);
        }
    }

    public void addListener(InvalidationListener invalidationListener) {

    }

    public void removeListener(InvalidationListener invalidationListener) {

    }
}