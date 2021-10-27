package com.example.schoolplanner2.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.activities.MainActivity;
import com.example.schoolplanner2.fragments.view_course_page;
import com.example.schoolplanner2.models.Course;

import java.util.ArrayList;

public class CourseListAdapter extends ArrayAdapter<Course> {
  private static final String TAG = "CourseListAdapter";

  private Context context;
  int mResource;

  public CourseListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Course> objects) {
    super(context, resource, objects);
    this.context = context;
    mResource = resource;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    // get filler info
    String course_code = getItem(position).getCourseCode();
    Double course_grade = getItem(position).getCurrentGrade();

    // make inflater and inflate the layout
    LayoutInflater inflater = LayoutInflater.from(context);
    View v = inflater.inflate(mResource, parent, false);

    // get all the view elements
    TextView tv_course_code             = v.findViewById(R.id.course_adapter_course_code);
    TextView tv_course_grade            = v.findViewById(R.id.course_adapter_course_grade);
    Button course_adapter_more_button   = v.findViewById(R.id.course_adapter_more_button);
    ListView assessment_list_view       = (ListView) v.findViewById(R.id.course_adapter_assess_list);
    LinearLayout layout                 = v.findViewById(R.id.course_adapter_layout);
    LinearLayout assess_list_layout     = v.findViewById(R.id.assess_list_layout);

    // set the code and grade
    tv_course_code.setText(course_code);
    tv_course_grade.setText(String.valueOf(course_grade));

    assess_list_layout.setVisibility(View.VISIBLE);

    // add on click for 'more' button
    course_adapter_more_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // navigate to the view_course_page for the class clicked on
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        view_course_page view_course_frag = view_course_page.newInstance(getItem(position));
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, view_course_frag).addToBackStack(null).commit();
      }
    });

    // add each assessment to the list view
    AssessmentListAdapter assessAdapter = new AssessmentListAdapter(getContext(), R.layout.assessment_adapter_view, getItem(position).getAssessmentList(), getItem(position));
    assessment_list_view.setAdapter(assessAdapter);

    // add on click to each list view element
    layout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.i(TAG, "List view element has been clicked " + course_code);

        // switch state of visibility

      }
    });

    return v;
  }
}




















