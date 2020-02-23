package cn.edu.jssvc.activitydatatransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            //使用putExtra传递数据
            intent.putExtra("name","zhangsan");
            intent.putExtra("score",95);

            //使用Bundle传递数据
//            Bundle bundle = new Bundle();
//            bundle.putString("name","zhangsan");
//            bundle.putInt("score",95);
//            intent.putExtras(bundle);

            startActivity(intent);
            }
        });
    }
}
