package cn.edu.jssvc.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.e("Lifecycle","Main2Activity:onCreate被调用");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Lifecycle","Main2Activity:onStart被调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Lifecycle","Main2Activity:onResume被调用");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Lifecycle","Main2Activity:onPause被调用");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Lifecycle","Main2Activity:onStop被调用");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Lifecycle","Main2Activity:onDestroy被调用");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("Lifecycle","Main2Activity:onRestart被调用");
    }
}
