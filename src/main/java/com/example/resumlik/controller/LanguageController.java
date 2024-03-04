package com.example.resumlik.controller;

import com.example.resumlik.dto.request.LanguageRequestDto;
import com.example.resumlik.dto.response.LanguageResponseDto;
import com.example.resumlik.model.Language;
import com.example.resumlik.repository.LanguageRepository;
import com.example.resumlik.service.LanguageService;
import com.example.resumlik.util.Constants;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_VERSION+"/language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping("/{resumeId}")
    public ResponseEntity<Response<List<LanguageResponseDto>>> getLanguagesByResumeId(@PathVariable Long resumeId) {
        return ResponseEntity.ok(languageService.getByResumeId(resumeId));
    }

    @PostMapping
    public ResponseEntity<Response<LanguageResponseDto>> saveLanguage(@Valid @RequestBody LanguageRequestDto languageRequestDto) {
        return ResponseEntity.ok(languageService.save(languageRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteLanguage(@PathVariable Long id) {
        return ResponseEntity.ok(languageService.delete(id));
    }
}
