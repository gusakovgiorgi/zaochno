package ru.zaochno.zaochno;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.birbit.android.jobqueue.JobManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.zaochno.zaochno.dialogs.BuyDialogFragment;
import ru.zaochno.zaochno.dialogs.FilterDialogFragment;
import ru.zaochno.zaochno.job.LoadCategoryJob;
import ru.zaochno.zaochno.message.MessagesFragment;
import ru.zaochno.zaochno.model.Training;
import ru.zaochno.zaochno.model.testing.Test;
import ru.zaochno.zaochno.model.user.UserManager;
import ru.zaochno.zaochno.settings.UserProfileFragment;
import ru.zaochno.zaochno.testing.TestingDetailsFragment;
import ru.zaochno.zaochno.testing.TestingFragment;
import ru.zaochno.zaochno.testing.TestingRootFragment;
import ru.zaochno.zaochno.trainings.DefaultTrainingsFragment;
import ru.zaochno.zaochno.trainings.DetailsTrainingFragment;
import ru.zaochno.zaochno.trainings.ExamSignUpFragment;
import ru.zaochno.zaochno.trainings.ProgressTrainingsFragment;
import ru.zaochno.zaochno.trainings.RootTrainingsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DefaultTrainingsFragment.OnDefaultTrainingsFragmentCallBack,
        DetailsTrainingFragment.OnDetailsTrainingsFragmentCallback, ProgressTrainingsFragment.OnProgressTrainingsFragmentCallback,
        TestingDetailsFragment.OnTestingDetailsFragmentCallBack, RootTrainingsFragment.OnRootTrainingsFragmentCallback {


    @BindView(R.id.navigationDrawerUserNameTvId)
    TextView mUserNameTv;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    private static Toolbar toolbar;
    private static View bottomLayoutView;
    @BindView(R.id.filterImageButtonId)
    ImageButton filterImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!UserManager.getInstance().isUserLogIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomLayoutView = findViewById(R.id.bottomLayoutId);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        // showing dot next to notifications label
        mNavigationView.getMenu().getItem(3).setActionView(R.layout.message_counter);


        onNavigationItemSelected(mNavigationView.getMenu().getItem(0));
        mNavigationView.getMenu().getItem(0).setChecked(true);

        mUserNameTv.setText(FakeData.DUMMY_USER_DATA[0]);

        setupBottomLayout();

        MainApplication.getInstance().getJobManager().addJobInBackground(new LoadCategoryJob());
    }

    private void setupBottomLayout() {
        filterImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DialogFragment.show() will take care of adding the fragment
                // in a transaction.  We also want to remove any currently showing
                // dialog, so make our own transaction and take care of that here.
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.navTreningId:
            case R.id.navFavoriteId:
//                enableToolBarScrolling();
                transaction.replace(R.id.mainFrameId, RootTrainingsFragment.newInstance(item.getItemId()));
                break;
            case R.id.navTestID:
//                enableToolBarScrolling();
                transaction.replace(R.id.mainFrameId, TestingRootFragment.newInstance());
                break;
            case R.id.navSettingsId:
//                disableToolBarScrolling();
//                setEnableButtomLayout(false);
                transaction.replace(R.id.mainFrameId, UserProfileFragment.newInstance());
                break;
            case R.id.navMessageId:
//                disableToolBarScrolling();
//                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
//                        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
//                setEnableButtomLayout(false);
                transaction.replace(R.id.mainFrameId, MessagesFragment.newInstance());
                break;
            case R.id.navExitId:
                startLoginActivity();

        }
        transaction.commit();


//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void disableToolBarScrolling() {
        if (toolbar == null) {
            return;
        }
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        if (params.getScrollFlags() != 0) {
            params.setScrollFlags(0);
            toolbar.setLayoutParams(params);
        }
    }

    public static void enableToolBarScrolling() {
        if (toolbar == null) {
            return;
        }
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        if (params.getScrollFlags() != (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS)) {
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
            toolbar.setLayoutParams(params);
        }
    }

    private void startLoginActivity() {
        FakeData.setIsUserLogin(false);
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    public static void setEnableButtomLayout(boolean enable) {
        if(bottomLayoutView==null){
            return;
        }
        bottomLayoutView.setVisibility(enable ? View.VISIBLE : View.GONE);
    }


    @Override
    public void onDefaultTrainingsFragmentTrainingClicked(Training training) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrameId, DetailsTrainingFragment.newInstance(training));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDefaultTrainingsFragmentBuyTrainingClicked(Training training) {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("buy_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = BuyDialogFragment.newInstance(training);
        newFragment.show(ft, "buy_dialog");
    }

    @Override
    public void onDetailsTrainingsFragmentButtonDemoClicked(int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrameId, ProgressTrainingsFragment.newInstance("fakeId"));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void OnDefaultTrainingsFragmentDemoButtonClicked(int trainingId) {
        disableToolBarScrolling();
        onDetailsTrainingsFragmentButtonDemoClicked(trainingId);
    }

    @Override
    public void onSignUpExam(String examId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrameId, ExamSignUpFragment.newInstance("fakeId"));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void OnTestingDetailsFragmentRunTestClicked(Test test) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrameId, TestingFragment.newInstance(test));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void RootTrainingsFragmentChangeDrawerPosition(int itemNumber) {
        mNavigationView.getMenu().getItem(itemNumber).setChecked(true);
    }
}
