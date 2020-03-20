package cn.edu.jssvc.smarthome;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private EditText et_send;
    private TextView tv_recv;
    private EditText et_ip;
    private EditText et_port;

    private ConnectThread connectThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_connect = findViewById(R.id.bt_conn);
        Button bt_disconnect = findViewById(R.id.bt_disconn);
        Button bt_on = findViewById(R.id.bt_on);
        Button bt_off = findViewById(R.id.bt_off);
        Button bt_send = findViewById(R.id.bt_send);

        et_send = findViewById(R.id.et_send);
        tv_recv = findViewById(R.id.tv_recv);
        et_ip = findViewById(R.id.et_ip);
        et_port = findViewById(R.id.et_port);

        bt_connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                connectThread = new ConnectThread();
                connectThread.start();
            }
        });

        bt_disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    connectThread.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }    //关闭连接
                connectThread.socket = null;
            }
        });

        bt_on.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //发送数据
                if (connectThread.socket != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                connectThread.outputStream.write("6".getBytes());
                                Log.e("已发送", "已发送");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });

        bt_off.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //发送数据
                if (connectThread.socket != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                connectThread.outputStream.write("7".getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });

        bt_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //发送数据
                if (connectThread.socket != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                connectThread.outputStream.write(et_send.getText().toString().getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
    }

    class ConnectThread extends Thread {
        Socket socket = null;        //定义socket
        OutputStream outputStream = null;    //定义输出流（发送）
        InputStream inputStream = null;    //定义输入流（接收）

        public void run() {
            try {
                //用InetAddress方法获取ip地址
                InetAddress ipAddress = InetAddress.getByName(et_ip.getText().toString());
                int port = Integer.valueOf(et_port.getText().toString());        //获取端口号
                socket = new Socket(ipAddress, port);
                Log.e("Socket", socket.toString());
                //连接失败
                if (null == socket) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "连接失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
                //获取输出流
                outputStream = socket.getOutputStream();
                //接收数据
                while (true) {
                    final byte[] buffer = new byte[1024];//创建接收缓冲区
                    inputStream = socket.getInputStream();
                    final int len = inputStream.read(buffer);//数据读出来，并且返回数据的长度
                    if (len != 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_recv.append(new String(buffer, 0, len) + "\r\n");
                            }
                        });
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
