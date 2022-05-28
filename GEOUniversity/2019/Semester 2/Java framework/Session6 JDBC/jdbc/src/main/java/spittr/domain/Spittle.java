package main.java.spittr.domain;

import java.util.Date;

public class Spittle {
  private final Long id;
  private final Spitter spitter;
  private final String message;
  private final Date postedTime;

  public Spittle(Long id, Spitter spitter, String message, Date postedTime) {
    this.id = id;
    this.spitter = spitter;
    this.message = message;
    this.postedTime = postedTime;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public Date getPostedTime() {
    return this.postedTime;
  }

  public Spitter getSpitter() {
    return this.spitter;
  }

}
