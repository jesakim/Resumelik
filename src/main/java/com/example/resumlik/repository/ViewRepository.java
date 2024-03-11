package com.example.resumlik.repository;

import com.example.resumlik.model.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    List<View> findAllByResumeId(Long id);
}
