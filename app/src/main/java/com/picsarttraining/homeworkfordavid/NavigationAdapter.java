package com.picsarttraining.homeworkfordavid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Arsen on 20.03.2016.
 */
public class NavigationAdapter extends ArrayAdapter<String> {
    public NavigationAdapter(Context context, ArrayList<String> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_nav_item, parent, false);
        }
        ((TextView)convertView.findViewById(R.id.item_text)).setText(item);
        return convertView;
    }
}
