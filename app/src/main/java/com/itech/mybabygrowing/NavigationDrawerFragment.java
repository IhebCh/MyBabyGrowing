package com.itech.mybabygrowing;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itech.adapter.DrawerListViewItemAdapter;
import com.itech.models.DrawerListViewItem;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static String PREF_FILE_NAME = "MyBabyGrowingPref";
    public static String Key_User_Learned_Drawer = "user_save_drawer";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean mUserLearnedDrawer;
    private DrawerListViewItemAdapter drawerListViewItemAdapter;
    private boolean mFromSavedInstance;
    private ListView listView;
    private View containerView;
    private int recyclerViewSelectedItem;
    private NavigationDrawerCallbacks mCallbacks;
    private int mCurrentSelectedPosition;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    public static List<DrawerListViewItem> getData() {

        List<DrawerListViewItem> list = new ArrayList<>();
        DrawerListViewItem drawerListViewItem;
        int[] iconTableau = {R.drawable.today_selected_btn, R.drawable.baby_btn, R.drawable.me_btn, R.drawable.more_btn};
        String[] textTableau = {"Aujourdhui", "Mon enfant", "Moi", "Plus"};
        for (int i = 0; i < iconTableau.length; i++) {
            drawerListViewItem = new DrawerListViewItem();
            drawerListViewItem.setTitle(textTableau[i]);
            drawerListViewItem.setIconId(iconTableau[i]);
            list.add(drawerListViewItem);
        }
        return list;
    }

    private static void savePreferences(Context context, String nameOfPreference, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(nameOfPreference, value);
        editor.apply();

    }

    private static boolean readPreferences(Context context, String nameOfPreference, boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(nameOfPreference, defaultValue);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = readPreferences(getActivity(), Key_User_Learned_Drawer, false);
        if (savedInstanceState != null) {
            mFromSavedInstance = true;
        }


        selectItem(mCurrentSelectedPosition);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        listView = (ListView) layout.findViewById(R.id.drawerList);
            // Inflate the layout for this fragment
        drawerListViewItemAdapter = new DrawerListViewItemAdapter(getActivity(), getData());

        listView.setAdapter(drawerListViewItemAdapter);

        listView.setOnItemClickListener(new SlideMenuClickListener());

        return layout;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        this.mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    savePreferences(getActivity(), Key_User_Learned_Drawer, mUserLearnedDrawer);
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
        //        toolbar.setAlpha(1 - (float) (slideOffset * 0.7));
            }
        };

        Log.v("dkholt","NavigationDrawerFragment-SetUp");
        if (!mUserLearnedDrawer && !mFromSavedInstance) {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        Log.v("dkholt","NavigationDrawerFragment-SetUpAfter");

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }


    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Toast.makeText(getActivity(), "Onclick " + position + " sel " + recyclerViewSelectedItem, Toast.LENGTH_SHORT).show();
              /*remove selection from previous item */
            ((TextView) listView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_text)).setTextColor(getResources().getColor(R.color.primary_color_text));
            listView.getChildAt(recyclerViewSelectedItem).setBackgroundColor(Color.TRANSPARENT);

            switch (recyclerViewSelectedItem) {
                case 0:
                    ((ImageView) (listView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.today_btn);
                    break;
                case 1:
                    ((ImageView) (listView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.baby_btn);

                    break;
                case 2:
                    ((ImageView) (listView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.me_btn);

                    break;
                case 3:
                    ((ImageView) (listView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.more_btn);

                    break;

            }

                  /*  select current item and highlight */
            recyclerViewSelectedItem = position;
            ((TextView) listView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_text)).setTextColor(getResources().getColor(R.color.primary_color_text_big));
            listView.getChildAt(recyclerViewSelectedItem).setBackgroundColor(getResources().getColor(R.color.primary_color_light));

            switch (recyclerViewSelectedItem) {
                case 0:
                    ((ImageView) (listView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.today_selected_btn);
                    break;
                case 1:
                    ((ImageView) (listView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.baby_selected_btn);

                    break;
                case 2:
                    ((ImageView) (listView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.me_selected_btn);

                    break;
                case 3:
                    ((ImageView) (listView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.more_selected_btn);

                    break;


            }

            selectItem(position);
//                listView.getChildAt(recyclerViewSelectedItem).setBackgroundColor(Color.TRANSPARENT);
            //listView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_text);


            //              listView.getChildAt(recyclerViewSelectedItem).setBackgroundColor(Color.CYAN);


        }
    }

  /*  private void displayView(int position) {

        getFragmentManager().beginTransaction()
                .replace(R.id.frame_container, MeFragment.newInstance("","")).commit();

    }
*/
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private void selectItem (int position) {
        mCurrentSelectedPosition = position;
        if (listView != null) {
            listView.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(containerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }
}
