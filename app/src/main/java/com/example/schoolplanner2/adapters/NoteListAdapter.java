package com.example.schoolplanner2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.models.Note;

import java.util.List;

public class NoteListAdapter extends ArrayAdapter<Note> {
  private static final String TAG = "NoteListAdapter";

  private Context context;
  int mResource;

  public NoteListAdapter(@NonNull Context context, int resource, @NonNull List<Note> objects) {
    super(context, resource, objects);
    this.context = context;
    this.mResource = resource;
  }

  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    // make inflater and inflate the layout
    LayoutInflater inflater = LayoutInflater.from(context);
    View v = inflater.inflate(mResource, parent, false);

    // count, title, word count
    TextView countTextView = v.findViewById(R.id.note_adapter_count);
    TextView titleTextView = v.findViewById(R.id.note_adapter_title);
    TextView wordsTextView = v.findViewById(R.id.note_adapter_words);

    countTextView.setText(String.valueOf(position));
    titleTextView.setText(getItem(position).getTitle());
    wordsTextView.setText(String.valueOf(getItem(position).getWordCount()));

    return v;
  }
}
