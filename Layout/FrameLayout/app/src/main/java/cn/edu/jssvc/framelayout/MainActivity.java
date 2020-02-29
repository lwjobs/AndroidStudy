package cn.edu.jssvc.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView1,imageView2;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fl);

        button = (Button)findViewById(R.id.button);
        imageView1 = (ImageView)findViewById(R.id.imageView);
        imageView2 = (ImageView)findViewById(R.id.imageView2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count%2==0){
                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.INVISIBLE);
                }else {
                    imageView1.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                }
                count++;
            }
        });
    }
}
