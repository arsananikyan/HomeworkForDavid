package com.picsarttraining.homeworkfordavid;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SwipeRefreshActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private StringsAdapter textsAdapter;
    private ListView textListView;
    private ArrayList<String> texts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swpipe_to_refresh);
        textListView = (ListView) findViewById(R.id.text_list_view);
        texts = new ArrayList<>();
        texts.add("Text 1");
        texts.add("Text 2");
        texts.add("Text 3");
        texts.add("Text 4");
        startImitateNewItems();
        textsAdapter = new StringsAdapter(this);
        textsAdapter.setStrings(texts);
        textListView.setAdapter(textsAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread(new Runnable() {
                    @Override
                    synchronized public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(texts.size()!=textsAdapter.getCount())
                                {
                                    textsAdapter.setStrings(texts);
                                }
                            }
                        });


                        try {
                            wait(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void startImitateNewItems() {
        Thread newItemsThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=5;i<30;i++)
                {
                    texts.add("Text " + i);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        newItemsThread.start();
    }
}
