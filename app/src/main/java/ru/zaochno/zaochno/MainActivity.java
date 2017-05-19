package ru.zaochno.zaochno;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ru.zaochno.zaochno.trainings.DefaultTreningsFragment;
import ru.zaochno.zaochno.trainings.DetailsTrainingFragment;
import ru.zaochno.zaochno.trainings.ProgressTrainingsFragment;
import ru.zaochno.zaochno.trainings.RootTrainingsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DefaultTreningsFragment.OnTrainingDetailsCallBack,DetailsTrainingFragment.OnDetailsTrainingsFragmentCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // showing dot next to notifications label
        navigationView.getMenu().getItem(3).setActionView(R.layout.message_counter);


        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        navigationView.getMenu().getItem(0).setChecked(true);
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
        switch (item.getItemId()){
            case R.id.navTreningId:
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainFrameId, RootTrainingsFragment.newInstance());
                transaction.commit();
        }



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

    @Override
    public void onTrainingClicked(String data) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrameId, DetailsTrainingFragment.newInstance(data));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDetailsTrainingsFragmentButtonDemoClicked(int id) {

//        switch (id){
//            case R.id.trainingDetailsOpenProgressButtonId:
//            case R.id.defaultTrainingsFragmentDemoButtonId:
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainFrameId, ProgressTrainingsFragment.newInstance("fakeId"));
                transaction.addToBackStack(null);
                transaction.commit();

//        }
    }

    @Override
    public void OnTraininDetailsCallbackDemoButtonClicked(int trainingId) {
        onDetailsTrainingsFragmentButtonDemoClicked(trainingId);
    }
}
