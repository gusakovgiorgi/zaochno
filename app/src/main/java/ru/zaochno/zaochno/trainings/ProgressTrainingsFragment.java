package ru.zaochno.zaochno.trainings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.trainings.adapter.ProgressListViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnProgressTrainingsFragmentCallback} interface
 * to handle interaction events.
 * Use the {@link ProgressTrainingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgressTrainingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnProgressTrainingsFragmentCallback mListener;

    public ProgressTrainingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ProgressTrainingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgressTrainingsFragment newInstance(String param1) {
        ProgressTrainingsFragment fragment = new ProgressTrainingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trainings_progress, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView lv=(ListView)view.findViewById(R.id.progressTrainingsFragmentListViewId);
        List<String> data=new ArrayList<>();
        data.add("fake1");
        data.add("fake2");
        data.add("fake3");
        lv.setAdapter(new ProgressListViewAdapter(getContext(),data));

        LinearLayout examSingUpButton=(LinearLayout)view.findViewById(R.id.progressTrainingsFragmentExamSingUpButtonId);
        examSingUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singUpExam("fake id");
            }
        });
        LinearLayout startTestingBtn=(LinearLayout)view.findViewById(R.id.progressTrainingsFragmentstartTestingButtonId);
        startTestingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTesting("refactor");
            }
        });
    }

    private void singUpExam(String s) {
        if (mListener != null) {
            mListener.onSignUpExam(s);
        }
    }
    //todo refactor this class for siutable model
    private void startTesting(String s) {
//        if (mListener != null) {
//            mListener.onSignUpExam(s);
//        }
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onSignUpExam(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProgressTrainingsFragmentCallback) {
            mListener = (OnProgressTrainingsFragmentCallback) context;
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
    public interface OnProgressTrainingsFragmentCallback {
        // TODO: Update argument type and name
        void onSignUpExam(String examId);
    }


}
