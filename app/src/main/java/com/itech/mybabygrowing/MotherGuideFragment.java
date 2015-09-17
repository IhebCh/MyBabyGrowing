package com.itech.mybabygrowing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.itech.adapter.RecyclerViewItemAdapter;
import com.itech.models.DrawerListViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MotherGuideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MotherGuideFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    */private RecyclerViewItemAdapter recyclerViewItemAdapter;

    private RecyclerView recyclerView;
    private View containerView;
    private int recyclerViewSelectedItem;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public static List<DrawerListViewItem> getData() {

        List<DrawerListViewItem> list = new ArrayList<>();
        DrawerListViewItem drawerListViewItem;
        int[] iconTableau = {R.drawable.guide_food, R.drawable.guide_exercise, R.drawable.guide_medical, R.drawable.guide_labour};
        String[] textTableau = {"Nutrition", "Exercice", "Medical", "Plus"};
        for (int i = 0; i < iconTableau.length; i++) {
            drawerListViewItem = new DrawerListViewItem();
            drawerListViewItem.setTitle(textTableau[i]);
            drawerListViewItem.setIconId(iconTableau[i]);
            list.add(drawerListViewItem);
        }
        return list;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuideMotherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MotherGuideFragment newInstance(String param1, String param2) {
        MotherGuideFragment fragment = new MotherGuideFragment();
        Bundle args = new Bundle();
       /* args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        */fragment.setArguments(args);
        return fragment;
    }

    public MotherGuideFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_mother_guide, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.mother_guide_list);       // Inflate the layout for this fragment
        recyclerViewItemAdapter = new RecyclerViewItemAdapter(getActivity(), getData());

        recyclerView.setAdapter(recyclerViewItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addOnItemTouchListener(new RecyclerOnTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position, RecyclerView recyclerView) {

                Toast.makeText(getActivity(), "Onclick " + position + " sel " + recyclerViewSelectedItem, Toast.LENGTH_SHORT).show();

            /*remove selection from previous item */

                ((TextView) recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_text)).setTextColor(getResources().getColor(R.color.primary_color_text));
                recyclerView.getChildAt(recyclerViewSelectedItem).setBackgroundColor(Color.TRANSPARENT);

            /*    switch (recyclerViewSelectedItem) {

                    case 0:
                        ((ImageView) (recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.today_btn);
                        break;
                    case 1:
                        ((ImageView) (recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.baby_btn);

                        break;
                    case 2:
                        ((ImageView) (recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.me_btn);

                        break;
                    case 3:
                        ((ImageView) (recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.more_btn);

                        break;

                }*/

                  /*  select current item and highlight */
                recyclerViewSelectedItem = position;
                ((TextView) recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_text)).setTextColor(getResources().getColor(R.color.primary_color_text_big));
                recyclerView.getChildAt(recyclerViewSelectedItem).setBackgroundColor(getResources().getColor(R.color.primary_color_light));

           /*     switch (recyclerViewSelectedItem) {
                    case 0:
                        ((ImageView) (recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.today_selected_btn);
                        break;
                    case 1:
                        ((ImageView) (recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.baby_selected_btn);

                        break;
                    case 2:
                        ((ImageView) (recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.me_selected_btn);

                        break;
                    case 3:
                        ((ImageView) (recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_icon))).setImageResource(R.drawable.more_selected_btn);

                        break;

                }*/


//                recyclerView.getChildAt(recyclerViewSelectedItem).setBackgroundColor(Color.TRANSPARENT);
                recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_text);


                //              recyclerView.getChildAt(recyclerViewSelectedItem).setBackgroundColor(Color.CYAN);

            }

            @Override
            public void onLongClick(View view, int position, RecyclerView recyclerView) {
                Toast.makeText(getActivity(), "OnLongclick " + position, Toast.LENGTH_SHORT).show();

            }
        }));
        return layout;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
          //  mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    //    mListener = null;
    }



    public static interface ClickListener {
        public void onClick(View view, int position, RecyclerView recyclerView);

        public void onLongClick(View view, int position, RecyclerView recyclerView);


    }

    class RecyclerOnTouchListener implements RecyclerView.OnItemTouchListener {

        GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerOnTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (view != null && clickListener != null) {
                        clickListener.onLongClick(view, recyclerView.getChildPosition(view), recyclerView);
                    }
                    //  super.onLongPress(e);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());

            if (view != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(view, rv.getChildPosition(view), rv);
            }


            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }
    }

}
