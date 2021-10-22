package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.models.Note;
import com.example.schoolplanner2.models.Student;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link new_note_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class new_note_page extends Fragment {

  private static final String student_key = "student";

  private Student student;

  public new_note_page() {
    // Required empty public constructor
  }

  public static new_note_page newInstance(Student input_student) {
    new_note_page fragment = new new_note_page();
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
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_new_note_page, container, false);


    final Button new_note_submit = v.findViewById(R.id.new_note_submit);
    new_note_submit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // get form info
        EditText titleEditText = v.findViewById(R.id.new_note_title);
        EditText bodyEditText = v.findViewById(R.id.new_note_body);

        String input_title = titleEditText.getText().toString();
        String input_body = bodyEditText.getText().toString();

        // make new note
        Note new_note = new Note(input_title, input_body);

        // add to student obj
        student.addNote(new_note);

        // return to notes page
        AppCompatActivity activity1 = (AppCompatActivity) view.getContext();
        view_notes_page view_note_frag = view_notes_page.newInstance(student);
        activity1.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, view_note_frag).addToBackStack(null).commit();
      }
    });


    return v;
  }
}


















