package com.example.resumlik.repository;

import com.example.resumlik.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    List<Hobby> findAllByResumeId(Long resumeId);
}
