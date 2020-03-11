package cn.edu.jssvc.viewpagertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> views;
    private MyNewAdapter myNewAdapter;

    private Button button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        views = new ArrayList<View>();
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());

        View view1 = layoutInflater.inflate(R.layout.a1,null);
        View view2 = layoutInflater.inflate(R.layout.a2,null);
        View view3 = layoutInflater.inflate(R.layout.a3,null);
        views.add(view1);
        views.add(view2);
        views.add(view3);

        button1 = view1.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"来自页面1",Toast.LENGTH_SHORT).show();
            }
        });

        button2 = view2.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"来自页面2",Toast.LENGTH_SHORT).show();
            }
        });

        button3 = view3.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"来自页面3",Toast.LENGTH_SHORT).show();
            }
        });


        myNewAdapter = new MyNewAdapter(views);
        viewPager.setAdapter(myNewAdapter);

    }
}
