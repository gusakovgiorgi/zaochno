package ru.zaochno.zaochno.trainings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.zaochno.zaochno.R;

/**
 * Created by notbl on 5/28/2017.
 */

public class ProgressListViewAdapter extends BaseAdapter {

    private List<String> data;
    private LayoutInflater mInflater;

    public ProgressListViewAdapter(Context ctx, List<String> data){
        this.data=data;
        mInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=mInflater.inflate(R.layout.progress_list_view_item,parent,false);
        TextView text= (TextView) v.findViewById(R.id.progressListViewItemHeaderId);
        text.setText(data.get(position));
        TextView progressText=(TextView)v.findViewById(R.id.progressListViewItemProgressTextId);
        return v;
    }
}