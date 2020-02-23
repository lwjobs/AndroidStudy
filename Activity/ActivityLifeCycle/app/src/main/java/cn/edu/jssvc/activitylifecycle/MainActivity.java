package cn.edu.jssvc.activitylifecycle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Lifecycle","MainActivity:onCreate被调用");

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
//                startActivity(intent);
            AlertDialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this).setTitle("dd");
            dialog =builder.create();
            dialog.show();
            }
        });

        Thread.currentThread().getName();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle","MainActivity:onStart被调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle","MainActivity:onResume被调用");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle","MainActivity:onPause被调用");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle","MainActivity:onStop被调用");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle","MainActivity:onDestroy被调用");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Lifecycle","MainActivity:onRestart被调用");
    }
}
