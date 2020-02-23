package cn.edu.jssvc.viewpagertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> views;
    private MyNewAdapter myNewAdapter;
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

        myNewAdapter = new MyNewAdapter(views);
        viewPager.setAdapter(myNewAdapter);

    }
}
