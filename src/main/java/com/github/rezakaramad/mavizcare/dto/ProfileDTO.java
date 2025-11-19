package com.github.rezakaramad.mavizcare.dto;

/**
 * DTO for creating or updating a Profile.
 *
 * <p>This avoids exposing the JPA entity directly to the web layer.
 */
public class ProfileDTO {

  private Long id;
  private String name;

  public ProfileDTO() {}

  public ProfileDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
