package cn.edu.jssvc.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView1,listView2;

    private String[] names = new String[]{"李白","杜甫"};
    private String[] nicknames = new String[]{"诗仙","诗圣"};
    private int[] heads = new int[]{R.drawable.libai,R.drawable.dufu};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView1 = findViewById(R.id.list_array);
        listView2 = findViewById(R.id.list_simple);

        final String[] arr = new String[]{"Java","Python","C++"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.item_text,arr);
        listView1.setAdapter(arrayAdapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),arr[position],Toast.LENGTH_SHORT).show();
            }
        });

        List <Map<String,Object>>list = new ArrayList();
        for (int i=0;i<names.length;i++){
            Map<String,Object> item = new HashMap<>();
            item.put("name",names[i]);
            item.put("nickname",nicknames[i]);
            item.put("head",heads[i]);
            list.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                list,
                R.layout.item_simple,
                new String[]{"head","name","nickname"},
                new int[]{R.id.imageView,R.id.textView, R.id.textView2});

        listView2.setAdapter(simpleAdapter);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),names[position],Toast.LENGTH_SHORT).show();
            }
        });


    }
}
