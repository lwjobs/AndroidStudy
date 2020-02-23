package cn.edu.jssvc.smartwaterlamp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 配套课程
 * https://www.icourse163.org/course/JSSVC-1449806164
 */
public class MainActivity extends Activity {

    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final UUID MY_UUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static BluetoothSocket mmSocket;
    public static OutputStream mmOutStream;
    public static boolean CONNECT_STATUS = false;
    private BluetoothAdapter mBtAdapter;
    private TextView tv_rx;// 可以定义一个接收文本框
    private EditText et_send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_rx = findViewById(R.id.tv_rx);
        et_send = findViewById(R.id.et_send);

        Button bt_conn = findViewById(R.id.bt_conn);
        Button bt_send = findViewById(R.id.bt_send);
        Button bt_on = findViewById(R.id.bt_on);
        Button bt_off = findViewById(R.id.bt_off);
        Button bt_theme1 = findViewById(R.id.bt_theme1);
        Button bt_theme2 = findViewById(R.id.bt_theme2);

        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // 判断蓝牙是否可用
        if (mBtAdapter == null) {
            Toast.makeText(this, "蓝牙是不可用的", Toast.LENGTH_LONG).show();
//            finish();
//            return;
        }


        bt_send.setOnClickListener(new OnClickListener() {// 发送数据
            @Override
            public void onClick(View arg0) {
                String msg = et_send.getText().toString();
                if (CONNECT_STATUS) {
                    write(msg);
                } else {
                    Toast.makeText(getApplicationContext(), "请先连接蓝牙", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_on.setOnClickListener(new OnClickListener() {// 发送数据
            @Override
            public void onClick(View arg0) {
                if (CONNECT_STATUS) {
                    write("ON");
                } else {
                    Toast.makeText(getApplicationContext(), "请先连接蓝牙", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_off.setOnClickListener(new OnClickListener() {// 发送数据
            @Override
            public void onClick(View arg0) {
                if (CONNECT_STATUS) {
                    write("OFF");
                } else {
                    Toast.makeText(getApplicationContext(), "请先连接蓝牙", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_theme1.setOnClickListener(new OnClickListener() {// 发送数据
            @Override
            public void onClick(View arg0) {
                if (CONNECT_STATUS) {
                    write("THEME1");
                } else {
                    Toast.makeText(getApplicationContext(), "请先连接蓝牙", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_theme2.setOnClickListener(new OnClickListener() {// 发送数据
            @Override
            public void onClick(View arg0) {
                if (CONNECT_STATUS) {
                    write("THEME2");
                } else {
                    Toast.makeText(getApplicationContext(), "请先连接蓝牙", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_conn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                openOptionsMenu();
            }
        });

    }

    // 发送数据
    public static void write(String str) {
        if (CONNECT_STATUS) {
            byte[] buffer = str.getBytes();
            try {
                mmOutStream = mmSocket.getOutputStream();
                mmOutStream.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 连接设备
    public void ConnectThread(BluetoothDevice device) {
        try {
            mmSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
            mBtAdapter.cancelDiscovery();
            mmSocket.connect();
            Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_LONG).show();
            CONNECT_STATUS = true;
            // 接收数据进程
            ReceiveData receivethread = new ReceiveData();// 连接成功后开启接收数据服务
            receivethread.start();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "连接失败", Toast.LENGTH_SHORT)
                    .show();
            CONNECT_STATUS = false;
            try {
                mmSocket.close();
            } catch (IOException e2) {
                e.printStackTrace();
            }
        }
    }

    // 取消链接
    public void cancelconnect() {
        try {
            mmSocket.close();
            CONNECT_STATUS = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // 菜单选项点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.open:// 打开蓝牙设备
                if (!mBtAdapter.isEnabled()) {
                    Intent enableIntent = new Intent(
                            BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                } else {
                    Toast.makeText(MainActivity.this, "蓝牙已打开", Toast.LENGTH_SHORT)
                            .show();
                }
                break;

            case R.id.scan:// 扫描设备
                if (!mBtAdapter.isEnabled()) {
                    Toast.makeText(MainActivity.this, "未打开蓝牙", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Intent serverIntent = new Intent(MainActivity.this,
                            ListActivity.class);
                    startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                }
                break;

            case R.id.disconnect:// 断开连接
                if (!CONNECT_STATUS) {
                    Toast.makeText(MainActivity.this, "无连接", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "已断开连接", Toast.LENGTH_SHORT)
                            .show();
                    cancelconnect();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Intent接收器，返回结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CONNECT_DEVICE) {
            // 当DeviceListActivity返回与设备连接的消息
            if (resultCode == Activity.RESULT_OK) {
                // 得到链接设备的MAC
                String address = data.getExtras().getString(
                        ListActivity.EXTRA_DEVICE_ADDRESS, "");
                // 得到BLuetoothDevice对象
                if (!TextUtils.isEmpty(address)) {
                    BluetoothDevice device = mBtAdapter.getRemoteDevice(address);
                    ConnectThread(device);
                }
            }
        }
    }


    // 读数据线程
    private class ReceiveData extends Thread {
        InputStream mmInStream;

        private ReceiveData() {
            try {
                mmInStream = mmSocket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

            int bytes;
            byte[] buffer = new byte[256];
            while (true) {

                try { // 接收数据
                    bytes = mmInStream.read(buffer);
                    final String readStr = new String(buffer, 0, bytes);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_rx.setText(readStr);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
