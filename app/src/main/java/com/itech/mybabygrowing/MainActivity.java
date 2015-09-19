package com.itech.mybabygrowing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    private Toolbar toolbar;
    private Fragment fragment = null;

    ProjetNaissance projetNaissance ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        projetNaissance=ProjetNaissance.projetNaissance ;

        getSupportActionBar().setTitle("Accueille");
        getSupportActionBar().setSubtitle("");

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


        ///*set image icon floating*/
      /* ImageView icon = new ImageView(this);
        icon.setImageDrawable(getResources().getDrawable(R.drawable.heart));

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();


        actionButton.setPosition(2, (FrameLayout.LayoutParams) actionButton.getLayoutParams());
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

BabyFragment babyFragment =new BabyFragment();
    MeFragment meFragment =  new MeFragment();
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();


        switch (position) {
            case 1:
                fragment = babyFragment;
                Log.v("Fragment", position + "  --  " + fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .commit());

                break;
            case 2:
                fragment =meFragment;
                Log.v("Fragment", position + "  --  " + fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .commit());

                break;
        }
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
        //    super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }

}
