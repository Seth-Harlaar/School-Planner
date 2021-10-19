package com.example.schoolplanner2.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.schoolplanner2.R;
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
    // get info
    String course_code = getItem(position).getCourseCode();
    Double course_grade = getItem(position).getCurrentGrade();

    // make inflater and inflate the layout
    LayoutInflater inflater = LayoutInflater.from(context);
    View v = inflater.inflate(mResource, parent, false);

    TextView tv_course_code = v.findViewById(R.id.course_adapter_course_code);
    TextView tv_course_title = v.findViewById(R.id.course_adapter_course_title);

    tv_course_code.setText(course_code);
    tv_course_title.setText(String.valueOf(course_grade));

    // add on click to each list view element
    LinearLayout layout = v.findViewById(R.id.course_adapter_layout);
    layout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.i(TAG, "List view element has been clicked " + course_code);

        // expand the view to include a new fragment

        // navigate to the view_course_page for the class clicked on
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        view_course_page view_course_frag = view_course_page.newInstance(getItem(position));
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, view_course_frag).addToBackStack(null).commit();

      }
    });

    return v;
  }
}




















