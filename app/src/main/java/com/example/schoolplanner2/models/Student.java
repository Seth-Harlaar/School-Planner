package com.example.schoolplanner2.models;

import com.example.schoolplanner2.models.Course;

import java.util.ArrayList;


public class Student {

  // a list of classes they're in
  private ArrayList<Course> courses = new ArrayList<Course>();

  // program name
  private String programName;

  // current year
  private Integer currentYear;

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
}
