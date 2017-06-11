package ru.zaochno.zaochno.testing.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.model.testing.Test;
import ru.zaochno.zaochno.testing.TestingDetailsFragment;

/**
 * Created by notbl on 5/21/2017.
 */

public class TestingsViewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    private TestingDetailsFragment mTestingDetailsFragment;
    private List<Test> mTestList;

    public TestingsViewAdapter(Fragment fragment, List<Test> testList){
        mInflater = (LayoutInflater) fragment.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mTestList=testList;
        mTestingDetailsFragment= (TestingDetailsFragment) fragment;
    }

    public TestingsViewAdapter(Fragment fragment){
        this(fragment,new ArrayList<Test>());
    }

    //todo do this adapter
    @Override
    public int getCount() {
        return mTestList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTestList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView==null){
            convertView=mInflater.inflate(R.layout.testing_list_view_item,parent,false);
            holder=new ViewHolder();

            holder.title=(TextView)convertView.findViewById(R.id.testingListViewItemTitleId);
            holder.shortDescription=(TextView)convertView.findViewById(R.id.testingListViewItemDescriptionTextId);
            holder.imageView=(ImageView)convertView.findViewById(R.id.testingListViewItemImageId);
            holder.expire=(TextView)convertView.findViewById(R.id.testingListViewItemExpireTextViewId);
            holder.progressTextView=(TextView)convertView.findViewById(R.id.testingListViewItemProgressTextViewId);
            holder.progressBar=(ProgressBar)convertView.findViewById(R.id.testingListViewItemprogressBarId);
            holder.continueButton=(Button)convertView.findViewById(R.id.testingListViewItemContinueButtonId);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        if(mTestList.get(position).getTestProgress()>0){
            holder.progressBar.setVisibility(View.VISIBLE);
            holder.progressTextView.setVisibility(View.VISIBLE);
        }
        holder.title.setText(mTestList.get(position).getTestName());
        holder.shortDescription.setText(mTestList.get(position).getShortDescription());
        holder.expire.setText(mTestList.get(position).getExpireDate().toString());
        //todo add imageView here
//        holder.imageView
        holder.progressTextView.setText(String.valueOf(mTestList.get(position).getTestProgress()));
        holder.progressBar.setProgress(mTestList.get(position).getTestProgress());
        holder.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo implement listener
                mTestingDetailsFragment.showTest(mTestList.get(position));
            }
        });
        return convertView;
    }

    static class ViewHolder{
        private TextView title;
        private ImageView imageView;
        private TextView shortDescription;
        private TextView expire;
        private Button continueButton;
        private TextView progressTextView;
        private ProgressBar progressBar;
    }
}
