package ru.zaochno.zaochno.trainings;

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

import ru.zaochno.zaochno.R;


/**
 * Список тренингов
 */
public class RootTrainingsFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private static final String ARG_PARAM1 = "menu_item_param";
    private int menuId;

    private OnFragmentInteractionCallback mListener;

    public RootTrainingsFragment() {
        menuId=R.id.navTreningId;
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RootTrainingsFragment newInstance(int menuId) {
       RootTrainingsFragment fragment = new RootTrainingsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, menuId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            menuId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trenings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (ViewPager) view.findViewById(R.id.treningViewpagerId);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) view.findViewById(R.id.treningTabLayoutId);
        mTabLayout.setupWithViewPager(mViewPager);
        switch (menuId){
            case R.id.navFavoriteId:
                mViewPager.setCurrentItem(1);
        }

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
//        if (context instanceof OnFragmentInteractionCallback) {
//            mListener = (OnFragmentInteractionCallback) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionCallback");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(DefaultTreningsFragment.newInstance(), "Тренинги");
        adapter.addFragment(DefaultTreningsFragment.newInstance(), "Избранное");
        adapter.addFragment(DefaultTreningsFragment.newInstance(), "Купленное");
        viewPager.setAdapter(adapter);
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
    public interface OnFragmentInteractionCallback {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
