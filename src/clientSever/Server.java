package clientSever;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import main.GamePanel;

public class Server implements Runnable, ClientServer {
    Data data;
    ServerSocket ss;
    Socket socket;
    Thread serverThread;
    GamePanel gamePanel;
    
    public Server(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        data = new Data(gamePanel);
    }
    
    public void startThread() {
        serverThread = new Thread(this);
        serverThread.start();
    }

    public void run() {
        while (serverThread != null) {
            send();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            recive();
        }
    }


    public void connect() {
        try {
            ss = new ServerSocket(7777);
            System.out.println("ServerSocket awaiting connections...");
            socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
            System.out.println("Connection from " + socket + "!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void recive() {
        InputStream inputStream;
        try {
            inputStream = socket.getInputStream();
            // create a DataInputStream so we can read data from it.
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Data recivedData;
            try {
                recivedData = (Data) (objectInputStream.readObject());
                recivedData.download(gamePanel);
            } catch (ClassNotFoundException e) {
                end();
            }

        } catch (IOException e) {
            end();
        }
    }

    public void send() {
        OutputStream outputStream;
        try {
            outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an
            // object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            data.upload();
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
        }
    }

    public void end() {
        try {
            System.out.println("Closing sockets.");
            ss.close();
            socket.close();
            serverThread = null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}