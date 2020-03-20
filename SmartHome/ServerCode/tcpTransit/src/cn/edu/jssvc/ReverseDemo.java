package cn.edu.jssvc;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ReverseDemo extends Thread {
    ArrayList list;
    Socket socket;

    public ReverseDemo(Socket socket, ArrayList list) {
        this.list = list;
        this.socket = socket;
        this.start();
    }

    @Override
    public void run() {
        try {
            InputStream inStream = socket.getInputStream();
            OutputStream outStream = socket.getOutputStream();

            PrintWriter out = new PrintWriter(outStream, true);
            System.out.println(socket.getInetAddress().getHostAddress() + " connected!!!");
            out.println("连接成功！");

            byte[] buf = new byte[1024];

            while (true) {
                int bytes_read = inStream.read(buf);
                if (bytes_read < 0) {
                    break;
                } else {
                    broadcast(buf, 0, bytes_read);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            list.remove(socket);
        }
    }


    void broadcast(byte[] b, int offset, int length) {
        for (int i = 0, n = list.size(); i < n; i++) {
            Socket sock = (Socket) list.get(i);
            if (sock != socket) {
                try {
                    sock.getOutputStream().write(b, offset, length);
                    sock.getOutputStream().flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

