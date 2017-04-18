package com.example.theom.mmha;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.theom.mmha.Fragments.HomepageFragment;
import com.example.theom.mmha.Fragments.Places.PlacePins;
import com.example.theom.mmha.Fragments.Places.SearchDoctorFragment;
import com.example.theom.mmha.Fragments.SeeSightsFragment;
import com.example.theom.mmha.MySafety_Quiz.AssessmentFinishFragment;
import com.example.theom.mmha.MySafety_Quiz.QuestionFragment;
import com.example.theom.mmha.MySafety_Quiz.SetupAssessmentFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomepageFragment.OnFragmentInteractionListener,
        HomepageFragment.OnSetToolbarTitleListener,
        QuestionFragment.OnFragmentInteractionListener,
        QuestionFragment.OnSetToolbarTitleListener,
        SetupAssessmentFragment.OnSetToolbarTitleListener,
        AssessmentFinishFragment.OnSetToolbarTitleListener,
        SeeSightsFragment.OnFragmentInteractionListener,
        SeeSightsFragment.OnSetToolbarTitleListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        TextView egristLink = (TextView) header.findViewById(R.id.more_info_address);
        egristLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.egrist.org";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Fragment fragment = new HomepageFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.relativeLayout, fragment).commit();


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
        Fragment fragment = new Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.relativeLayout, fragment).commit();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment = new HomepageFragment();
        } else if (id == R.id.nav_assessment) {
            fragment = new QuestionFragment();
        } else if (id == R.id.nav_hospital) {
            fragment = new SearchDoctorFragment();
        } else if (id == R.id.nav_prev_assessment) {
            fragment = new SeeSightsFragment();
        }

        fragmentManager.beginTransaction().replace(R.id.relativeLayout, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static PlacePins returnPlacePins() {
        PlacePins placePins = new PlacePins();
        return placePins;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
