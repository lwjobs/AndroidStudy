package cn.edu.jssvc.simpleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;
/**
 * @author lwjobs
 * @version 1.0
 * @data 2020/1/30
 * @see <a href="http://www.icourse163.org/course/JSSVC-1449806164">课程链接</a>
 */
public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private ImageView imageView;
    private Button button;
    private RadioGroup radioGroup;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpleview);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);
        radioGroup = findViewById(R.id.radioGroup);
        checkBox = findViewById(R.id.checkBox);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                textView.setText(str);
                imageView.setImageResource(R.drawable.ic_launcher_background);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton){
                    Toast.makeText(getApplicationContext(),"RB1被选中",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"RB2被选中",Toast.LENGTH_SHORT).show();
                }

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"CB被选中",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"CB没被选中",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
