package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.activities.MainActivity;
import com.example.schoolplanner2.adapters.AssessmentListAdapter;
import com.example.schoolplanner2.adapters.CourseListAdapter;
import com.example.schoolplanner2.adapters.GradedAssessmentListAdapter;
import com.example.schoolplanner2.models.Assessment;
import com.example.schoolplanner2.models.Course;
import com.example.schoolplanner2.models.GradedAssessment;
import com.example.schoolplanner2.models.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;


public class view_course_page extends Fragment {

  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String course_key = "course";

  private Course course;

  public view_course_page() {
    // Required empty public constructor
  }

  public static view_course_page newInstance(Course input_course) {
    view_course_page fragment = new view_course_page();
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
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_view_course_page, container, false);

    // get course info
    String course_code  = course.getCourseCode();
    String course_grade = String.valueOf(course.getCurrentGrade());
    String course_title = course.getTitle();
    String course_abs_grade = String.valueOf(course.getAbsoluteGrade());


    // find textview and set info
    final TextView codeTv       = v.findViewById(R.id.course_view_page_code);
    final TextView gradeTv      = v.findViewById(R.id.course_view_page_grade);
    final TextView titleTv      = v.findViewById(R.id.course_view_page_title);
    final TextView absGradeTv   = v.findViewById(R.id.course_view_page_abs_grade);

    codeTv.setText(course_code);
    gradeTv.setText(course_grade);
    titleTv.setText(course_title);
    absGradeTv.setText(course_abs_grade);

    ArrayList<GradedAssessment> gradedAssessments  = course.getGradedAssessmentList();
    ArrayList<Assessment> assessments              = course.getAssessmentList();

    // get the list view and add each course to the course view
    ListView assessment_list_view = (ListView) v.findViewById(R.id.course_view_assessment_list);
    AssessmentListAdapter assessAdapter = new AssessmentListAdapter(getContext(), R.layout.assessment_adapter_view, assessments, course);
    assessment_list_view.setAdapter(assessAdapter);

    ListView gradedAssessmentListView = (ListView) v.findViewById(R.id.course_view_graded_assessment_list);
    GradedAssessmentListAdapter gradedAdapter = new GradedAssessmentListAdapter(getContext(), R.layout.graded_adapter_view, gradedAssessments, course);
    gradedAssessmentListView.setAdapter(gradedAdapter);


    return v;
  }
}


















