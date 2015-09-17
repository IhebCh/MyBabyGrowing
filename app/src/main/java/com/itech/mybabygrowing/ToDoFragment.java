package com.itech.mybabygrowing;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doomonafireball.betterpickers.datepicker.DatePickerBuilder;
import com.doomonafireball.betterpickers.datepicker.DatePickerDialogFragment;
import com.doomonafireball.betterpickers.timepicker.TimePickerBuilder;
import com.doomonafireball.betterpickers.timepicker.TimePickerDialogFragment;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.itech.adapter.RecyclerViewToDoAdapter;
import com.itech.models.ToDo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ToDoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ToDoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDoFragment extends Fragment implements DatePickerDialogFragment.DatePickerDialogHandler, TimePickerDialogFragment.TimePickerDialogHandler {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public boolean isAddToDoIsVisible() {
        return addToDoIsVisible;
    }

    public void setAddToDoIsVisible(boolean addToDoIsVisible) {
        this.addToDoIsVisible = addToDoIsVisible;
    }

    private boolean addToDoIsVisible;
    private LinearLayout linearLayout;
    private ViewGroup.LayoutParams layoutParams;
    private RecyclerView recyclerView;
    private RecyclerViewToDoAdapter recyclerViewToDoAdapter;
    private ViewGroup container;
    private TextView addDate, addHeure;
    private EditText commentaire;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToDoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ToDoFragment newInstance(String param1, String param2) {
        ToDoFragment fragment = new ToDoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ToDoFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.addTodo);
        layoutParams = linearLayout.getLayoutParams();
        addToDoIsVisible = false;
        linearLayout.setVisibility(View.GONE);

        recyclerView = (RecyclerView) view.findViewById(R.id.list_todo);
      //  recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        ArrayList<ToDo> toDoArrayList = new ArrayList<ToDo>();

        recyclerViewToDoAdapter = new RecyclerViewToDoAdapter(getActivity(), toDoArrayList);
        recyclerView.setAdapter(recyclerViewToDoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.container = container;

        addDate = (TextView) view.findViewById(R.id.date);
        addHeure = (TextView) view.findViewById(R.id.heure);
        commentaire = (EditText) view.findViewById(R.id.commentaire);
        final Time time = new Time();
        time.setToNow();


        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getChildFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .setTargetFragment(ToDoFragment.this);

                dpb.setYear(time.year);
                dpb.setMonthOfYear(time.month);
                dpb.show();
            }
        });

        addHeure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerBuilder timePickerBuilder = new TimePickerBuilder().setFragmentManager(getChildFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .setTargetFragment(ToDoFragment.this);

                timePickerBuilder.show();
            }
        });

        return view;
    }

    public void showAddToDo(final AddFloatingActionButton addFloatingActionButton) {


        linearLayout.animate().translationY(layoutParams.height)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        addFloatingActionButton.setColorNormal(getResources().getColor(R.color.fab_ok));

                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        linearLayout.setVisibility(View.VISIBLE);
                        //  linearLayout.setAlpha(1.0f);
                        addToDoIsVisible = true;
                    }
                });

    }

    public void hideAddToDo(final AddFloatingActionButton addFloatingActionButton) {
       /* ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();;
        layoutParams.height = 0 ;
        linearLayout.setLayoutParams(layoutParams);
      */
        linearLayout.animate().translationY(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        linearLayout.setVisibility(View.GONE);
                        addToDoIsVisible = false;
                        addFloatingActionButton.setColorNormal(getResources().getColor(R.color.accent_color));

                        addFloatingActionButton.setClickable(true);
                        addToDo();


                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        addFloatingActionButton.setClickable(false);
                    }
                });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @Override
    public void onDialogDateSet(int i, int i1, int i2, int i3) {
        addDate.setText(i3 + "/" + i2 + "/" + i1);
    }

    @Override
    public void onDialogTimeSet(int i, int i1, int i2) {
        addHeure.setText(i1 + ":" + i2);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void addToDo() {

        ToDo toDo = new ToDo(addDate.getText().toString(), commentaire.getText().toString(), false);

        recyclerViewToDoAdapter.add(toDo);
        commentaire.setText("");
        addDate.setText("");
        addHeure.setText("");

    }
}