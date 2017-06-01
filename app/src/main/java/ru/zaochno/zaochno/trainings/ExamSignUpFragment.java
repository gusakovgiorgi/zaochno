package ru.zaochno.zaochno.trainings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.zaochno.zaochno.MainActivity;
import ru.zaochno.zaochno.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExamSignUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExamSignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExamSignUpFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String examId;

    private OnFragmentInteractionListener mListener;

    public ExamSignUpFragment() {
        // Required empty public constructor
    }

    public static ExamSignUpFragment newInstance(String examId) {
        ExamSignUpFragment fragment = new ExamSignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, examId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            examId = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.disableToolBarScrolling();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exam_sing_up, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnTestingDetailsFragmentCallBack) {
//            mListener = (OnTestingDetailsFragmentCallBack) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnTestingDetailsFragmentCallBack");
//        }
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
