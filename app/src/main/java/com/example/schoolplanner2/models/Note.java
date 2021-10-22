package com.example.schoolplanner2.models;

public class Note {
  // mandatory for input
  String title;
  String body;
  //  comes from function
  Integer wordCount;

  // date created
  // date last edited

  public Note(String inputTitle, String inputBody){
    super();
    this.title = inputTitle;
    this.body = inputBody;

    if( inputBody == null || inputBody.isEmpty()){
      this.wordCount = 0;
    } else {
      String[] words = inputBody.split("\\s+");
      this.wordCount = words.length;
    }
  }

  // getters and setters
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Integer getWordCount() {
    return wordCount;
  }

  public void setWordCount(Integer wordCount) {
    this.wordCount = wordCount;
  }
}

