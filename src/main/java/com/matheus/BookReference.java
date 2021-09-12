package com.matheus;

public class BookReference {

  private final String key;
  private final String name;

  public BookReference(String key, String name) {
    this.key = key;
    this.name = name;
  }

  public String getKey() {
    return key;
  }

  public String getName() {
    return name;
  }
}
