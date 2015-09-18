package com.itech.mybabygrowing;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

      int[] iconTableauList = {R.drawable.guide_food, R.drawable.guide_exercise, R.drawable.guide_medical};
      String[] textTableauList = {"Nutrition", "Exercice", "Medical" };

      int[] IconTableauListNutrition = {R.drawable.fruits,R.drawable.laitages,R.drawable.viandes_poisson_eoufs};
      String[] textTableauListNutrition = {" 3 fruits. Consommez de préférence des fruits plutôt que des jus de fruits. Plus concentrés, ceux-ci sont riches en sucre, donc souvent trop caloriques (comme un soda). Oui à la banane, mais pas au même repas qu’un féculent !",

            " 3 ou 4 parts, soit 1 part de fromage par jour + 3 parts de lait, yaourt, fromage blanc, etc. Il faut 1 200 mg de calcium par jour. 300 mg = 40 g de fromage à pâte dure (parmesan, gruyère, comté… très riches en graisse aussi !) = ¼ l de lait = 300 g de fromage blanc = 1, 2 ou 3 yaourts. Lisez les étiquettes : le taux en calcium d’un yaourt blanc varie de 130 à 300 mg d’une marque à l’autre.",
            "Les viandes, poissons, œufs : 2 parts, sachant qu'1 part = 150 à 200 g de viande, de poisson, 1 œuf, 2 tranches de jambon. Pas de cru pour éviter les parasites et les problèmes de digestion. On ne compte pas les œufs que l’on met dans un gâteau."};



    public static List<DrawerListViewItem> getData(int[] iconTableauList,String[] textTableauList) {

        List<DrawerListViewItem> list = new ArrayList<>();
        DrawerListViewItem drawerListViewItem;

        for (int i = 0; i < iconTableauList.length; i++) {
            drawerListViewItem = new DrawerListViewItem();
            drawerListViewItem.setTitle(textTableauList[i]);
            drawerListViewItem.setIconId(iconTableauList[i]);
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
        recyclerViewItemAdapter = new RecyclerViewItemAdapter(getActivity(), getData(iconTableauList,textTableauList));

        recyclerView.setAdapter(recyclerViewItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addOnItemTouchListener(new RecyclerOnTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position, RecyclerView recyclerView) {


               // ((TextView) recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_text)).setTextColor(getResources().getColor(R.color.primary_color_text));
                //recyclerView.getChildAt(recyclerViewSelectedItem).setBackgroundColor(Color.TRANSPARENT);

                  /*  select current item and highlight */
                recyclerViewSelectedItem = position;
               // ((TextView) recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_text)).setTextColor(getResources().getColor(R.color.primary_color_text_big));
                //recyclerView.getChildAt(recyclerViewSelectedItem).setBackgroundColor(getResources().getColor(R.color.primary_color_light));

                //recyclerView.getChildAt(recyclerViewSelectedItem).findViewById(R.id.list_text);

                switch (position) {

                    case 0 :

                        recyclerViewItemAdapter.changeList(getData(IconTableauListNutrition,textTableauListNutrition));

                        break;
                }
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
