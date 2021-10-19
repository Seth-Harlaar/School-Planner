package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.schoolplanner2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link chart_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class chart_page extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public chart_page() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment chart_page.
   */
  // TODO: Rename and change types and number of parameters
  public static chart_page newInstance(String param1, String param2) {
    chart_page fragment = new chart_page();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
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
    View v = inflater.inflate(R.layout.fragment_chart_page, container, false);

    // get the button and add an onclick listener to return home
    final Button home_button_chart = v.findViewById(R.id.home_button_chart);
    home_button_chart.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        // execute navigation to different page
        Navigation.findNavController(v).navigate(R.id.home_page);
      }
    });

    return v;
  }
}