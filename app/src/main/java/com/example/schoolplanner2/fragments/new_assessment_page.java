package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.activities.MainActivity;
import com.example.schoolplanner2.models.Assessment;
import com.example.schoolplanner2.models.Course;
import com.example.schoolplanner2.models.Student;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class new_assessment_page extends Fragment {
  private static final String TAG = "new_assessment_page";

  private static final String course_key = "course";

  private Course course;

  public new_assessment_page() {
    // Required empty public constructor
  }

  public static new_assessment_page newInstance(Course input_course) {
    new_assessment_page fragment = new new_assessment_page();
    Bundle args = new Bundle();

    // put course arg in
    args.putParcelable(course_key, input_course);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      course = getArguments().getParcelable(course_key);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // make hashmap for string integer values for input
    Map<String, Integer> input = new HashMap<String, Integer>();

    // most very somewhat least
    // for importance
    input.put("Least", 1);
    input.put("Somewhat", 2);
    input.put("Very", 3);
    input.put("Most", 4);

    // for status
    input.put("None", 1);
    input.put("Some", 2);
    input.put("Most", 3);
    input.put("All", 3);

    // for type
    input.put("Test", 1);
    input.put("Assignment", 2);
    input.put("Lab", 3);


    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_new_assessment_page, container, false);

    // add title relate to course
    String course_code = course.getCourseCode();

    TextView titleTv = v.findViewById(R.id.new_assessment_page_title);

    titleTv.setText(course_code);

    // on submit
    final Button submit_new_assessment_form_button = v.findViewById(R.id.new_assessment_form_submit);
    submit_new_assessment_form_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        // edit texts
        EditText titleEditText  = v.findViewById(R.id.new_assessment_title);
        EditText weightEditText = v.findViewById(R.id.new_assessment_weight);
        EditText descEditText = v.findViewById(R.id.new_assessment_description);

        // radio groups
        RadioGroup importanceGroup = v.findViewById(R.id.new_assessment_importance_group);
        RadioGroup statusGroup = v.findViewById(R.id.new_assessment_status_group);
        RadioGroup typeGroup = v.findViewById(R.id.new_assessment_type_group);

        // radio buttons
        RadioButton importanceButton = v.findViewById(importanceGroup.getCheckedRadioButtonId());
        RadioButton statusButton = v.findViewById(statusGroup.getCheckedRadioButtonId());
        RadioButton typeButton = v.findViewById(typeGroup.getCheckedRadioButtonId());

        // String title, String description, Integer weight, Integer urgency, Integer importance, Integer status, Integer assessmentType, String date
        String input_title   = titleEditText.getText().toString();
        String input_description = descEditText.getText().toString();
        Integer input_weight = Integer.valueOf(weightEditText.getText().toString());
        Integer input_urgency = 1;
        Integer input_importance = input.get(importanceButton.getText().toString());
        Integer input_status = input.get(statusButton.getText().toString());
        Integer input_type = input.get(typeButton.getText().toString());

        // make new assessment
        Assessment newAssessment = new Assessment(input_title, input_description, input_weight, input_urgency, input_importance, input_status, input_type, "");

        //Log.i(TAG, "New assessment made" + newAssessment.toString());

        ArrayList<Assessment> assessments = course.getAssessmentList();

        // add new assessment to course assessment list
        assessments.add(newAssessment);

        //Log.i(TAG, "Course:" + course.toString());
      }
    });

    return v;
  }
}















