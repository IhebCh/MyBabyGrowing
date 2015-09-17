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
import com.itech.adapter.RecyclerViewAppointementAdapter;
import com.itech.models.Appointement;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AppointementsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AppointementsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointementsFragment extends Fragment implements DatePickerDialogFragment.DatePickerDialogHandler, TimePickerDialogFragment.TimePickerDialogHandler {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayout linearLayout;
    private ViewGroup container;
    private RecyclerViewAppointementAdapter recyclerViewAppointementAdapter;
    private RecyclerView recyclerView;
    private boolean addAppointmentsIsVisible = false;
    private ViewGroup.LayoutParams layoutParams;
    private TextView addDate, addHeure;
    private EditText nom, commentaire;

    //  private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointementsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointementsFragment newInstance(String param1, String param2) {
        AppointementsFragment fragment = new AppointementsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AppointementsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_appointements, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.addRDV);
        layoutParams = linearLayout.getLayoutParams();
        addAppointmentsIsVisible = false;
        linearLayout.setVisibility(View.GONE);

        recyclerView = (RecyclerView) view.findViewById(R.id.list_appointments);
     //   recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        ArrayList<Appointement> appointementItems= new ArrayList<Appointement>();


        recyclerViewAppointementAdapter = new RecyclerViewAppointementAdapter(getActivity(), appointementItems);
        recyclerView.setAdapter(recyclerViewAppointementAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.container = container;


        addDate = (TextView) view.findViewById(R.id.date);
        addHeure = (TextView) view.findViewById(R.id.heure);
        nom = (EditText) view.findViewById(R.id.nom);
        commentaire = (EditText) view.findViewById(R.id.commentaire);
        final Time time = new Time();   time.setToNow();


        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getChildFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .setTargetFragment(AppointementsFragment.this);

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
                        .setTargetFragment(AppointementsFragment.this);

                timePickerBuilder.show();
            }
        });

        return view;
    }

    public boolean isAddAppointmentsIsVisible() {
        return addAppointmentsIsVisible;
    }

    public void showAddAppointments(final AddFloatingActionButton addFloatingActionButton) {
       /* ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT ;
        linearLayout.setLayoutParams(layoutParams);
        */
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
                        addAppointmentsIsVisible = true;
                    }
                });

    }

    public void hideAddAppointments(final AddFloatingActionButton addFloatingActionButton) {
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
                        addAppointmentsIsVisible = false;
                        addFloatingActionButton.setColorNormal(getResources().getColor(R.color.accent_color));

                        addFloatingActionButton.setClickable(true);
                        addAppointement();


                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        addFloatingActionButton.setClickable(false);
                    }
                });
    }
    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //      mListener = null;
    }

    @Override
    public void onDialogDateSet(int i, int i1, int i2, int i3) {
        addDate.setText(i3 + "/" + i2 + "/" + i1);
    }

    @Override
    public void onDialogTimeSet(int i, int i1, int i2) {
        addHeure.setText(i1+":"+i2);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void addAppointement(){
        Appointement appointement = new Appointement();
        appointement.setDate(addDate.getText().toString());
        appointement.setHeure(addHeure.getText().toString());
        appointement.setNom(nom.getText().toString());
        appointement.setIcon(R.drawable.timeline_icon);
        recyclerViewAppointementAdapter.add(appointement);
        commentaire.setText("");
        addDate.setText("");
        addHeure.setText("");
    }
}
