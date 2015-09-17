package com.itech.mybabygrowing;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.itech.adapter.MePagerAdapter;
import com.itech.tab.PagerSlidingTabStrip;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * <p/>
 * to handle interaction events.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private AddFloatingActionButton addFloatingActionButton;

    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerSlidingTabStrip;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MePagerAdapter mePagerAdapter;
    private Activity activity;
    private int currentPage;

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_me, container, false);
        // Inflate the layout for this fragment

        addFloatingActionButton = (AddFloatingActionButton) view.findViewById(R.id.fab);

        pagerSlidingTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);

        viewPager = (ViewPager) view.findViewById(R.id.pager);

        mePagerAdapter = new MePagerAdapter(getChildFragmentManager(), getActivity());

        viewPager.setAdapter(mePagerAdapter);

        pagerSlidingTabStrip.setViewPager(viewPager);

        //  pagerSlidingTabStrip.onScrollChanged(1,1,1,1);
        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                Log.v("position", "position " + position + " = " + "  Offset " + positionOffset + " -  OffsetPixels " + positionOffsetPixels);

                if (!pagerSlidingTabStrip.isOrientedRight() && position != 0)
                    positionOffset = 1 - positionOffset;
                if (positionOffset > 0.5) positionOffset = 1 - positionOffset;
                addFloatingActionButton.setScaleX(1 - positionOffset * 2);

                addFloatingActionButton.setScaleY(1 - positionOffset * 2);
                //  if (positionOffset >0.5) positionOffset=1-positionOffset ;
                //    addFloatingActionButton.setAlpha(1 - positionOffset*2 );

            }

            @Override
            public void onPageSelected(int position) {
                ((ActionBarActivity) activity).getSupportActionBar().setSubtitle(mePagerAdapter.changeSubtitle(position));
                currentPage = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /*viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //    pagerSlidingTabStrip.update
                //    pagerSlidingTabStrip.setSelectedNavigationItem(position);
            }
        });

       /* for (int i = 0; i < mePagerAdapter.getCount(); i++) {
            pagerSlidingTabStrip.addTab(
                    pagerSlidingTabStrip.newTab()
                            .setIcon(getResources().getDrawable(R.drawable.baby_btn))
                            .setTabListener(this)
            );
        }*/
        ((ActionBarActivity) activity).getSupportActionBar().setSubtitle(mePagerAdapter.changeSubtitle(0));

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        addFloatingActionButton.setAnimation(scaleAnimation);
        scaleAnimation.setDuration(300);
        scaleAnimation.start();
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (currentPage) {
                    case 1:
                        if (!((MotherWeightFragment) mePagerAdapter.getItem(currentPage)).isAddPoidsIsVisible())
                            ((MotherWeightFragment) mePagerAdapter.getItem(currentPage)).showAddPoids(addFloatingActionButton);
                        else
                            ((MotherWeightFragment) mePagerAdapter.getItem(currentPage)).hideAddPoids(addFloatingActionButton);

                        break;
                    case 2:
                        if (!((AppointementsFragment) mePagerAdapter.getItem(currentPage)).isAddAppointmentsIsVisible())
                            ((AppointementsFragment) mePagerAdapter.getItem(currentPage)).showAddAppointments(addFloatingActionButton);
                        else {
                            ((AppointementsFragment) mePagerAdapter.getItem(currentPage)).hideAddAppointments(addFloatingActionButton);

                       //     ((AppointementsFragment) mePagerAdapter.getItem(currentPage)).addAppointement();
                        }
                        break;

                    case 3:
                        if (!((ToDoFragment) mePagerAdapter.getItem(currentPage)).isAddToDoIsVisible())
                            ((ToDoFragment) mePagerAdapter.getItem(currentPage)).showAddToDo(addFloatingActionButton);
                        else {
                            ((ToDoFragment) mePagerAdapter.getItem(currentPage)).hideAddToDo(addFloatingActionButton);

                        }
                        break;

                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {


        this.activity = activity;
        ((ActionBarActivity) activity).getSupportActionBar().setTitle("Moi");

        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface TabChangeTitleListener {

        public String changeSubtitle(int position);
    }

}
