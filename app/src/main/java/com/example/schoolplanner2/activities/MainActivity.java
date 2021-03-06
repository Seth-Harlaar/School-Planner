package com.example.schoolplanner2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.example.schoolplanner2.R;
import com.example.schoolplanner2.models.Student;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

  private Student student = null;

  // on create load data
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // load data from storage
    // get context
    Context context = MainActivity.this;

    String file_name = "studentInfo2.txt";

    // try open the file
    FileInputStream fis = null;
    try {
      fis = context.openFileInput(file_name);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      // file not found, must make it
      String file_data = "nothing";

      // get file and write to it
      try{
        FileOutputStream fos = context.openFileOutput(file_name, Context.MODE_PRIVATE);
        fos.write(file_data.getBytes());
        fos.close();
      } catch(Exception e2) {
        e.printStackTrace();
      }
    } finally {
      try {
        fis = context.openFileInput(file_name);
      } catch(FileNotFoundException e){
        e.printStackTrace();
      }
    }

    InputStreamReader inputStreamReader;

    // open stream
    if( fis != null ){
      inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
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

      Student newStudent = null;

      try{
        // attempt to make a new student
        Gson gson = new Gson();
        newStudent = gson.fromJson(contents, Student.class);

        this.student = newStudent;
      } catch(JsonSyntaxException e){
        e.printStackTrace();
        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHost.getNavController();
        navController.navigate(R.id.signup_page);
      } finally {
        // if there is still no student
        if(newStudent == null){
          NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
          NavController navController = navHost.getNavController();
          navController.navigate(R.id.signup_page);
        }
      }
    }
  }


  @Override
  protected void onStop(){
    super.onStop();
    // save data
    String file_name = "studentInfo2.txt";
    String file_data = "some stuff is here";

    // get context
    Context context = MainActivity.this;

    // turn all the student data to a GSON string
    // first check if the student class has actually been created
    if( student != null ){
      // if exists, get its data and GSON it
      Gson gson = new Gson();
      file_data = gson.toJson(student);

      // then write it to file
      try{
        FileOutputStream fos = context.openFileOutput(file_name, Context.MODE_PRIVATE);
        fos.write(file_data.getBytes());
      } catch(Exception e) {
        // problem writing student to file
        e.printStackTrace();
      }
    }
  }

  public Student getStudent(){
    return student;
  }
}











