package com.example.resumlik.service;

import com.example.resumlik.dto.request.LanguageRequestDto;
import com.example.resumlik.dto.response.LanguageResponseDto;
import com.example.resumlik.model.Language;
import com.example.resumlik.repository.LanguageRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.util.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final ResumeRepository resumeRepository;

    public Response<List<LanguageResponseDto>> getByResumeId(Long resumeId) {
        List<LanguageResponseDto> languages = languageRepository.findByResumeId(resumeId)
                .stream()
                .map(LanguageResponseDto::new)
                .toList();

        return Response.<List<LanguageResponseDto>>builder()
                .result(languages)
                .build();


    }

    public Response<LanguageResponseDto> save(LanguageRequestDto languageRequestDto) {

        Language language = languageRequestDto.toEntity();
        language.setResume(resumeRepository.findById(languageRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found.")));

        return Response.<LanguageResponseDto>builder()
                .result(new LanguageResponseDto(languageRepository.save(language)))
                .build();
    }

    public Response<String> delete(Long id) {
        languageRepository.findById(id).orElseThrow(() -> new RuntimeException("Language not found."));

        languageRepository.deleteById(id);
        return Response.<String>builder()
                .result("Language deleted.")
                .build();
    }
}
