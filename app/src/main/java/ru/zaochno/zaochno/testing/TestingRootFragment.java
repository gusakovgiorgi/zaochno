package ru.zaochno.zaochno.testing;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.zaochno.zaochno.FakeData;
import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.model.testing.Test;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestingRootFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestingRootFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestingRootFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    

    private OnFragmentInteractionListener mListener;

    public TestingRootFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TestingRootFragment newInstance() {
        TestingRootFragment fragment = new TestingRootFragment();

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
        return inflater.inflate(R.layout.fragment_testing_root, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (ViewPager) view.findViewById(R.id.testingViewpagerId);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) view.findViewById(R.id.testingTabLayoutId);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        List<Test> testList=FakeData.getTrainings().get(0).getTests();
        List<Test> scurrentTest=new ArrayList<>();
        List<Test> proidennieTest=new ArrayList<>();
        for(Test test:testList){
            if(test.getTestProgress()==0){
                scurrentTest.add(test);
            }else{
                proidennieTest.add(test);
            }
        }

        adapter.addFragment(TestingDetailsFragment.newInstance(scurrentTest.toArray(new Test[scurrentTest.size()])), "Текущее тестирование");
        adapter.addFragment(TestingDetailsFragment.newInstance(proidennieTest.toArray(new Test[proidennieTest.size()])), "Пройденные тесты");
        viewPager.setAdapter(adapter);
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

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {


        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
