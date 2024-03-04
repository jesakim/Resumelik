package com.example.resumlik.service;

import com.example.resumlik.dto.request.EducationRequestDto;
import com.example.resumlik.dto.response.EducationResponseDto;
import com.example.resumlik.model.Education;
import com.example.resumlik.repository.EducationRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final ResumeRepository resumeRepository;

    public Response<List<EducationResponseDto>> getEducationsByResumeId(Long resumeId) {

        List<EducationResponseDto> educations = educationRepository.getEducationsByResumeId(resumeId)
                .stream()
                .map(EducationResponseDto::new)
                .toList();

        return Response.<List<EducationResponseDto>>
                builder()
                .result(educations)
                .build();
    }

    public Response<EducationResponseDto> create(EducationRequestDto educationRequestDto) {

        Education education = educationRequestDto.toEntity();
        education.setResume(resumeRepository.findById(educationRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found.")));

        return Response.<EducationResponseDto>
                builder()
                .result(new EducationResponseDto(educationRepository.save(education)))
                .message("Education created successfully.")
                .build();
    }

    public Response<String> delete(Long id) {
        educationRepository.findById(id).orElseThrow(() -> new RuntimeException("Education not found."));
        educationRepository.deleteById(id);
        return Response.<String>
                builder()
                .message("Education deleted successfully.")
                .build();
    }
}
