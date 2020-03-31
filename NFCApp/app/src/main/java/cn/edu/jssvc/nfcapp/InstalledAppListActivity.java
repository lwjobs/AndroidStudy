package cn.edu.jssvc.nfcapp;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class InstalledAppListActivity extends ListActivity implements
        AdapterView.OnItemClickListener {
    private List<String> mPackages = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PackageManager packageManager = getPackageManager();
        List<PackageInfo> packageInfos = packageManager
                .getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (PackageInfo packageInfo : packageInfos) {
            mPackages.add(packageInfo.applicationInfo.loadLabel(packageManager)
                    + "\n" + packageInfo.packageName);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                mPackages);
        setListAdapter(arrayAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Intent intent = new Intent();
        intent.putExtra("package_name", mPackages.get(position));
        setResult(1, intent);
        finish();
    }

}
