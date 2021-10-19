package com.example.schoolplanner2.models;


import com.example.schoolplanner2.models.Assessment;

public class GradedAssessment extends Assessment {
  private Double finalGrade;
  private Integer achScore;
  private Integer maxScore;

  // constructor
  public GradedAssessment(Assessment assessment, Double finalGrade, Integer achScore, Integer maxScore){

    this.setTitle( assessment.getTitle() );
    this.setAssessmentType( assessment.getAssessmentType() );
    this.setDescription( assessment.getDescription() );
    this.setImportance( assessment.getImportance() );
    this.setStatus( 4 );
    this.setUrgency( assessment.getUrgency() );
    this.setWeight( assessment.getWeight() );
    this.setDueDate( assessment.getDueDate() );

    if( finalGrade == -1){
      if( achScore == 0 || maxScore == 0){
        throw new IllegalArgumentException("A final grade or achieved and max scores must be present");
      }
      this.finalGrade = (double) achScore/maxScore;
    } else {
      this.finalGrade = finalGrade;
    }
  }

  public Integer getAchScore() {
    return achScore;
  }

  public Double getFinalGrade() {
    return finalGrade;
  }

  public Integer getMaxScore() {
    return maxScore;
  }

  // other functions
  // update grade -> change the grade of a gradedassessment
  public String toString(){
    return "Graded Assessment:\n" + super.toString() + "  Final Score: " + String.format( "%.2f", this.getFinalGrade()*100) + "\n";
  }
}
