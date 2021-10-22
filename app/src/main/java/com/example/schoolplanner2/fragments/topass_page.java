package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.activities.MainActivity;
import com.example.schoolplanner2.models.Assessment;
import com.example.schoolplanner2.models.Course;
import com.example.schoolplanner2.models.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link topass_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class topass_page extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public topass_page() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment topass_page.
   */
  // TODO: Rename and change types and number of parameters
  public static topass_page newInstance(String param1, String param2) {
    topass_page fragment = new topass_page();
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
    View v = inflater.inflate(R.layout.fragment_topass_page, container, false);

    // get all the course names
    MainActivity activity = (MainActivity) getActivity();
    Student student = activity.getStudent();
    ArrayList<Course> courses = student.getCourses();
    ArrayList<String> course_names = new ArrayList<>();

    // add each name
    for( Course course: courses ) {
      course_names.add(course.getCourseCode());
    }

    // add selections to the spinner for every course
    Spinner topass_page_spinner = v.findViewById(R.id.topass_page_spinner);
    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, course_names);

    topass_page_spinner.setAdapter(spinnerAdapter);

    // on submit
    final Button topass_submit_button = v.findViewById(R.id.topass_submit);
    topass_submit_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // get selection/course
        String course_code = topass_page_spinner.getSelectedItem().toString();

        Course selected_course = null;
        Double required_grade = 0.00;

        for( Course course_iter: courses ){
          if( course_iter.getCourseCode().equals(course_code) ){
            selected_course = course_iter;
          }
        }

        // get get the input
        EditText weightEt = v.findViewById(R.id.topass_exam_weight);
        EditText targetEt = v.findViewById(R.id.topass_passing_grade);
        TextView resultTV = v.findViewById(R.id.topass_exam_grade);

        Double input_weight = Double.valueOf(weightEt.getText().toString());
        Double input_target = Double.valueOf(targetEt.getText().toString());

        if( selected_course != null ){
          required_grade = selected_course.toPass(input_weight, input_target);
        }

        // set the output text
        resultTV.setText(String.valueOf(required_grade));
      }
    });

    return v;
  }
}




















