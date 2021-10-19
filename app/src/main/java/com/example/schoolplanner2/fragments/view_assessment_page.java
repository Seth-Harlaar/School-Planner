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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link view_assessment_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class view_assessment_page extends Fragment {

  private static final String TAG = "new_assessment_page";

  private static final String assess_key = "assess";

  private Assessment assessment;

  public view_assessment_page() {
    // Required empty public constructor
  }

  public static view_assessment_page newInstance(Assessment input_assessment) {
    view_assessment_page fragment = new view_assessment_page();
    Bundle args = new Bundle();

    // put course arg in
    args.putParcelable(assess_key, input_assessment);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      assessment = getArguments().getParcelable(assess_key);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_view_assessment_page, container, false);

    // String title, String description, Integer weight, Integer urgency, Integer importance, Integer status, Integer assessmentType, String date

    // get all text views
    TextView titleTv = v.findViewById(R.id.assessment_view_title);
    TextView descriptionTv = v.findViewById(R.id.assessment_view_description);
    TextView weightTv = v.findViewById(R.id.assessment_view_weight);
    TextView importanceTv = v.findViewById(R.id.assessment_view_importance);
    TextView statusTv = v.findViewById(R.id.assessment_view_status);
    TextView typeTv = v.findViewById(R.id.assessment_view_type);

    // set texts
    titleTv.setText(assessment.getTitle());
    descriptionTv.setText(assessment.getDescription());
    weightTv.setText(String.valueOf(assessment.getWeight()));
    importanceTv.setText(String.valueOf(assessment.getImportance()));
    statusTv.setText(String.valueOf(assessment.getStatus()));
    typeTv.setText(String.valueOf(assessment.getAssessmentType()));

    return v;

  }
}