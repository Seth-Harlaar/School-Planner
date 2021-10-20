package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.models.Assessment;
import com.example.schoolplanner2.models.Course;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link submit_grade#newInstance} factory method to
 * create an instance of this fragment.
 */
public class submit_grade extends Fragment {

  private final static String course_key = "course";
  private final static String assess_key = "assess";

  Assessment assessment;
  Course course;


  public submit_grade() {
    // Required empty public constructor
  }

  public static submit_grade newInstance(Assessment input_assessment, Course input_course) {
    submit_grade fragment = new submit_grade();
    Bundle args = new Bundle();
    args.putParcelable(assess_key, input_assessment);
    args.putParcelable(course_key, input_course);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      assessment = getArguments().getParcelable(assess_key);
      course = getArguments().getParcelable(course_key);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_submit_grade, container, false);

  final Button submit_button = v.findViewById(R.id.submit_grade_submit);
    submit_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // get form data based on switch
        EditText gradeEditText  = v.findViewById(R.id.submit_grade_final_grade);
        EditText achEditText    = v.findViewById(R.id.submit_grade_ach_score);
        EditText maxEditText    = v.findViewById(R.id.submit_grade_max_score);

        Double input_grade      = Double.valueOf(gradeEditText.getText().toString());
        Integer input_ach_score = Integer.valueOf(achEditText.getText().toString());
        Integer input_max_score = Integer.valueOf(maxEditText.getText().toString());

        // public void finishAssessment( Assessment assessment, Double finalGrade, Integer achScore, Integer maxScore ){
        course.finishAssessment(assessment, input_grade, input_ach_score, input_max_score);

        // go back to course view
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        view_course_page view_course_frag = view_course_page.newInstance(course);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, view_course_frag).addToBackStack(null).commit();
      }
    });

    return v;
  }
}
















