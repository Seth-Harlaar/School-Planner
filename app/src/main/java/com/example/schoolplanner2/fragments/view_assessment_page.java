package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.models.Assessment;
import com.example.schoolplanner2.models.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link view_assessment_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class view_assessment_page extends Fragment {

  private static final String TAG = "new_assessment_page";

  private static final String assess_key = "assess";
  private static final String course_key = "course";

  private Assessment assessment;
  private Course course;

  public view_assessment_page() {
    // Required empty public constructor
  }

  public static view_assessment_page newInstance(Assessment input_assessment, Course input_course) {
    view_assessment_page fragment = new view_assessment_page();
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
      assessment = getArguments().getParcelable(assess_key);
      course = getArguments().getParcelable(course_key);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_view_assessment_page, container, false);

    final Button input_button = v.findViewById(R.id.assessment_view_input_grade);
    input_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // go to submit grade page
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        submit_grade submit_grade_frag = submit_grade.newInstance(assessment, course);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, submit_grade_frag).addToBackStack(null).commit();
      }
    });

    // add another class button
    final Button grades_add_class_button2 = v.findViewById(R.id.assessment_view_edit_assessment);
    grades_add_class_button2.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        // go to edit assessment form
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        new_assessment_page new_assessment_frag = new_assessment_page.newInstance(course, assessment, 1);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new_assessment_frag).addToBackStack(null).commit();
      }
    });



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