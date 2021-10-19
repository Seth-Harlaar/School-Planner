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
import com.example.schoolplanner2.fragments.view_course_page;
import com.example.schoolplanner2.models.Assessment;
import com.example.schoolplanner2.models.Course;

import java.util.ArrayList;

public class AssessmentListAdapter extends ArrayAdapter<Assessment> {
  private static final String TAG = "AssessmentListAdapter";

  private Context context;
  int mResource;

  public AssessmentListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Assessment> objects) {
    super(context, resource, objects);
    this.context = context;
    mResource = resource;
  }

  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    // inflate the view
    LayoutInflater inflater = LayoutInflater.from(context);
    View v = inflater.inflate(mResource, null);

    v.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // get assessment clicked on

        // go to the assessment view page for the assess clicked on
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        view_assessment_page view_assessment_frag = view_assessment_page.newInstance(getItem(position));
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, view_assessment_frag).addToBackStack(null).commit();
      }
    });


    // get the info
    // title weight status type
    String assessment_title = getItem(position).getTitle();
    Integer assessment_weight = getItem(position).getWeight();
    Integer assessment_status = getItem(position).getStatus();
    Integer assessment_type = getItem(position).getAssessmentType();

    TextView tv_assessment_title = v.findViewById(R.id.assessment_adapter_title);
    TextView tv_assessment_weight = v.findViewById(R.id.assessment_adapter_weight);
    TextView tv_assessment_status = v.findViewById(R.id.assessment_adapter_status);
    TextView tv_assessment_type = v.findViewById(R.id.assessment_adapter_type);

    tv_assessment_title.setText(assessment_title);
    tv_assessment_weight.setText(String.valueOf(assessment_weight));
    tv_assessment_status.setText(String.valueOf(assessment_status));
    tv_assessment_type.setText(String.valueOf(assessment_type));

    // return view
    return v;
  }

}















