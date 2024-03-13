package com.example.resumlik.service;

import com.example.resumlik.dto.request.ExperienceRequestDto;
import com.example.resumlik.dto.response.ExperienceResponseDto;
import com.example.resumlik.model.Experience;
import com.example.resumlik.model.Resume;
import com.example.resumlik.repository.ExperienceRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.util.AuthHelper;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    public final ExperienceRepository experienceRepository;
    private final ResumeRepository resumeRepository;

    public Response<List<ExperienceResponseDto>> getAllByResumeId(Long resumeId) {

        List<ExperienceResponseDto> experienceResponseDtos = experienceRepository.findAllByResumeId(resumeId)
                .stream()
                .map(ExperienceResponseDto::new)
                .toList();

        return Response.<List<ExperienceResponseDto>>builder()
                .result(experienceResponseDtos)
                .build();
    }

    public Response<ExperienceResponseDto> save(ExperienceRequestDto experienceRequestDto) {
        Resume resume = resumeRepository.findById(experienceRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found."));
        if(!AuthHelper.checkOwnerShip(resume)){
            throw new RuntimeException("You are not authorized to create experience for this resume.");
        }
        Experience experience = experienceRequestDto.toEntity();
        experience.setResume(resume);

        return Response.<ExperienceResponseDto>builder()
                .result(new ExperienceResponseDto(experienceRepository.save(experience)))
                .build();
    }

    public Response<String> delete(Long id) {
        Experience experience = experienceRepository.findById(id).orElseThrow(() -> new RuntimeException("Experience not found."));
        if(!AuthHelper.checkOwnerShip(experience)){
            throw new RuntimeException("You are not authorized to delete this experience.");
        }

        experienceRepository.deleteById(id);

        return Response.<String>builder()
                .message("Experience deleted.")
                .result("Experience deleted.")
                .build();
    }

    public Response<ExperienceResponseDto> update(Long id, ExperienceRequestDto experienceRequestDto) {
        Experience experience = experienceRepository.findById(id).orElseThrow(() -> new RuntimeException("Experience not found."));
        if(!AuthHelper.checkOwnerShip(experience)){
            throw new RuntimeException("You are not authorized to update this experience.");
        }

        experience.setCompany(experienceRequestDto.getCompany());
        experience.setTitle(experienceRequestDto.getTitle());
        experience.setFromDate(experienceRequestDto.getFromDate());
        experience.setToDate(experienceRequestDto.getToDate());
        experience.setDescription(experienceRequestDto.getDescription());

        return Response.<ExperienceResponseDto>builder()
                .result(new ExperienceResponseDto(experienceRepository.save(experience)))
                .message("Experience updated.")
                .build();
    }
}
