package ru.zaochno.zaochno.trainings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.zaochno.zaochno.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnDetailsTrainingsFragmentCallback} interface
 * to handle interaction events.
 * Use the {@link DetailsTrainingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsTrainingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";



    private String data;

    private OnDetailsTrainingsFragmentCallback mListener;

    public DetailsTrainingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DetailsTrainingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsTrainingFragment newInstance(String param1) {
        DetailsTrainingFragment fragment = new DetailsTrainingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView descriptionTv= (TextView) view.findViewById(R.id.trainingDetailsDescriptionId);
        descriptionTv.setText(data);
        Button openTrainingProgressBtn=(Button)view.findViewById(R.id.trainingDetailsOpenProgressButtonId);
        openTrainingProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(v.getId());
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int id) {
        if (mListener != null) {
            mListener.onDetailsTrainingsFragmentButtonClicked(id);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDetailsTrainingsFragmentCallback) {
            mListener = (OnDetailsTrainingsFragmentCallback) context;
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
    public interface OnDetailsTrainingsFragmentCallback {
        // TODO: Update argument type and name
        void onDetailsTrainingsFragmentButtonClicked(int id);
    }
}
