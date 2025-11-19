package com.github.rezakaramad.mavizcare.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * JPA entity representing a purchase item.
 */
@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@Entity
@Table(name = "purchases")
public class Purchase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "profile_id", nullable = false)
  private Profile profile;

  @ManyToOne
  @JoinColumn(name = "food_id", nullable = false)
  private Food food;

  @Column(name = "purchase_date", nullable = false)
  private LocalDate purchaseDate;

  private Integer quantity;
  private BigDecimal price;
  private String store;

  public Purchase() {}

  /**
   * Purchase class.
   */
  public Purchase(
      Profile profile,
      Food food,
      LocalDate purchaseDate,
      Integer quantity,
      BigDecimal price,
      String store) {
    this.profile = profile;
    this.food = food;
    this.purchaseDate = purchaseDate;
    this.quantity = quantity;
    this.price = price;
    this.store = store;
  }

  // Getters and setters

  public Long getId() {
    return id;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public Food getFood() {
    return food;
  }

  public void setFood(Food food) {
    this.food = food;
  }

  public LocalDate getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getStore() {
    return store;
  }

  public void setStore(String store) {
    this.store = store;
  }
}
