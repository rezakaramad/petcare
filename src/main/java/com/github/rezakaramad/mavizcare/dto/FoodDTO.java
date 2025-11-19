package com.github.rezakaramad.mavizcare.dto;

/**
 * DTO for creating or updating a Food.
 *
 * <p>This avoids exposing the JPA entity directly to the web layer.
 */
public class FoodDTO {

  private String name;
  private String brand;
  private String category;

  public FoodDTO() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
