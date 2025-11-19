package com.github.rezakaramad.mavizcare.repository;

import com.github.rezakaramad.mavizcare.entity.Purchase;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Book} entities.
 *
 * <p>This interface extends {@link JpaRepository}, providing CRUD operations and additional query
 * methods for the {@link Book} entity. The {@link JpaRepository} includes functionality such as
 * pagination, sorting, and more.
 *
 * <p>The {@link Long} type is used as the identifier for the {@link Book} entity.
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
  /** Returns all purchases ordered by purchaseDate (newest first). */
  List<Purchase> findAllByOrderByPurchaseDateDesc();
}
