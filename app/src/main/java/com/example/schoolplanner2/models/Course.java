package com.example.schoolplanner2.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Course implements Parcelable {

  // for parcelable
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    public Course createFromParcel(Parcel in) {
      return new Course(in);
    }

    public Course[] newArray(int size) {
      return new Course[size];
    }
  };

  // Title
  private String title;

  // Course Code
  private String courseCode;

  // The grade you would get if you submitted no more work and completed no more assessments
  private Double currentGrade;

  // The grade you have, with respect to only the completed work/assessments
  private Double absoluteGrade;

  // grading schematics - different weights and outcomes of work/assessments
  // -- not started --

  // list of assessments for the course
  private ArrayList<Assessment> assessments = new ArrayList<Assessment>();

  // finished assignments for finding grade
  private ArrayList<GradedAssessment> gradedAssessments  = new ArrayList<GradedAssessment>();

  // a course outline file
  // -- figure out when connecting app --

  // schedule - list of due dates from assessments
  // -- figure out when connecting app --

  // constructor
  public Course( String titleInput, String courseCodeInput ){

    // must be course code
    if( courseCodeInput.isEmpty() ){
      throw new IllegalArgumentException( "A course code must be provided " );
    } else {
      this.courseCode = courseCodeInput;
    }

    // title can be empty, if it is display course code
    this.title = titleInput;

    // don't start with average
    this.currentGrade = 0.00;
    this.absoluteGrade = 0.00;
  }

  public Course(Parcel in){
    this.title = in.readString();
    this.currentGrade = in.readDouble();
    this.absoluteGrade = in.readDouble();
    this.assessments = in.readArrayList(Assessment.class.getClassLoader());
    this.gradedAssessments = in.readArrayList(GradedAssessment.class.getClassLoader());
  }


  // getters
  public String getCourseCode(){
    return this.courseCode;
  }

  public String getTitle(){ return this.title; }


  public ArrayList<Assessment> getAssessmentList( ){
    return this.assessments;
  }

  public ArrayList<GradedAssessment> getGradedAssessmentList(){
    return this.gradedAssessments;
  }

  public Double getCurrentGrade(){
    return this.currentGrade;
  }

  public Double getAbsoluteGrade(){
    return this.absoluteGrade;
  }

  // setters
  public void setCurrentGrade( Double currentGrade ){
    this.currentGrade = currentGrade;
  }

  public void setAbsoluteGrade( Double absoluteGrade ){
    this.absoluteGrade = absoluteGrade;
  }



  // other functions

  // add assessment -> read name
  public void addAssessment( Assessment newAssessment ){

    if( newAssessment != null ){
      assessments.add( newAssessment );
    }
    this.recalculateGrades();
  }

  public void removeAssessment( Assessment oldAssessment ){

    if( oldAssessment != null ){
      assessments.remove( oldAssessment );
    }
    this.recalculateGrades();
  }

  // finishAssessment -> creates a graded assessment with grade, and pointer to the assessment that was finished
  public void finishAssessment( Assessment assessment, Double finalGrade, Integer achScore, Integer maxScore ){

    try {
      GradedAssessment newGradedAssessment = new GradedAssessment( assessment, finalGrade, achScore, maxScore );
      gradedAssessments.add( newGradedAssessment );
      assessments.remove( assessment );
    } catch( Exception e ) {
      System.out.println( e.getMessage() );
    }

    this.recalculateGrades();
  }

  // recalculates the average for the course
  public void recalculateGrades(){
    // Take the weight of each assessment
    Double totalWeightAssess = 0.00;
    Double totalWeightFin = 0.00;

    Double totalGrade = 0.00;

    Double absGrade = 0.00;
    Double curGrade = 0.00;

    for( Assessment assess: assessments ){
      totalWeightAssess += assess.getWeight();
    }

    for( GradedAssessment gradedAssess : gradedAssessments ){
      totalWeightFin += gradedAssess.getWeight();
      totalGrade += gradedAssess.getFinalGrade()*gradedAssess.getWeight();
    }

    totalWeightAssess += totalWeightFin;
    try {
      // current weighted grade
      curGrade = totalGrade/totalWeightFin;

      // absolute grade
      absGrade = totalGrade/totalWeightAssess;
    } catch( Exception e ){
      System.out.println("Error calculating grades\n" + e.getMessage() );
    }

    if( curGrade.isNaN()){
      curGrade = 0.00;
    }

    this.setCurrentGrade( curGrade );
    this.setAbsoluteGrade( absGrade );
  }

  // calculates what grade you need on final to pass the course with your current grade and weight of final
  public Double toPass( Double examWeight, Double targetGrade ){

    Double impactGrade = targetGrade - absoluteGrade;

    if( impactGrade > 0.00){
      impactGrade = 0.00;
    }

    Double requiredGrade = impactGrade/(examWeight/100);

    return requiredGrade;
  }


  // toString
  public String toString(){
    String courseString;

    courseString = "Course:\n";

    // title and course code
    courseString = courseString + "Course Title: " + this.title + "\nCourse Code: " + this.courseCode + "\n";

    // all assessments
    for( Assessment assess: assessments ){
      if( assess.getStatus() != 4 ){

      }
      courseString = courseString + assess.toString();
    }

    // all graded assessments
    for( GradedAssessment assess: gradedAssessments ){
      courseString = courseString + assess.toString();
    }

    return courseString;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {

  }
}
