package cn.edu.jssvc.helloandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("MainActivity","V冗余信息");
        Log.i("MainActivity","I普通信息");
        Log.d("MainActivity","D调试信息");
        Log.w("MainActivity","W警告信息");
        Log.e("MainActivity","E错误信息");
    }
}
