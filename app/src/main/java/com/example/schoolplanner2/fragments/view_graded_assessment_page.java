package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.models.Assessment;
import com.example.schoolplanner2.models.Course;
import com.example.schoolplanner2.models.GradedAssessment;


public class view_graded_assessment_page extends Fragment {

  private static final String TAG = "view_graded_assessment_page";

  private static final String assess_key = "assess";
  private static final String course_key = "course";

  private GradedAssessment assessment;
  private Course course;

  public view_graded_assessment_page() {
    // Required empty public constructor
  }

  public static view_graded_assessment_page newInstance(GradedAssessment input_assessment, Course input_course) {
    view_graded_assessment_page fragment = new view_graded_assessment_page();
    Bundle args = new Bundle();

    // put course arg in
    args.putParcelable(assess_key, input_assessment);
    args.putParcelable(course_key, input_course);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      course = getArguments().getParcelable(course_key);
      assessment = getArguments().getParcelable(assess_key);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_view_graded_assessment_page, container, false);

    // get all text views
    TextView titleTv        = v.findViewById(R.id.graded_assessment_view_title);
    TextView descriptionTv  = v.findViewById(R.id.graded_assessment_view_description);
    TextView weightTv       = v.findViewById(R.id.graded_assessment_view_weight);
    TextView importanceTv   = v.findViewById(R.id.graded_assessment_view_importance);
    TextView gradeTv        = v.findViewById(R.id.graded_assessment_view_grade);
    TextView typeTv         = v.findViewById(R.id.graded_assessment_view_type);

    // set texts
    titleTv.setText(assessment.getTitle());
    descriptionTv.setText(assessment.getDescription());
    weightTv.setText(String.valueOf(assessment.getWeight()));
    importanceTv.setText(String.valueOf(assessment.getImportance()));
    gradeTv.setText(String.valueOf(assessment.getFinalGrade()));
    typeTv.setText(String.valueOf(assessment.getAssessmentType()));

    return v;
  }
}













