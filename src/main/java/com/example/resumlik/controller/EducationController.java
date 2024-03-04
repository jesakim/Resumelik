package com.example.resumlik.controller;

import com.example.resumlik.dto.request.EducationRequestDto;
import com.example.resumlik.dto.response.EducationResponseDto;
import com.example.resumlik.model.Education;
import com.example.resumlik.repository.EducationRepository;
import com.example.resumlik.service.EducationService;
import com.example.resumlik.util.Constants;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_VERSION+"/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @GetMapping("/{resumeId}")
    public ResponseEntity<Response<List<EducationResponseDto>>> getEducationsByResumeId(@PathVariable Long resumeId) {
        return ResponseEntity.ok(educationService.getEducationsByResumeId(resumeId));
    }

    @PostMapping
    public ResponseEntity<Response<EducationResponseDto>> createEducation(@Valid @RequestBody EducationRequestDto educationRequestDto) {
        return ResponseEntity.ok(educationService.create(educationRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteEducation(@PathVariable Long id) {
        return ResponseEntity.ok(educationService.delete(id));
    }


}
