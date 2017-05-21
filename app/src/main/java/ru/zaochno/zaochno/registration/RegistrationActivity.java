package ru.zaochno.zaochno.registration;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.registration.ExtendedRegistrationFragment;
import ru.zaochno.zaochno.registration.SimpleRegistrationFragment;

/**
 * A login screen that offers login via email/password.
 */
public class RegistrationActivity extends AppCompatActivity{


    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mViewPager = (ViewPager) findViewById(R.id.registrationActivityViewPagerId);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.registrationActivityTabBarId);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ExtendedRegistrationFragment(), "Юридическое Лицо");
        adapter.addFragment(new SimpleRegistrationFragment(), "Физическое Лицо");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}

