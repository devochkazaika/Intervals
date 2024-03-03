package com.intervals.Service;

import com.intervals.Model;
import org.springframework.boot.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntervalseRepository extends JpaRepository<Model, Long> {
    Optional<Model> findById(Long id);
}
