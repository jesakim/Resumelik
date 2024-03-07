package com.example.resumlik.repository;

import com.example.resumlik.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findAllByUserId(Long userId);

    Optional<Resume> findByName(String name);
}
