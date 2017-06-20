package ru.zaochno.zaochno.trainings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.zaochno.zaochno.MainActivity;
import ru.zaochno.zaochno.MainApplication;
import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.database.DatabaseManager;
import ru.zaochno.zaochno.drawer.DrawerItemType;
import ru.zaochno.zaochno.model.TrainingsType;
import ru.zaochno.zaochno.model.user.UserManager;
import ru.zaochno.zaochno.rest.category.CategoryGetData;
import ru.zaochno.zaochno.rest.category.CategorySendData;
import ru.zaochno.zaochno.rest.training.TrainingAPI;
import ru.zaochno.zaochno.rest.training.TrainingsGetData;
import ru.zaochno.zaochno.rest.training.TrainingsSendData;


/**
 * Список тренингов
 */
public class RootTrainingsFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private TrainingAPI trainingAPI;

    private static final String ARG_PARAM1 = "menu_item_param";
    private DrawerItemType type;

    private OnRootTrainingsFragmentCallback mListener;

    public RootTrainingsFragment() {
        type = DrawerItemType.DEFAULT_TRAINING;
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RootTrainingsFragment newInstance(DrawerItemType type) {
        RootTrainingsFragment fragment = new RootTrainingsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (DrawerItemType) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.enableToolBarScrolling();
        MainActivity.setEnableButtomLayout(true);
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
        switch (type) {
            case FAVORITE_TRAINING:
                mViewPager.setCurrentItem(1);
        }


        mSwipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDataFromInternet();
            }
        });

        loadDataFromInternet();

    }

    private void loadDataFromInternet() {
        trainingAPI = MainApplication.getInstance().getRetrofit().create(TrainingAPI.class);
        TrainingsSendData data = new TrainingsSendData();
        data.setLimit(100);
        data.setPriceStart(0);
        data.setPriceEnd(50000);
        data.setThematics("");
        data.setToken(UserManager.getInstance().getUser().getUserToken());

        DatabaseManager.getInstance().startLoadingFromInternet();
        trainingAPI.getTrainings(data).enqueue(new Callback<TrainingsGetData>() {
            @Override
            public void onResponse(Call<TrainingsGetData> call, Response<TrainingsGetData> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                Log.v("test",response.body().toString());
                DatabaseManager.getInstance().saveTrainings(response.body().getTrainings());
                List<Fragment> fragments=getChildFragmentManager().getFragments();
                for(Fragment fragment: fragments){
                    ((DefaultTrainingsFragment)fragment).loadData();
                }
            }

            @Override
            public void onFailure(Call<TrainingsGetData> call, Throwable t) {
                DatabaseManager.getInstance().stopLoadingFromInternet();
                mSwipeRefreshLayout.setRefreshing(false);
                t.printStackTrace();
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
        adapter.addFragment(DefaultTrainingsFragment.newInstance(TrainingsType.ALL), "Тренинги");
        adapter.addFragment(DefaultTrainingsFragment.newInstance(TrainingsType.FAVORITES), "Избранное");
        adapter.addFragment(DefaultTrainingsFragment.newInstance(TrainingsType.BOUGHT), "Купленное");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //0- is trenings and 1 - favorites it is corresponf to nav drawer items
                if (position == 0 || position == 1) {
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
        if (mListener != null) {
            mListener.RootTrainingsFragmentChangeDrawerPosition(itemNumber);
        }
    }


    public interface OnRootTrainingsFragmentCallback {
        void RootTrainingsFragmentChangeDrawerPosition(int itemNumber);
    }
}
