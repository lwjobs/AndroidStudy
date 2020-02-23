package cn.edu.jssvc.runonuithread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int progress = 0;
    private TextView textView;
    private ProgressBar progressBar;

    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar4);

        button2 = findViewById(R.id.button2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    progress++;

                    if(progress>100){
                        progress = 0;
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //UI控件更新
                            textView.setText(progress + "%");
                            progressBar.setProgress(progress);
                        }
                    });
                    try{
                        //为了让您看到进度滚动效果，放慢进度上升的速度
                        Thread.sleep(10);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"测试中",Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("HandleMessage",Thread.currentThread().getName());
    }
}
