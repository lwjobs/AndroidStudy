package cn.edu.jssvc.handlemessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private int progress = 0;
    private TextView textView;
    private ProgressBar progressBar;

    private Button button2;

    private Thread thread;

    private Handler handler = new Handler(){
        //消息处理方法
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            textView.setText(msg.arg1 + "%");
            progressBar.setProgress(msg.arg1);
            return ;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar4);

        button2 = findViewById(R.id.button2);



        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    //拿到主线程Handler的Message
                    Message msg = handler.obtainMessage();
                    //将进度值作为消息的参数包装进去，进度自加1
                    msg.arg1 = progress ++;
                    //将消息发送给主线程的Handler
                    handler.sendMessage(msg);
                    //这个例子是反复循环，实际项目中可能会进行页面跳转或其他处理
                    if(progress>100){
                        progress = 0;
                    }
                    try{
                        //为了让您看到进度滚动效果，放慢进度上升的速度
                        Thread.sleep(10);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });



        thread.start();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"测试中",Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("HandleMessage",Thread.currentThread().getName());


    }
}
