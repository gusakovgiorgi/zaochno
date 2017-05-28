package ru.zaochno.zaochno.trainings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.dialogs.FilterDialogFragment;
import ru.zaochno.zaochno.model.TrainingsCategory;


/**
 * Список тренингов
 */
public class RootTrainingsFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private static final String ARG_PARAM1 = "menu_item_param";
    private int menuId;

    private OnRootTrainingsFragmentCallback mListener;

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
        return inflater.inflate(R.layout.fragment_trainings_root, container, false);
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

        ImageButton imageButton=(ImageButton)view.findViewById(R.id.rootTrainingsFragmentSettinsImageButtonId);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DialogFragment.show() will take care of adding the fragment
                // in a transaction.  We also want to remove any currently showing
                // dialog, so make our own transaction and take care of that here.
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                Fragment prev = getChildFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                // Create and show the dialog.
                DialogFragment newFragment = FilterDialogFragment.newInstance();
                newFragment.show(ft, "dialog");

            }
        });



    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRootTrainingsFragmentCallback) {
            mListener = (OnRootTrainingsFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRootTrainingsFragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(DefaultTrainingsFragment.newInstance(TrainingsCategory.ALL), "Тренинги");
        adapter.addFragment(DefaultTrainingsFragment.newInstance(TrainingsCategory.FAVORITES), "Избранное");
        adapter.addFragment(DefaultTrainingsFragment.newInstance(TrainingsCategory.BOUGHT), "Купленное");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //0- is trenings and 1 - favorites it is corresponf to nav drawer items
                if(position==0||position==1){
                    setNavigarionDrawerItemTo(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    private void setNavigarionDrawerItemTo(int itemNumber) {
        if(mListener!=null){
            mListener.RootTrainingsFragmentChangeDrawerPosition(itemNumber);
        }
    }



    public interface OnRootTrainingsFragmentCallback {
        void RootTrainingsFragmentChangeDrawerPosition(int itemNumber);
    }
}
