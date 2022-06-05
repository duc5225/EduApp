package com.example.eduapp.base;

public class FilterParam {
  public boolean toan = true;
  public boolean van = true;
  public boolean anh = true;
  public boolean ly = true;
  public boolean hoa = true;
  public boolean cntt = true;
  public int city = 0;
  public int scope = 0;

  public FilterParam() {
  }

  public FilterParam(boolean toan, boolean van, boolean anh, boolean ly, boolean hoa, boolean cntt, int city, int scope) {
    this.toan = toan;
    this.van = van;
    this.anh = anh;
    this.ly = ly;
    this.hoa = hoa;
    this.cntt = cntt;
    this.city = city;
    this.scope = scope;
  }
}
