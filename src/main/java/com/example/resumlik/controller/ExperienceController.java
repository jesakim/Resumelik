package com.example.resumlik.controller;

import com.example.resumlik.dto.request.ExperienceRequestDto;
import com.example.resumlik.dto.response.ExperienceResponseDto;
import com.example.resumlik.model.Experience;
import com.example.resumlik.repository.ExperienceRepository;
import com.example.resumlik.service.ExperienceService;
import com.example.resumlik.util.Constants;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_VERSION+"/experience")
@RequiredArgsConstructor
public class ExperienceController {
    public final ExperienceService experienceService;

    @GetMapping("/{resumeId}")
    public ResponseEntity<Response<List<ExperienceResponseDto>>> getAllByResumeId(@PathVariable Long resumeId) {
        return ResponseEntity.ok(experienceService.getAllByResumeId(resumeId));
    }

    @PostMapping
    public ResponseEntity<Response<ExperienceResponseDto>> save(@Valid @RequestBody ExperienceRequestDto experienceRequestDto) {
        return ResponseEntity.ok(experienceService.save(experienceRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(experienceService.delete(id));
    }


}
