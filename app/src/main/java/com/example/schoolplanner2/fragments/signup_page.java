package com.example.schoolplanner2.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.models.Student;
import com.google.gson.Gson;

import java.io.FileOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signup_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signup_page extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public signup_page() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment signup_page.
   */
  // TODO: Rename and change types and number of parameters
  public static signup_page newInstance(String param1, String param2) {
    signup_page fragment = new signup_page();
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
    View v = inflater.inflate(R.layout.fragment_signup_page, container, false);

    // on submit, get info from fields
    final Button signup_page_submit_button = v.findViewById(R.id.signup_page_submit_button);
    signup_page_submit_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Student newStudent = null;

        // get the info and parse it
        EditText yearEditText = (EditText) v.findViewById(R.id.input_current_year);
        Integer inputCurrentYear = Integer.parseInt( yearEditText.getText().toString() );

        EditText programEditText = (EditText) v.findViewById(R.id.input_program_name);
        String inputProgramName = programEditText.getText().toString();

        try{
          // make a student class out of it
          newStudent = new Student(inputProgramName, 2);

          String file_name = "studentInfo.txt";
          String file_data = "some stuff is here";

          // get context
          Context context = getContext();

          // turn all the student data to a GSON string
          // first check if the student class has actually been created
          if( newStudent != null ){
            // if exists, get its data and GSON it
            Gson gson = new Gson();
            file_data = gson.toJson(newStudent);

            // then write it to file
            FileOutputStream fos = context.openFileOutput(file_name, Context.MODE_PRIVATE);
            fos.write(file_data.getBytes());
          }

        } catch(Exception e){
          e.printStackTrace();
        }
      }
    });

    return v;
  }
}