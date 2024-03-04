package com.example.resumlik.service;

import com.example.resumlik.dto.request.ExperienceRequestDto;
import com.example.resumlik.dto.response.ExperienceResponseDto;
import com.example.resumlik.model.Experience;
import com.example.resumlik.repository.ExperienceRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

//    public final SkillRepository skillRepository;
//    private final ResumeRepository resumeRepository;
//
//    public Response<List<SkillResponseDto>> getAllByResumeId(Long resumeId) {
//
//        List<SkillResponseDto> skillResponseDtos = skillRepository.findAllByResumeId(resumeId)
//                .stream()
//                .map(SkillResponseDto::new)
//                .toList();
//
//        return Response.<List<SkillResponseDto>>builder()
//                .result(skillResponseDtos)
//                .build();
//    }
//
//    public Response<SkillResponseDto> save(SkillRequestDto skillRequestDto) {
//
//        Skill skill = skillRequestDto.toEntity();
//        skill.setResume(resumeRepository.findById(skillRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found.")));
//
//        return Response.<SkillResponseDto>builder()
//                .result(new SkillResponseDto(skillRepository.save(skill)))
//                .build();
//    }
//
//    public Response<String> delete(Long id) {
//        skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found."));
//
//        skillRepository.deleteById(id);
//
//        return Response.<String>builder()
//                .message("Skill deleted.")
//                .result("Skill deleted.")
//                .build();
//    }

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

        Experience experience = experienceRequestDto.toEntity();
        experience.setResume(resumeRepository.findById(experienceRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found.")));

        return Response.<ExperienceResponseDto>builder()
                .result(new ExperienceResponseDto(experienceRepository.save(experience)))
                .build();
    }

    public Response<String> delete(Long id) {
        experienceRepository.findById(id).orElseThrow(() -> new RuntimeException("Experience not found."));

        experienceRepository.deleteById(id);

        return Response.<String>builder()
                .message("Experience deleted.")
                .result("Experience deleted.")
                .build();
    }
}
