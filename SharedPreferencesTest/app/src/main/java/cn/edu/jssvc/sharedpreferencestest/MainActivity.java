package cn.edu.jssvc.sharedpreferencestest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private EditText write_key;
    private EditText write_value;
    private EditText read_key;
    private TextView read_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取SharedPreferences对象及编辑器
        sharedPreferences = getSharedPreferences("mysp", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Button bt_write = findViewById(R.id.button);
        Button bt_read = findViewById(R.id.button2);

        write_key = findViewById(R.id.editText_key);
        write_value = findViewById(R.id.editText_value);

        read_key = findViewById(R.id.editText_rkey);
        read_value = findViewById(R.id.textView);

        bt_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String writeKey = write_key.getText().toString().trim();
                String writeValue = write_value.getText().toString().trim();

                if (TextUtils.isEmpty(writeKey) || TextUtils.isEmpty(writeValue)) {
                    Toast.makeText(getApplicationContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //SharedPreferences 写入
                    editor.putString(writeKey, writeValue);
                    editor.apply();
                }
            }
        });

        bt_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String readKey = read_key.getText().toString().trim();

                if (TextUtils.isEmpty(readKey)) {
                    Toast.makeText(getApplicationContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //SharedPreferences 读出
                    String readValue = sharedPreferences.getString(readKey, null);
                    if (readValue == null) {
                        Toast.makeText(getApplicationContext(), "您还未写入该数据", Toast.LENGTH_SHORT).show();
                    } else {
                        read_value.setText(readValue);
                    }
                }
            }
        });
    }
}
