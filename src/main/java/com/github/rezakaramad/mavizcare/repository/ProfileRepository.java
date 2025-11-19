package com.github.rezakaramad.mavizcare.repository;

import com.github.rezakaramad.mavizcare.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Book} entities.
 *
 * <p>This interface extends {@link JpaRepository}, providing CRUD operations and additional query
 * methods for the {@link Profile} entity. The {@link JpaRepository} includes functionality such as
 * pagination, sorting, and more.
 *
 * <p>The {@link Long} type is used as the identifier for the {@link Book} entity.
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {}
