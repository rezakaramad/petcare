package com.github.rezakaramad.petcare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling requests to the root URL of the application.
 *
 * <p>This class manages the main entry point of the web application. It handles GET requests to the
 * root URL ("/") and prepares the data needed for rendering the home page.
 *
 * <p>The method in this controller adds attributes to the model, which are then used in the
 * Thymeleaf template to display dynamic content on the home page.
 *
 * @see org.springframework.ui.Model
 */
@Controller
public class PetCareController {

  /**
   * Handles requests to the home page ("/").
   *
   * <p>This method processes GET requests to the root URL ("/"). It adds two attributes to the
   * model: a message and a title, which are displayed on the home page.
   *
   * @param model The model to which the message and title are added for rendering the view.
   * @return The name of the "index" template to be rendered as the home page.
   */
  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("message", "Feature: Alpha");
    model.addAttribute("title", "Home Page");
    return "index"; // Return the template name
  }
}
