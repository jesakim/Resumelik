package com.example.resumlik.service;

import com.example.resumlik.dto.request.SkillRequestDto;
import com.example.resumlik.dto.response.SkillResponseDto;
import com.example.resumlik.model.Skill;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.repository.SkillRepository;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    public final SkillRepository skillRepository;
    private final ResumeRepository resumeRepository;

    public Response<List<SkillResponseDto>> getAllByResumeId(Long resumeId) {

        List<SkillResponseDto> skillResponseDtos = skillRepository.findAllByResumeId(resumeId)
                .stream()
                .map(SkillResponseDto::new)
                .toList();

        return Response.<List<SkillResponseDto>>builder()
                .result(skillResponseDtos)
                .build();
    }

    public Response<SkillResponseDto> save(SkillRequestDto skillRequestDto) {

        Skill skill = skillRequestDto.toEntity();
        skill.setResume(resumeRepository.findById(skillRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found.")));

        return Response.<SkillResponseDto>builder()
                .result(new SkillResponseDto(skillRepository.save(skill)))
                .build();
    }

    public Response<String> delete(Long id) {
        skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found."));

        skillRepository.deleteById(id);

        return Response.<String>builder()
                .message("Skill deleted.")
                .result("Skill deleted.")
                .build();
    }
}
