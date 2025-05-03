package com.musicband.merch.repository;

import com.musicband.merch.entity.Merch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchRepository extends JpaRepository<Merch, Long> {
    Optional<Merch> findByTitle(String title);
}
