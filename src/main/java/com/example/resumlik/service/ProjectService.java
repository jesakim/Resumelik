package com.example.resumlik.service;


import com.example.resumlik.dto.request.ProjectRequestDto;
import com.example.resumlik.dto.response.ProjectResponseDto;
import com.example.resumlik.model.Project;
import com.example.resumlik.repository.ProjectRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

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
//    } follow this pattern

    private final ProjectRepository projectRepository;
    private final ResumeRepository resumeRepository;

    public Response<ProjectResponseDto> save(ProjectRequestDto projectRequestDto) {

        Project project = projectRequestDto.toEntity();
        project.setResume(resumeRepository.findById(projectRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found.")));

        return Response.<ProjectResponseDto>builder()
                .result(new ProjectResponseDto(projectRepository.save(project)))
                .build();
    }

    public Response<List<ProjectResponseDto>> findByResumeId(Long resumeId) {

        List<ProjectResponseDto> projectResponseDtos = projectRepository.findAllByResumeId(resumeId)
                .stream()
                .map(ProjectResponseDto::new)
                .toList();

        return Response.<List<ProjectResponseDto>>builder()
                .result(projectResponseDtos)
                .build();
    }

    public Response<String> delete(Long id) {
        projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found."));

        projectRepository.deleteById(id);

        return Response.<String>builder()
                .message("Project deleted.")
                .result("Project deleted.")
                .build();
    }
}
