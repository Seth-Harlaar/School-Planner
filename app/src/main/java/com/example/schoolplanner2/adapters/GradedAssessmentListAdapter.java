package com.example.schoolplanner2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.fragments.view_assessment_page;
import com.example.schoolplanner2.fragments.view_graded_assessment_page;
import com.example.schoolplanner2.models.Assessment;
import com.example.schoolplanner2.models.Course;
import com.example.schoolplanner2.models.GradedAssessment;

import java.util.ArrayList;

public class GradedAssessmentListAdapter extends ArrayAdapter<GradedAssessment> {

  private Context context;
  int mResource;
  private Course course;

  public GradedAssessmentListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GradedAssessment> objects, Course input_course) {
    super(context, resource, objects);
    this.context = context;
    mResource = resource;
    course = input_course;
  }


  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View v = inflater.inflate(mResource, null);

    v.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // go to the graded assessment view page for the assess clicked on
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        view_graded_assessment_page view_graded_assessment_frag = view_graded_assessment_page.newInstance(getItem(position), course);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, view_graded_assessment_frag).addToBackStack(null).commit();
      }
    });

    // get the info
    // title weight grade type
    String assessment_title = getItem(position).getTitle();
    Integer assessment_weight = getItem(position).getWeight();
    Double assessment_grade = getItem(position).getFinalGrade();
    Integer assessment_type = getItem(position).getAssessmentType();

    TextView tv_assessment_title = v.findViewById(R.id.graded_assessment_adapter_title);
    TextView tv_assessment_weight = v.findViewById(R.id.graded_assessment_adapter_weight);
    TextView tv_assessment_status = v.findViewById(R.id.graded_assessment_adapter_grade);
    TextView tv_assessment_type = v.findViewById(R.id.graded_assessment_adapter_type);

    tv_assessment_title.setText(assessment_title);
    tv_assessment_weight.setText(String.valueOf(assessment_weight));
    tv_assessment_status.setText(String.valueOf(assessment_grade));
    tv_assessment_type.setText(String.valueOf(assessment_type));

    return v;
    }
  }












