package cn.edu.jssvc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(1234);

            while (true) {
                Socket socket = serverSocket.accept();
                list.add(socket);
                new ReverseDemo(socket, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
