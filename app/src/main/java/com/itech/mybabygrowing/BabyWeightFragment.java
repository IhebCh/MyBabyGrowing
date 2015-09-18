package com.itech.mybabygrowing;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.doomonafireball.betterpickers.datepicker.DatePickerBuilder;
import com.doomonafireball.betterpickers.datepicker.DatePickerDialogFragment;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerBuilder;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerDialogFragment;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BabyWeightFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BabyWeightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BabyWeightFragment extends Fragment  implements DatePickerDialogFragment.DatePickerDialogHandler,NumberPickerDialogFragment.NumberPickerDialogHandler  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BabyWeightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BabyWeightFragment newInstance(String param1, String param2) {
        BabyWeightFragment fragment = new BabyWeightFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private LinearLayout linearLayout;

    private LineChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private ViewGroup container;
    private ViewGroup.LayoutParams layoutParams;
    private TextView addPoids, addDate;
    private boolean dateAdded = false, poidAdded = false;
    ArrayList<String> xValsDates = new ArrayList<String>();

    ArrayList<Entry> vals1Poids = new ArrayList<Entry>();
    int cpt = 0;

    public boolean isAddPoidsIsVisible() {
        return addPoidsIsVisible;
    }

    private boolean addPoidsIsVisible;



        public BabyWeightFragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_baby_weight, container, false);
            this.container = container;
            linearLayout = (LinearLayout) view.findViewById(R.id.addPoids);
            //   hideAddAppointments();
            layoutParams = linearLayout.getLayoutParams();
            //   linearLayout.animate().scaleY(0).alpha(1.0f).setDuration(5000);
            addPoidsIsVisible = false;
            linearLayout.setVisibility(View.GONE);

            addPoids = (TextView) view.findViewById(R.id.poids);
            addDate = (TextView) view.findViewById(R.id.date);
            final Time time = new Time();
            time.setToNow();

            addPoids.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NumberPickerBuilder npb = new NumberPickerBuilder()
                            .setFragmentManager(getChildFragmentManager())
                            .setStyleResId(R.style.BetterPickersDialogFragment)
                            .setTargetFragment(BabyWeightFragment.this);

                    npb.show();

                }
            });

            addDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerBuilder dpb = new DatePickerBuilder()
                            .setFragmentManager(getChildFragmentManager())
                            .setStyleResId(R.style.BetterPickersDialogFragment)
                            .setTargetFragment(BabyWeightFragment.this);
                    dpb.setYear(time.year);
                    dpb.setMonthOfYear(time.month);
                    dpb.show();
                }
            });

         /*
               ************ Chart ***************
         */

            mChart = (LineChart) view.findViewById(R.id.chart1);
            // if enabled, the chart will always start at zero on the y-axis
            mChart.setStartAtZero(true);

            // disable the drawing of values into the chart
            mChart.setDrawYValues(false);

            mChart.setDrawBorder(true);

            mChart.setDrawLegend(false);

            // no description text
            mChart.setDescription("");
            mChart.setUnit(" Kg");


            // enable value highlighting
            mChart.setHighlightEnabled(true);

            // enable touch gestures
            mChart.setTouchEnabled(true);

            // enable scaling and dragging
            mChart.setDragEnabled(true);
            mChart.setScaleEnabled(true);

            // if disabled, scaling can be done on x- and y-axis separately
            mChart.setPinchZoom(false);

            mChart.setDrawGridBackground(false);
            mChart.setDrawVerticalGrid(false);

            //  Typeface tf = Typeface.createFromAsset(v.getAssets(), "OpenSans-Regular.ttf");
            //   mChart.setValueTypeface(tf);

            XLabels x = mChart.getXLabels();
            //  x.setTypeface(tf);

            YLabels y = mChart.getYLabels();
            //  y.setTypeface(tf);
            y.setLabelCount(1);

            //setData(36, 100);
            mChart.animateXY(2000, 2000);

            return view;
        }

        // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDialogDateSet(int i, int i1, int i2, int i3) {
        addDate.setText(i3+"/"+i2+"/"+i1);
        dateAdded=true;
    }

    @Override
    public void onDialogNumberSet(int i, int i1, double v, boolean b, double v1) {
        addPoids.setText(v1+" Kg");
        poidAdded=true;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void showAddPoids(AddFloatingActionButton addFloatingActionButton) {
       /* ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT ;
        linearLayout.setLayoutParams(layoutParams);
        */
        linearLayout.animate().translationY(layoutParams.height)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        linearLayout.setVisibility(View.VISIBLE);
                        //  linearLayout.setAlpha(1.0f);
                        addPoidsIsVisible = true;

                    }


                });

    }

    public void hideAddPoids(AddFloatingActionButton addFloatingActionButton) {
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
                        addPoidsIsVisible = false;

                        addDate.setText("");
                        addPoids.setText("");
                        dateAdded = false;
                        poidAdded = false;

                    }
                });

        if(dateAdded && poidAdded) {
            xValsDates.add(addDate.getText() + "");

            vals1Poids.add(new Entry(Float.parseFloat(addPoids.getText().toString().replace(" Kg", "")), cpt));
            cpt++;

            LineDataSet set1 = new LineDataSet(vals1Poids, "DataSet 1");
            set1.setDrawCubic(true);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(2f);
            set1.setCircleSize(5f);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.rgb(104, 241, 175));

            ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
            dataSets.add(set1);

            // create a data object with the datasets
            LineData data = new LineData(xValsDates, dataSets);

            // set data
            mChart.setData(data);
        }

    }

}
