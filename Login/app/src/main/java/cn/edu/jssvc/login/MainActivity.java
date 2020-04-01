package cn.edu.jssvc.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText,editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        Button button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString().trim();
                String password = editText2.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"用户名或密码为空",Toast.LENGTH_SHORT).show();
                } else if (name.equals("abc")&&password.equals("123")){
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("password",password);
                    startActivity(intent);
                } else if (name.equals("def")&&password.equals("456")){
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("password",password);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
