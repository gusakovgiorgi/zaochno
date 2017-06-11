package ru.zaochno.zaochno.drawer;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.testing.adapter.TestingsViewAdapter;

/**
 * Created by notbl on 6/11/2017.
 */

public class DrawerListvViewAdapter extends BaseAdapter {
    private List<DrawerListViewItem> data;

    public DrawerListvViewAdapter() {
    }

    public void setData(List<DrawerListViewItem> data) {
        this.data = data;
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
        ViewHolder holder;

        if(convertView==null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_listview_item, parent, false);
            holder = new ViewHolder();

            holder.ico= (ImageView) convertView.findViewById(R.id.drawerListViewIconId);
            holder.title=(TextView)convertView.findViewById(R.id.drawerListViewTextId);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.ico.setImageDrawable(data.get(position).getImageView());
        holder.title.setText(data.get(position).getTitle());

        return convertView;
    }

    static class ViewHolder{
        private TextView title;
        private ImageView ico;
    }
}
