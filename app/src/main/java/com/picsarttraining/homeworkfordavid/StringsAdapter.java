package com.picsarttraining.homeworkfordavid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arsen on 20.03.2016.
 */
public class StringsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> strings;
    private LayoutInflater inflater;

    public StringsAdapter(Context context) {
        this.context = context;
        this.strings = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings.clear();
        this.strings = new ArrayList<>(strings);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String string = strings.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_nav_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();
        holder.text.setText(string);
        return convertView;
    }

    private class ViewHolder {
        private TextView text;

        public ViewHolder(View convertView) {
            text = (TextView) convertView.findViewById(R.id.item_text);
        }
    }
}
