package com.example.resumlik.service;

import com.example.resumlik.dto.request.EducationRequestDto;
import com.example.resumlik.dto.response.EducationResponseDto;
import com.example.resumlik.model.Education;
import com.example.resumlik.model.Resume;
import com.example.resumlik.repository.EducationRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.util.AuthHelper;
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

        List<EducationResponseDto> educations = educationRepository.findAllByResumeId(resumeId)
                .stream()
                .map(EducationResponseDto::new)
                .toList();

        return Response.<List<EducationResponseDto>>
                builder()
                .result(educations)
                .build();
    }

    public Response<EducationResponseDto> create(EducationRequestDto educationRequestDto) {
        Resume resume = resumeRepository.findById(educationRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found."));
        if (!AuthHelper.checkOwnerShip(resume)) {
            throw new RuntimeException("You are not authorized to add education to this resume.");
        }
        Education education = educationRequestDto.toEntity();
        education.setResume(resume);

        return Response.<EducationResponseDto>
                builder()
                .result(new EducationResponseDto(educationRepository.save(education)))
                .message("Education created successfully.")
                .build();
    }

    public Response<String> delete(Long id) {
        Education education = educationRepository.findById(id).orElseThrow(() -> new RuntimeException("Education not found."));
        if (!AuthHelper.checkOwnerShip(education.getResume())) {
            throw new RuntimeException("You are not authorized to delete this education.");
        }
        educationRepository.deleteById(id);
        return Response.<String>
                builder()
                .message("Education deleted successfully.")
                .build();
    }

    public Response<EducationResponseDto> update(Long id, EducationRequestDto educationRequestDto) {
        Education education = educationRepository.findById(id).orElseThrow(() -> new RuntimeException("Education not found."));
        if (!AuthHelper.checkOwnerShip(education.getResume())) {
            throw new RuntimeException("You are not authorized to update this education.");
        }
        education.setEstablishment(educationRequestDto.getEstablishment());
        education.setDegree(educationRequestDto.getDegree());
        education.setToDate(educationRequestDto.getToDate());
        education.setFromDate(educationRequestDto.getFromDate());
        return Response.<EducationResponseDto>
                builder()
                .result(new EducationResponseDto(educationRepository.save(education)))
                .message("Education updated successfully.")
                .build();
    }
}
