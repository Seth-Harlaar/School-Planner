package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.models.Student;
import com.example.schoolplanner2.activities.MainActivity;
import com.example.schoolplanner2.models.Course;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link new_class_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class new_class_page extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public new_class_page() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment new_class_page.
   */
  // TODO: Rename and change types and number of parameters
  public static new_class_page newInstance(String param1, String param2) {
    new_class_page fragment = new new_class_page();
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
    View v = inflater.inflate(R.layout.fragment_new_class_page, container, false);

    // submit new class form
    final Button submit_new_class_form_button = v.findViewById(R.id.new_class_form_submit);
    submit_new_class_form_button.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        // get info
        EditText titleEditText = (EditText) v.findViewById(R.id.new_class_form_title);
        EditText codeEditText = (EditText) v.findViewById(R.id.new_class_form_code);

        String inputCourseTitle = titleEditText.getText().toString();
        String inputCourseCode = codeEditText.getText().toString();

        // make new course
        Course newCourse = new Course(inputCourseTitle, inputCourseCode );

        // add it to the student class
        MainActivity activity = (MainActivity) getActivity();
        Student student = activity.getStudent();
        if( student != null ){
          ArrayList<Course> courses = student.getCourses();
          if( courses != null ){
            courses.add(newCourse);
          }
        }
      }
    });

    return v;
  }
}