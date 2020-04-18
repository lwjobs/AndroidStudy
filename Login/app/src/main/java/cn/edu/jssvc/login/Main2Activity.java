package cn.edu.jssvc.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int count = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final TextView textView = findViewById(R.id.textView3);

        progressBar = findViewById(R.id.progressBar2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    count++;
                    progressBar.setProgress(count);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count > 100) {
//                        textView.setText(name + ":" + password);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = getIntent();
                                String name = intent.getStringExtra("name");
                                String password = intent.getStringExtra("password");
                                textView.setText(name + ":" + password);
                            }
                        });
                        break;
                    }
                }
            }
        }).start();
    }
}
