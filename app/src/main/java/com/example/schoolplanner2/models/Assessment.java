package com.example.schoolplanner2.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;


public class Assessment implements Parcelable {

  // mandatory
  private String title;
  // optional,  talk about what have to do
  private String description;
  // mandatory, 0 -> 100
  private Integer weight;

  // used for the * what to complete chart *
  // optional, 1 -> 4
  // based on the date
  private Integer urgency;
  // optional, 1 -> 4
  private Integer importance;

  // mandatory, finished, working on, need to start, almost done
  private Integer status;
  // mandatory, test, assignment, or lab
  private Integer assessmentType;
  // optional, any date
  private LocalDate dueDate;

  // constructor
  // so compiler is happy
  public Assessment(){

  }

  public Assessment(String title, Integer weight, Integer status, Integer assessmentType){
    this.title = title;
    this.weight = weight;
    this.status = status;
    this.assessmentType = assessmentType;
    this.description = "";
    this.urgency = 0;
    this.importance = 0;
    this.dueDate = null;
  }


  public Assessment(String title, String description, Integer weight, Integer urgency, Integer importance, Integer status, Integer assessmentType, String date ){
    // must be a title string
    if(title.isEmpty()){
      throw new IllegalArgumentException("A title is required when making a new assessment");
    } else {
      this.title = title;
    }

    // description can be empty, use empty string
    if(description.isEmpty()){
      this.description = "";
    } else {
      this.description = description;
    }

    // weight can be a number form 0->100, required
    if(weight == 0){
      throw new IllegalArgumentException("A weight value is required");
    } else {
      if( weight > 100 || weight < 1){
        throw new IllegalArgumentException("Weight value must be from 0 to 100");
      } else {
        this.weight = weight;
      }
    }


    // urgency can be from 1 to 6
    if( urgency > 6 || urgency < 1){
      throw new IllegalArgumentException("Urgency must be a value from 1 to 6");
    } else {
      this.urgency = urgency;
    }

    // importance can be from 1 to 4
    if( importance > 4 || importance < 1){
      throw new IllegalArgumentException("Importance must be a value from 1 to 6");
    } else {
      this.importance = importance;
    }

    // status
    // 4 -> finished   3 -> almost done   2 -> working on   1 -> need to start
    if( status == 0 ){
      throw new IllegalArgumentException("A status is required");
    } else {
      if( status > 4 || status < 1){
        throw new IllegalArgumentException("Status must be a value from 1 to 4");
      } else {
        this.status = status;
      }
    }


    // assessmentType   3 -> test   2 -> assignment  1 -> lab
    if( assessmentType == 0){
      throw new IllegalArgumentException("An assessment type is required");
    } else {
      if( assessmentType > 3 || assessmentType < 1 ){
        throw new IllegalArgumentException("Assessment type must be examination, assignment, or lab");
      } else {
        this.assessmentType = assessmentType;
      }
    }

    if( date.isEmpty() ){
      this.dueDate = null;
    } else {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.dueDate = LocalDate.parse( date );
      }
    }
  }

  protected Assessment(Parcel in) {
    title = in.readString();
    description = in.readString();
    if (in.readByte() == 0) {
      weight = null;
    } else {
      weight = in.readInt();
    }
    if (in.readByte() == 0) {
      urgency = null;
    } else {
      urgency = in.readInt();
    }
    if (in.readByte() == 0) {
      importance = null;
    } else {
      importance = in.readInt();
    }
    if (in.readByte() == 0) {
      status = null;
    } else {
      status = in.readInt();
    }
    if (in.readByte() == 0) {
      assessmentType = null;
    } else {
      assessmentType = in.readInt();
    }
  }

  public static final Creator<Assessment> CREATOR = new Creator<Assessment>() {
    @Override
    public Assessment createFromParcel(Parcel in) {
      return new Assessment(in);
    }

    @Override
    public Assessment[] newArray(int size) {
      return new Assessment[size];
    }
  };

  // getters
  public String getTitle(){
    return this.title;
  }

  public String getDescription(){
    return this.description;
  }

  public Integer getWeight(){
    return this.weight;
  }

  public Integer getUrgency(){
    return this.urgency;
  }

  public Integer getImportance(){
    return this.importance;
  }

  public Integer getStatus(){
    return this.status;
  }

  public Integer getAssessmentType(){
    return this.assessmentType;
  }

  public LocalDate getDueDate(){
    return this.dueDate;
  }

  // setters
  public void setTitle( String titleString ){
    this.title = titleString;
  }

  public void setDescription( String descriptionString ){
    this.description = descriptionString ;
  }

  public void setWeight( Integer weight ){
    this.weight = weight;
  }

  public void setUrgency( Integer urgency ){
    this.urgency = urgency;
  }

  public void setImportance( Integer importance ){
    this.importance = importance;
  }

  public void setStatus( Integer status ){
    this.status = status;
  }

  public void setAssessmentType( Integer assessmentType ){
    this.assessmentType = assessmentType;
  }

  public void setDueDate( String date ){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      this.dueDate = LocalDate.parse( date );
    }
  }

  public void setDueDate( LocalDate date ){
    this.dueDate = date;
  }
  // others
  // update weight -> change the weight of a gradedAssessment


  // toString
  // mandatory, finished, working on, need to start, almost done

  public String toString(){
    String[] type = { "Test: ", "Assignment: ", "Lab: " };

    String[] status = { "Need to Start", "Working On", "Almost Done", "Finished" };

    if( dueDate != null ){
      return type[ this.getAssessmentType() - 1 ] + "\n  Title: " + this.getTitle() + "\n  Description: " + this.getDescription() + "\n  Status: " + status[ this.getStatus() - 1 ] + "\n  Due on: " + this.getDueDate() + "\n";
    } else {
      return type[ this.getAssessmentType() - 1 ] + "\n  Title: " + this.getTitle() + "\n  Description: " + this.getDescription() + "\n  Status: " + status[ this.getStatus() - 1 ] + "\n  No due date set\n";
    }

  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(title);
    parcel.writeString(description);
    if (weight == null) {
      parcel.writeByte((byte) 0);
    } else {
      parcel.writeByte((byte) 1);
      parcel.writeInt(weight);
    }
    if (urgency == null) {
      parcel.writeByte((byte) 0);
    } else {
      parcel.writeByte((byte) 1);
      parcel.writeInt(urgency);
    }
    if (importance == null) {
      parcel.writeByte((byte) 0);
    } else {
      parcel.writeByte((byte) 1);
      parcel.writeInt(importance);
    }
    if (status == null) {
      parcel.writeByte((byte) 0);
    } else {
      parcel.writeByte((byte) 1);
      parcel.writeInt(status);
    }
    if (assessmentType == null) {
      parcel.writeByte((byte) 0);
    } else {
      parcel.writeByte((byte) 1);
      parcel.writeInt(assessmentType);
    }
  }
}
