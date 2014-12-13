package com.alfy.wccr.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Author: Alan Created: 12/12/2014.
 */

@XmlRootElement
public class FoodItem {
  private long id;
  private String item;
  private double gross;
  private double tare;
  private double net;
  private double calg;
  private double cal;
  private String date;
  private String user;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public double getGross() {
    return gross;
  }

  public void setGross(double gross) {
    this.gross = gross;
  }

  public double getTare() {
    return tare;
  }

  public void setTare(double tare) {
    this.tare = tare;
  }

  public double getNet() {
    return net;
  }

  public void setNet(double net) {
    this.net = net;
  }

  public double getCalg() {
    return calg;
  }

  public void setCalg(double calg) {
    this.calg = calg;
  }

  public double getCal() {
    return cal;
  }

  public void setCal(double cal) {
    this.cal = cal;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }
}
