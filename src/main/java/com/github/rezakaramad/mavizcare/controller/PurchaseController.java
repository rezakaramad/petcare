package com.github.rezakaramad.mavizcare.controller;

import com.github.rezakaramad.mavizcare.dto.PurchaseDTO;
import com.github.rezakaramad.mavizcare.entity.Purchase;
import com.github.rezakaramad.mavizcare.repository.FoodRepository;
import com.github.rezakaramad.mavizcare.repository.ProfileRepository;
import com.github.rezakaramad.mavizcare.repository.PurchaseRepository;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for managing purchases.
 *
 * @see Purchase
 * @see PurchaseRepository
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseController {

  private final PurchaseRepository purchaseRepository;
  private final FoodRepository foodRepository;
  private final ProfileRepository profileRepository;

  /**
   * PurchaseController class.
   */
  public PurchaseController(
      PurchaseRepository purchaseRepository,
      FoodRepository foodRepository,
      ProfileRepository profileRepository) {

    this.purchaseRepository = Objects.requireNonNull(purchaseRepository);
    this.foodRepository = Objects.requireNonNull(foodRepository);
    this.profileRepository = Objects.requireNonNull(profileRepository);
  }

  /** GET /purchase/list Displays all purchases. */
  @GetMapping("/list")
  public String listPurchases(Model model) {
    model.addAttribute("purchases", purchaseRepository.findAllByOrderByPurchaseDateDesc());
    return "purchase-list";
  }

  /**
   * GET /purchase/add Shows the form for creating a new purchase.
   *
   * <p>Loads profiles + foods for dropdowns.
   */
  @GetMapping("/add")
  public String showAddForm(Model model) {
    model.addAttribute("purchaseDTO", new PurchaseDTO());
    model.addAttribute("foods", foodRepository.findAll());
    model.addAttribute("profiles", profileRepository.findAll());
    return "purchase-add";
  }

  /** POST /purchase/add Saves the submitted purchase into the database. */
  @PostMapping("/add")
  public String savePurchase(PurchaseDTO dto) {

    var food =
        foodRepository
            .findById(dto.getFoodId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid food"));

    var profile =
        profileRepository
            .findById(dto.getProfileId())
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid profile"));

    Purchase purchase = new Purchase();
    purchase.setFood(food);
    purchase.setProfile(profile);
    purchase.setPurchaseDate(dto.getPurchaseDate());
    purchase.setQuantity(dto.getQuantity());
    purchase.setPrice(dto.getPrice());

    purchaseRepository.save(purchase);
    return "redirect:/purchase/list";
  }

  /** GET /purchase/{id} Displays a specific purchase. */
  @GetMapping("/{id}")
  public String viewPurchase(@PathVariable Long id, Model model) {
    Purchase purchase =
        purchaseRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase not found"));

    model.addAttribute("purchase", purchase);
    return "purchase-details";
  }

  /** POST /purchase/delete Deletes a purchase by ID. */
  @PostMapping("/delete")
  public String deletePurchase(Long id) {
    purchaseRepository.deleteById(id);
    return "redirect:/purchase/list";
  }
}
