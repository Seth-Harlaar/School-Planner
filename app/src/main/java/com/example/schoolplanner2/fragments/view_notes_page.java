package com.example.schoolplanner2.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.activities.MainActivity;
import com.example.schoolplanner2.adapters.CourseListAdapter;
import com.example.schoolplanner2.adapters.NoteListAdapter;
import com.example.schoolplanner2.models.Note;
import com.example.schoolplanner2.models.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class view_notes_page extends Fragment {
  private static final String student_key = "student";

  private Student student;

  public view_notes_page() {
    // Required empty public constructor
  }

  public static view_notes_page newInstance(Student input_student) {
    view_notes_page fragment = new view_notes_page();
    Bundle args = new Bundle();

    args.putParcelable(student_key ,input_student);

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
    View v = inflater.inflate(R.layout.fragment_view_notes_page, container, false);

    if( student == null ){
      // get it from activity
      MainActivity activity = (MainActivity) getActivity();
      student = activity.getStudent();
    }
    ArrayList<Note> notes = student.getNotes();

    // make adapter and set it to list view
    ListView notesListView = v.findViewById(R.id.view_notes_list);

    NoteListAdapter noteAdapter = new NoteListAdapter(getContext(), R.layout.note_adapter_view, notes);
    notesListView.setAdapter(noteAdapter);

    // new note button
    final FloatingActionButton new_note_button = v.findViewById(R.id.view_notes_new);
    new_note_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // go to new note page
        AppCompatActivity activity1 = (AppCompatActivity) view.getContext();
        new_note_page new_note_frag = new_note_page.newInstance(student);
        activity1.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new_note_frag).addToBackStack(null).commit();
      }
    });


    return v;
  }
}



















