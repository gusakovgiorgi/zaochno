package ru.zaochno.zaochno.testing;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import ru.zaochno.zaochno.MainActivity;
import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.model.testing.Test;
import ru.zaochno.zaochno.testing.adapter.TestingsViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnTestingDetailsFragmentCallBack} interface
 * to handle interaction events.
 * Use the {@link TestingDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestingDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Test[] tests;
    private TestingsViewAdapter mAdapter;

    private OnTestingDetailsFragmentCallBack mListener;

    public TestingDetailsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TestingDetailsFragment newInstance(Test[] tests) {
        TestingDetailsFragment fragment = new TestingDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(ARG_PARAM1, tests);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //todo use tis id for tabs
            tests = (Test[]) getArguments().getParcelableArray(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.enableToolBarScrolling();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_testing, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView lv=(ListView)view.findViewById(R.id.testingFragmentListViewId);
        mAdapter=new TestingsViewAdapter(this,new ArrayList<Test>(Arrays.asList(tests)));
        lv.setAdapter(mAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTestingDetailsFragmentCallBack) {
            mListener = (OnTestingDetailsFragmentCallBack) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTestingDetailsFragmentCallBack");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void showTest(Test test){
        if (mListener!=null){
            mListener.OnTestingDetailsFragmentRunTestClicked(test);
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
    public interface OnTestingDetailsFragmentCallBack {
        // TODO: Update argument type and name
        void OnTestingDetailsFragmentRunTestClicked(Test test);
    }
}
