package com.example.schoolplanner2.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.schoolplanner2.activities.MainActivity;
import com.example.schoolplanner2.R;
import com.example.schoolplanner2.models.Student;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_page extends Fragment {

  private static final String student_key = "student";

  private Student student;

  public home_page() {
    // Required empty public constructor
  }

  public static home_page newInstance(Student input_student) {
    home_page fragment = new home_page();
    Bundle args = new Bundle();

    args.putParcelable(student_key, input_student);

    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      student = getArguments().getParcelable(student_key);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    // Inflate the layout for this fragment and get the view
    View v = inflater.inflate(R.layout.fragment_home_page, container, false);

    // navigate to chart page
    final Button to_chart_button = v.findViewById(R.id.to_chart_button);
    to_chart_button.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        // execute navigation to different page
        Navigation.findNavController(v).navigate(R.id.chart_page);
      }
    });

    // navigate to grades page
    final Button to_grades_button = v.findViewById(R.id.to_grades_button);
    to_grades_button.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v){
        // got to grades page
        Navigation.findNavController(v).navigate(R.id.grades_page_frag);
      }
    });

    final Button to_topass_button = v.findViewById(R.id.to_topass_button);
    to_topass_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // go to the to pass page
        Navigation.findNavController(v).navigate(R.id.topass_page);
      }
    });

    final Button to_notes_button = v.findViewById(R.id.to_notes_button);
    to_notes_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        view_notes_page view_notes_frag = view_notes_page.newInstance(student);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, view_notes_frag).addToBackStack(null).commit();
      }
    });

    // save student data
    final Button save_data_button = v.findViewById(R.id.save_data_button);
    save_data_button.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v){

        String file_name = "studentInfo.txt";
        String file_data = "some stuff is here";

        // get context
        Context context = getContext();

        MainActivity activity = (MainActivity) getActivity();
        Student student = activity.getStudent();

        // turn all the student data to a GSON string
        // first check if the student class has actually been created
        if( student != null ) {
          // if exists, get its data and GSON it
          Gson gson = new Gson();
          file_data = gson.toJson(student);
        }
        // then write it to file
        try{
          FileOutputStream fos = context.openFileOutput(file_name, Context.MODE_PRIVATE);
          fos.write(file_data.getBytes());
        } catch(Exception e) {
          // problem writing student to file
        }
      }
    });


    // read data test
    final Button read_data_button = v.findViewById(R.id.read_data_button);
    read_data_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String file_name = "studentInfo2.txt";

        // get context
        Context context = getContext();

        // load the data
        // try open the file
        FileInputStream fis = null;
        try {
          fis = context.openFileInput(file_name);
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }

        // open stream
        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        // start new string builder to hold all the data
        StringBuilder stringBuilder = new StringBuilder();
        String contents = "nothing";
        try{
          // open buffer reader
          BufferedReader reader = new BufferedReader(inputStreamReader);
          String line = reader.readLine();
          // begin reading in each line
          while (line != null) {
            stringBuilder.append(line).append('\n');
            line = reader.readLine();
          }
        } catch (IOException e) {
          // Error occurred when opening raw file for reading.
        } finally {
          contents = stringBuilder.toString();
        }

        // set the text view to the data
        final TextView data_spot = v.findViewById(R.id.textView4);
        data_spot.setText(contents);

      }
    });


    return v;
  }
}