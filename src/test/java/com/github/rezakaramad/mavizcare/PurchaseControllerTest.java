package com.github.rezakaramad.mavizcare;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.github.rezakaramad.mavizcare.controller.PurchaseController;
import com.github.rezakaramad.mavizcare.entity.Food;
import com.github.rezakaramad.mavizcare.entity.Profile;
import com.github.rezakaramad.mavizcare.entity.Purchase;
import com.github.rezakaramad.mavizcare.repository.FoodRepository;
import com.github.rezakaramad.mavizcare.repository.ProfileRepository;
import com.github.rezakaramad.mavizcare.repository.PurchaseRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(
    controllers = PurchaseController.class,
    excludeAutoConfiguration = {
      org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class
    })
class PurchaseControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private PurchaseRepository purchaseRepository;

  @MockBean private FoodRepository foodRepository;

  @MockBean private ProfileRepository profileRepository;

  // GET /purchase/list
  @Test
  void testListPurchases() throws Exception {
    List<Purchase> mockList = List.of(
        new Purchase(
            new Profile("John"),
            new Food("Apple", "Brand", "Fruit"),
            LocalDate.now(),
            1,
            BigDecimal.TEN,
            "Store")
    );

    org.mockito.Mockito.when(purchaseRepository.findAllByOrderByPurchaseDateDesc())
        .thenReturn(mockList);

    mockMvc
        .perform(get("/purchase/list"))
        .andExpect(status().isOk())
        .andExpect(view().name("purchase-list"))
        .andExpect(model().attribute("purchases", mockList));
  }

  // GET /purchase/add
  @Test
  void testShowAddForm() throws Exception {
    org.mockito.Mockito.when(foodRepository.findAll()).thenReturn(List.of());
    org.mockito.Mockito.when(profileRepository.findAll()).thenReturn(List.of());

    mockMvc
        .perform(get("/purchase/add"))
        .andExpect(status().isOk())
        .andExpect(view().name("purchase-add"))
        .andExpect(model().attributeExists("purchaseDTO"))
        .andExpect(model().attributeExists("foods"))
        .andExpect(model().attributeExists("profiles"));
  }

  // POST /purchase/add
  @Test
  void testSavePurchase() throws Exception {

    org.mockito.Mockito.when(foodRepository.findById(1L))
        .thenReturn(Optional.of(new Food("Apple", "Brand", "Fruit")));

    org.mockito.Mockito.when(profileRepository.findById(2L))
        .thenReturn(Optional.of(new Profile("John")));

    mockMvc
        .perform(
            post("/purchase/add")
                .param("foodId", "1")
                .param("profileId", "2")
                .param("purchaseDate", "2025-01-10")
                .param("quantity", "3")
                .param("price", "4.99")
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/purchase/list"));

    org.mockito.Mockito.verify(purchaseRepository)
        .save(org.mockito.Mockito.any(Purchase.class));
  }

  // GET /purchase/{id}
  @Test
  void testViewPurchase() throws Exception {
    Purchase mockPurchase = new Purchase();
    org.mockito.Mockito.when(purchaseRepository.findById(1L))
        .thenReturn(Optional.of(mockPurchase));

    mockMvc
        .perform(get("/purchase/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("purchase-details"))
        .andExpect(model().attribute("purchase", mockPurchase));
  }

  // GET /purchase/{id} NOT FOUND
  @Test
  void testViewPurchase_NotFound() throws Exception {
    org.mockito.Mockito.when(purchaseRepository.findById(1L))
        .thenReturn(Optional.empty());

    mockMvc.perform(get("/purchase/1"))
        .andExpect(status().isNotFound());
  }

  // POST /purchase/delete
  @Test
  void testDeletePurchase() throws Exception {
    mockMvc
        .perform(post("/purchase/delete").param("id", "5"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/purchase/list"));

    org.mockito.Mockito.verify(purchaseRepository).deleteById(5L);
  }
}
