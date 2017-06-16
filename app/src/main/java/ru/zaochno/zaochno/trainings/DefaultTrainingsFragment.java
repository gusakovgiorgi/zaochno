package ru.zaochno.zaochno.trainings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ru.zaochno.zaochno.FakeData;
import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.database.DatabaseCallBack;
import ru.zaochno.zaochno.database.DatabaseManager;
import ru.zaochno.zaochno.model.Category;
import ru.zaochno.zaochno.model.Training;
import ru.zaochno.zaochno.model.TrainingUtils;
import ru.zaochno.zaochno.model.TrainingsType;
import ru.zaochno.zaochno.trainings.adapter.TrainingsListViewAdapter;

public class DefaultTrainingsFragment extends Fragment {


    private static final String ARG_PARAM1 ="param1" ;
    private TrainingsType mTrainingCategory;
    private ListView mListview;
    private TrainingsListViewAdapter mAdapter;

    private OnDefaultTrainingsFragmentCallBack mListener;

    public DefaultTrainingsFragment() {
        // Required empty public constructor
    }


    public static DefaultTrainingsFragment newInstance(TrainingsType category) {
        DefaultTrainingsFragment fragment = new DefaultTrainingsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTrainingCategory = (TrainingsType) getArguments().getSerializable(ARG_PARAM1);
        }
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
        mAdapter=new TrainingsListViewAdapter(this);

        DatabaseManager.getInstance().getTrainings(new DatabaseCallBack<Training[]>() {
            @Override
            public void returnData(Training[] data) {
                if(mTrainingCategory== TrainingsType.ALL){
                    mAdapter.setData(data);
                }else if(mTrainingCategory== TrainingsType.FAVORITES){
                    mAdapter.setData(TrainingUtils.getFavoriteTraining(data));
                }else if(mTrainingCategory== TrainingsType.BOUGHT){
                    mAdapter.setData(TrainingUtils.getBoughtTraining(data));
                }

                mListview.setAdapter(mAdapter);
            }
        });




    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDefaultTrainingsFragmentCallBack) {
            mListener = (OnDefaultTrainingsFragmentCallBack) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDefaultTrainingsFragmentCallBack");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnDefaultTrainingsFragmentCallBack {
        // TODO: change this to model
        void onDefaultTrainingsFragmentTrainingClicked(Training training);
        void OnDefaultTrainingsFragmentDemoButtonClicked(int trainingId);
        void onDefaultTrainingsFragmentBuyTrainingClicked(Training training);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onTrainingItemClicked(int position) {
        if (mListener != null) {
            mListener.onDefaultTrainingsFragmentTrainingClicked((Training)mListview.getAdapter().getItem(position));
        }
    }

    public void demoButtonClicked(int trainingId){
        if(mListener!=null){
            mListener.OnDefaultTrainingsFragmentDemoButtonClicked(trainingId);
        }
    }
    public void buyTraining(Training training) {
        if (mListener != null) {
            mListener.onDefaultTrainingsFragmentBuyTrainingClicked(training);
        }
    }


}
