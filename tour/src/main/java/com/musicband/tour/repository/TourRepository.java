package com.musicband.tour.repository;

import com.musicband.tour.entity.Tour;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    Optional<Tour> findByTitle(String title);

//    @Transactional
//    @Modifying
//    void deleteByTitle(String title);
}
