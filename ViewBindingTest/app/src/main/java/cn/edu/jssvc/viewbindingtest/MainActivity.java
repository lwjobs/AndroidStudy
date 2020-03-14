package cn.edu.jssvc.viewbindingtest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.jssvc.viewbindingtest.databinding.ActivityMainBinding;

/**
 * @author lwjobs
 * @配套课程 https://www.icourse163.org/course/JSSVC-1449806164
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        LayoutInflater layoutInflater = LayoutInflater.from(this);
//        ActivityMainBinding binding = ActivityMainBinding.inflate(layoutInflater);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.text.setText("文字已变化");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button被点击", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
