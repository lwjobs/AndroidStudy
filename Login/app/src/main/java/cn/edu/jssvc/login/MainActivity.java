package cn.edu.jssvc.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_password;

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        String name = sharedPreferences.getString("name", "");
        String password = sharedPreferences.getString("password", "");
        boolean isCh = sharedPreferences.getBoolean("Check", false);

        et_name = findViewById(R.id.editText1);
        et_password = findViewById(R.id.editText2);
        checkBox = findViewById(R.id.checkBox);

        et_name.setText(name);
        et_password.setText(password);
        checkBox.setChecked(isCh);


        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "用户名或密码为空", Toast.LENGTH_SHORT).show();
                } else if (name.equals("abc") && password.equals("123")) {
                    if (checkBox.isChecked()) {
                        editor.putString("name", name);
                        editor.putString("password", password);
                        editor.commit();
                    }

                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("password", password);
                    startActivity(intent);
                } else if (name.equals("edf") && password.equals("456")) {
                    if (checkBox.isChecked()) {
                        editor.putString("name", name);
                        editor.putString("password", password);
                        editor.commit();
                    }

                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("password", password);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
////                    Toast.makeText(getApplicationContext(),"需要存密码",Toast.LENGTH_SHORT).show();
////                    String name = et_name.getText().toString().trim();
////                    String password = et_password.getText().toString().trim();
////                    editor.putString("name",name);
////                    editor.putString("password",password);
////                    editor.commit();
//                }
                editor.putBoolean("Check", isChecked);
                editor.commit();
            }
        });
    }
}
