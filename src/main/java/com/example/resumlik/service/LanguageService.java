package com.example.resumlik.service;

import com.example.resumlik.dto.request.LanguageRequestDto;
import com.example.resumlik.dto.response.LanguageResponseDto;
import com.example.resumlik.model.Language;
import com.example.resumlik.model.Resume;
import com.example.resumlik.repository.LanguageRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.util.AuthHelper;
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
        List<LanguageResponseDto> languages = languageRepository.findAllByResumeId(resumeId)
                .stream()
                .map(LanguageResponseDto::new)
                .toList();

        return Response.<List<LanguageResponseDto>>builder()
                .result(languages)
                .build();


    }

    public Response<LanguageResponseDto> save(LanguageRequestDto languageRequestDto) {
        Resume resume = resumeRepository.findById(languageRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found."));
        if (!AuthHelper.checkOwnerShip(resume)){
            throw new RuntimeException("You are not authorized to create language for this resume.");
        }
        Language language = languageRequestDto.toEntity();
        language.setResume(resume);

        return Response.<LanguageResponseDto>builder()
                .result(new LanguageResponseDto(languageRepository.save(language)))
                .build();
    }

    public Response<String> delete(Long id) {
        Language language = languageRepository.findById(id).orElseThrow(() -> new RuntimeException("Language not found."));
        if (!AuthHelper.checkOwnerShip(language)){
            throw new RuntimeException("You are not authorized to delete this language.");
        }

        languageRepository.deleteById(id);
        return Response.<String>builder()
                .result("Language deleted.")
                .build();
    }

    public Response<LanguageResponseDto> update(Long id, @Valid LanguageRequestDto languageRequestDto) {
        Language language = languageRepository.findById(id).orElseThrow(() -> new RuntimeException("Language not found."));
        if (!AuthHelper.checkOwnerShip(language)){
            throw new RuntimeException("You are not authorized to update this language.");
        }
        language.setName(languageRequestDto.getName());
        language.setLevel(languageRequestDto.getLevel());

        return Response.<LanguageResponseDto>builder()
                .result(new LanguageResponseDto(languageRepository.save(language)))
                .build();
    }
}
