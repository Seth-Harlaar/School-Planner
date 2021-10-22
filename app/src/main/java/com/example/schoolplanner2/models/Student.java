package com.example.schoolplanner2.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.schoolplanner2.models.Course;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Student implements Parcelable {
  // parcelable implementation
  protected Student(Parcel in) {
    courses = in.createTypedArrayList(Course.CREATOR);
    programName = in.readString();
    if (in.readByte() == 0) {
      currentYear = null;
    } else {
      currentYear = in.readInt();
    }
  }

  public static final Creator<Student> CREATOR = new Creator<Student>() {
    @Override
    public Student createFromParcel(Parcel in) {
      return new Student(in);
    }

    @Override
    public Student[] newArray(int size) {
      return new Student[size];
    }
  };

  // a list of classes they're in
  private ArrayList<Course> courses = new ArrayList<Course>();

  // program name
  private String programName;

  // current year
  private Integer currentYear;

  // list of notes
  private ArrayList<Note> notes = new ArrayList<Note>();

  // event list - upcoming due dates


  // constructors - must have program name, and current year
  public Student( String inputProgramName, Integer inputCurrentYear ){
    // must be programName
    if( inputProgramName.isEmpty() ){
      throw new IllegalArgumentException( "A program name must be provided" );
    } else {
      this.programName = inputProgramName;
    }

    // current year must be 8 or less, and at least one
    if( inputCurrentYear < 1 | inputCurrentYear > 8 ){
      throw new IllegalArgumentException( "Current year must be less than 8 and at least 1" );
    } else {
      this.currentYear = inputCurrentYear;
    }
  }




  // update a note function
  // add a new note
  public void addNote(Note inputNote){
    this.notes.add(inputNote);
  }




  // getters
  public ArrayList<Course> getCourses(){
    return this.courses;
  }

  public String getProgramName(){
    return this.programName;
  }

  public Integer getCurrentYear(){
    return this.currentYear;
  }

  public ArrayList<Note> getNotes() {
    return notes;
  }


  // setters
  public void setCourses( ArrayList<Course> newCourses ){
    this.courses = newCourses;
  }

  public void setProgramName( String newProgramName ){
    this.programName = newProgramName;
  }

  public void setCurrentYear( Integer newCurrentYear ){
    this.currentYear = newCurrentYear;
  }

  public void setNotes(ArrayList<Note> notes) {
    this.notes = notes;
  }

  // parcelable functions

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeTypedList(courses);
    parcel.writeString(programName);
    if (currentYear == null) {
      parcel.writeByte((byte) 0);
    } else {
      parcel.writeByte((byte) 1);
      parcel.writeInt(currentYear);
    }
  }
}
