package clientSever;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import main.GamePanel;

public class Client implements Runnable, ClientServer {
    Data data;
    Socket socket;
    Thread clientThread;
    GamePanel gamePanel;

    public Client(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        data = new Data(gamePanel);
    }
    
    public void startThread() {
        clientThread = new Thread(this);
        clientThread.start();
    }

    public void run() {
        while (clientThread != null) {
            recive();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            send();
        }
    }

    public void connect() {
        try {
            socket = new Socket("localhost", 7777);
            System.out.println("Connected!");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
            System.out.println("Closing socket and terminating program.");
            socket.close();
            clientThread = null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}