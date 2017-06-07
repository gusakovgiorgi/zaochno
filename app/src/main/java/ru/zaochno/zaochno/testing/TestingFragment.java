package ru.zaochno.zaochno.testing;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.model.testing.Question;
import ru.zaochno.zaochno.model.testing.Test;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private ViewGroup mViewGroup;
    private int questionNumber=0;

    // TODO: Rename and change types of parameters
    private Test mTest;

    private OnFragmentInteractionListener mListener;

    public TestingFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TestingFragment newInstance(Test test) {
        TestingFragment fragment = new TestingFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, test);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTest = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_testing, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewGroup=(ViewGroup)view.findViewById(R.id.testingPredicateLayouttId);
        drawQuestion(questionNumber);

    }

    private void drawQuestion(int number) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        if(mTest.getQuestions().size()>number){
            final Question question=mTest.getQuestions().get(number);
            TextView questionTv=(TextView)getView().findViewById(R.id.testingQuestionId);
            questionTv.setText(question.getQuestion());
            questionNumber++;
            mViewGroup.removeAllViews();
            for(int i=0;i<question.getAnswers().size();i++){

                Button btn= (Button) inflater.inflate(R.layout.answer_bytton_layout,null);
//                btn.styl
                btn.setText(question.getAnswers().get(i));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawQuestion(questionNumber);
                    }
                });
                mViewGroup.addView(btn);
            }
        }else{
            //todo clear other view
            mViewGroup.removeAllViews();
            TextView tv=new TextView(getContext());
            tv.setText("тест Окончен");
            mViewGroup.addView(tv);
        }
        mViewGroup.invalidate();
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
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
