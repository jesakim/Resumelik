package com.example.resumlik.service;

import com.example.resumlik.dto.request.SkillRequestDto;
import com.example.resumlik.dto.response.SkillResponseDto;
import com.example.resumlik.enums.SkillType;
import com.example.resumlik.model.Resume;
import com.example.resumlik.model.Skill;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.repository.SkillRepository;
import com.example.resumlik.util.AuthHelper;
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
        Resume resume = resumeRepository.findById(skillRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found."));
        if(!AuthHelper.checkOwnerShip(resume)){
            throw new RuntimeException("You are not authorized to create skill for this resume.");
        }
        Skill skill = skillRequestDto.toEntity();
        skill.setResume(resume);

        return Response.<SkillResponseDto>builder()
                .result(new SkillResponseDto(skillRepository.save(skill)))
                .build();
    }

    public Response<String> delete(Long id) {
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found."));
        if(!AuthHelper.checkOwnerShip(skill)){
            throw new RuntimeException("You are not authorized to delete this skill.");
        }

        skillRepository.deleteById(id);

        return Response.<String>builder()
                .message("Skill deleted.")
                .result("Skill deleted.")
                .build();
    }

    public Response<SkillResponseDto> update(Long id, SkillRequestDto skillRequestDto) {
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found."));
        if(!AuthHelper.checkOwnerShip(skill)){
            throw new RuntimeException("You are not authorized to update this skill.");
        }
        skill.setName(skillRequestDto.getName());
        skill.setType(SkillType.valueOf(skillRequestDto.getType()));
        skill.setResume(resumeRepository.findById(skillRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found.")));

        return Response.<SkillResponseDto>builder()
                .result(new SkillResponseDto(skillRepository.save(skill)))
                .message("Skill updated.")
                .build();
    }
}
