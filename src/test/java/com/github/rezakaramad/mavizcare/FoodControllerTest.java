package com.github.rezakaramad.mavizcare;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.github.rezakaramad.mavizcare.controller.FoodController;
import com.github.rezakaramad.mavizcare.entity.Food;
import com.github.rezakaramad.mavizcare.repository.FoodRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

@WebMvcTest(
    controllers = FoodController.class,
    excludeAutoConfiguration = {
      org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class
    })
class FoodControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private FoodRepository foodRepository;

  // GET /food/list
  @Test
  void testListFoods() throws Exception {
    List<Food> mockFoods = List.of(new Food("Apple", "BrandA", "Fruit"));
    org.mockito.Mockito.when(foodRepository.findAll()).thenReturn(mockFoods);

    mockMvc
        .perform(get("/food/list"))
        .andExpect(status().isOk())
        .andExpect(view().name("food/list"))
        .andExpect(model().attribute("foods", mockFoods));
  }

  // GET /food/add
  @Test
  void testShowAddForm() throws Exception {
    mockMvc
        .perform(get("/food/add"))
        .andExpect(status().isOk())
        .andExpect(view().name("food/add"))
        .andExpect(model().attributeExists("foodDTO")); // FIXED
  }

  // POST /food/add
  @Test
  void testSaveFood() throws Exception {
    mockMvc
        .perform(
            post("/food/add")
                .param("name", "Apple")
                .param("brand", "BrandA")
                .param("category", "Fruit"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/food/list"));

    org.mockito.Mockito.verify(foodRepository).save(org.mockito.Mockito.any(Food.class));
  }

  // GET /food/{id}
  @Test
  void testViewFood() throws Exception {
    Food mockFood = new Food("Apple", "BrandA", "Fruit");
    org.mockito.Mockito.when(foodRepository.findById(1L))
        .thenReturn(Optional.of(mockFood));

    mockMvc
        .perform(get("/food/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("food/details"))
        .andExpect(model().attribute("food", mockFood));
  }

  // GET /food/{id} not found
  @Test
  void testViewFood_NotFound() throws Exception {
    org.mockito.Mockito.when(foodRepository.findById(1L))
        .thenReturn(Optional.empty());

    mockMvc.perform(get("/food/1"))
            .andExpect(status().isNotFound());
  }

  // POST /food/delete
  @Test
  void testDeleteFood() throws Exception {
    mockMvc
        .perform(post("/food/delete").param("id", "5"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/food/list"));

    org.mockito.Mockito.verify(foodRepository).deleteById(5L);
  }
}
