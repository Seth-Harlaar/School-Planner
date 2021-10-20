package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class new_assessment_page extends Fragment {
  private static final String TAG = "new_assessment_page";

  private static final String course_key = "course";
  private static final String assess_key = "assess";

  private Course course;
  private Assessment assessment;
  private Integer mode;

  public new_assessment_page() {
    // Required empty public constructor
  }

  public static new_assessment_page newInstance(Course input_course, Assessment input_assessment, Integer input_mode){
    new_assessment_page fragment = new new_assessment_page();
    Bundle args = new Bundle();

    // put course arg in
    args.putParcelable(course_key, input_course);
    args.putParcelable(assess_key, input_assessment);
    args.putInt("mode", input_mode);

    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      course = getArguments().getParcelable(course_key);
      assessment = getArguments().getParcelable(assess_key);
      mode = getArguments().getInt("mode");
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    // make hashmap for string integer values for input
    Map<String, Integer> input = new HashMap<>();

    // most very somewhat least
    // for importance
    input.put("Least", 1);
    input.put("Somewhat", 2);
    input.put("Very", 3);
    input.put("Most", 4);

    // for status
    input.put("None", 1);
    input.put("Some", 2);
    input.put("Majority", 3);
    input.put("All", 4);

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

    // edit texts
    EditText titleEditText        = v.findViewById(R.id.new_assessment_title);
    EditText descEditText         = v.findViewById(R.id.new_assessment_description);
    EditText weightEditText       = v.findViewById(R.id.new_assessment_weight);
    // radio groups
    RadioGroup importanceGroup    = v.findViewById(R.id.new_assessment_importance_group);
    RadioGroup statusGroup        = v.findViewById(R.id.new_assessment_status_group);
    RadioGroup typeGroup          = v.findViewById(R.id.new_assessment_type_group);


    // if in edit mode, change the title to the assessment
    if(mode == 1){
      titleTv.setText(MessageFormat.format("Edit info for: {0}", assessment.getTitle()));

      // get previous inputs
      String oldTitle       = assessment.getTitle();
      String oldDescription = assessment.getDescription();
      Integer oldWeight     = assessment.getWeight();
      Integer oldImportance = assessment.getImportance();
      Integer oldStatus     = assessment.getStatus();
      Integer oldType       = assessment.getAssessmentType();

      // set info to previous inputs
      titleEditText.setText(oldTitle);
      descEditText.setText(oldDescription);
      weightEditText.setText(String.valueOf(oldWeight));
      if(oldImportance > 0){
        ((RadioButton)importanceGroup.getChildAt(oldImportance - 1)).setChecked(true);
      }
      if(oldStatus > 0){
        ((RadioButton)statusGroup.getChildAt(oldStatus - 1)).setChecked(true);
      }
      if(oldType > 0){
        ((RadioButton)typeGroup.getChildAt(oldType - 1)).setChecked(true);
      }
    }

    // on submit
    final Button submit_new_assessment_form_button = v.findViewById(R.id.new_assessment_form_submit);
    submit_new_assessment_form_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // radio buttons
        RadioButton importanceButton  = v.findViewById(importanceGroup.getCheckedRadioButtonId());
        RadioButton statusButton      = v.findViewById(statusGroup.getCheckedRadioButtonId());
        RadioButton typeButton        = v.findViewById(typeGroup.getCheckedRadioButtonId());

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

        // if in edit mode, first remove the old assessment
        if(mode == 1){
          course.removeAssessment(assessment);
        }

        // add new assessment to course
        course.addAssessment(newAssessment);

        // go back to the assessment view of the new assessment made
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        view_assessment_page view_assessment_frag = view_assessment_page.newInstance(newAssessment, course);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, view_assessment_frag).addToBackStack(null).commit();
      }
    });

    return v;
  }
}















