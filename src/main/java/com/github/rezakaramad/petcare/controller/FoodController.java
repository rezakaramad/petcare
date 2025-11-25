package com.github.rezakaramad.petcare.controller;

import com.github.rezakaramad.petcare.dto.FoodDTO;
import com.github.rezakaramad.petcare.entity.Food;
import com.github.rezakaramad.petcare.repository.FoodRepository;
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
 * Controller for managing food.
 */
@Controller
@RequestMapping("/food")
public class FoodController {

  private final FoodRepository foodRepository;

  public FoodController(FoodRepository foodRepository) {
    this.foodRepository = Objects.requireNonNull(foodRepository);
  }

  /** GET /food/list — Displays all foods. */
  @GetMapping("/list")
  public String listFoods(Model model) {
    model.addAttribute("foods", foodRepository.findAll());
    return "food/list";
  }

  /** GET /food/add — Show form for creating a new food. */
  @GetMapping("/add")
  public String showAddForm(Model model) {
    model.addAttribute("foodDTO", new FoodDTO());
    return "food/add";
  }

  /** POST /food/add — Save new food using DTO. */
  @PostMapping("/add")
  public String saveFood(FoodDTO dto) {

    Food food = new Food();
    food.setName(dto.getName());
    food.setBrand(dto.getBrand());
    food.setCategory(dto.getCategory());

    foodRepository.save(food);
    return "redirect:/food/list";
  }

  /** GET /food/{id} — Displays details of one food item. */
  @GetMapping("/{id}")
  public String viewFood(@PathVariable Long id, Model model) {
    Food food = foodRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found"));
    model.addAttribute("food", food);
    return "food/details";
  }

  /** POST /food/delete — Deletes a food by ID. */
  @PostMapping("/delete")
  public String deleteFood(Long id) {
    foodRepository.deleteById(id);
    return "redirect:/food/list";
  }
}
