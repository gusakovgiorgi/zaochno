package ru.zaochno.zaochno.trainings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ru.zaochno.zaochno.FakeData;
import ru.zaochno.zaochno.R;

public class DefaultTreningsFragment extends Fragment {


    private ListView mListview;

    private OnTrainingDetailsItemClickedListener mListener;

    public DefaultTreningsFragment() {
        // Required empty public constructor
    }


    public static DefaultTreningsFragment newInstance() {
        DefaultTreningsFragment fragment = new DefaultTreningsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_default_trenings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListview=(ListView)view.findViewById(R.id.defaultTreningListViewId);
        mListview.setAdapter(new TreningsListViewAdapter(getContext(),FakeData.getDefaultTrenings()));
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onTraining(position);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onTraining(int position) {
        if (mListener != null) {
            mListener.onTrainingClicked((String) mListview.getAdapter().getItem(position));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTrainingDetailsItemClickedListener) {
            mListener = (OnTrainingDetailsItemClickedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTrainingDetailsItemClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    class TreningsListViewAdapter extends BaseAdapter{

        private List<String> data;
        private LayoutInflater mInflater;

        public TreningsListViewAdapter(Context ctx,List<String> data){
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
            View v=mInflater.inflate(R.layout.list_view_item,parent,false);
            TextView text= (TextView) v.findViewById(R.id.list_item_shosrt_description_id);
            text.setText(data.get(position));
            return v;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnTrainingDetailsItemClickedListener {
        // TODO: change this to model
        void onTrainingClicked(String data);
    }


}
