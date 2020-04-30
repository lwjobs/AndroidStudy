package jssvc.edu.cn.recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("zhangsan","15512345678",R.drawable.ic_android_black_24dp);
        Person person2 = new Person("lisi","18812345678",R.drawable.ic_directions_run_black_24dp);
        personList.add(person1);
        personList.add(person2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        MyAdapter myAdapter = new MyAdapter(personList);

        recyclerView.setAdapter(myAdapter);
    }
}
