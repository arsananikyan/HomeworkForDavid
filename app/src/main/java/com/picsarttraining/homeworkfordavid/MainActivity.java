package com.picsarttraining.homeworkfordavid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NavigationAdapter navigationAdapter;
    private ListView navigationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationListView = (ListView) findViewById(R.id.navigation_list_view);
        ArrayList<String> items = new ArrayList<>();
        items.add("Notifications");
        items.add("Swipe to refresh");
        navigationAdapter = new NavigationAdapter(this, items);
        navigationListView.setAdapter(navigationAdapter);
        navigationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                navigateTo(navigationAdapter.getItem(position));
            }
        });
    }

    private void navigateTo(String menuItemName)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch (menuItemName)
        {
            case "Notifications":
                intent.setData(Uri.parse("david_homework://notification"));
                break;
            case "Swipe to refresh":
                intent.setData(Uri.parse("david_homework://swipe_refresh"));
                break;
        }
        startActivity(intent);
    }
}
