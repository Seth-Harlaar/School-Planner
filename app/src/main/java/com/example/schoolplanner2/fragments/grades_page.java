package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.schoolplanner2.activities.MainActivity;
import com.example.schoolplanner2.R;
import com.example.schoolplanner2.models.Student;
import com.example.schoolplanner2.adapters.CourseListAdapter;
import com.example.schoolplanner2.models.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link grades_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class grades_page extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public grades_page() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment grades_page_frag.
   */
  // TODO: Rename and change types and number of parameters
  public static grades_page newInstance(String param1, String param2) {
    grades_page fragment = new grades_page();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_grades_page, container, false);

    // *** button setup ***

    // add another class button
    final FloatingActionButton grades_add_class_button2 = v.findViewById(R.id.grades_add_class2);
    grades_add_class_button2.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        // go to new class form
        Navigation.findNavController(v).navigate(R.id.new_class_page);
      }
    });



    // get each course
    MainActivity activity = (MainActivity) getActivity();
    Student student = activity.getStudent();
    ArrayList<Course> courses = student.getCourses();


    // get the list view and add each course to the course view
    ListView courses_list_view = v.findViewById(R.id.grades_page_list);

    CourseListAdapter courseAdapter = new CourseListAdapter(getContext(), R.layout.course_adapter_view, courses );
    courses_list_view.setAdapter(courseAdapter);



    return v;
  }
}




















