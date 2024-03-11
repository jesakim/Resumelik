package com.example.resumlik.service;


import com.example.resumlik.dto.request.ResumeRequestDto;
import com.example.resumlik.dto.response.*;
import com.example.resumlik.model.Resume;
import com.example.resumlik.repository.*;
import com.example.resumlik.util.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CertificateRepository certificateRepository;
    private final ContactRepository contactRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final HobbyRepository hobbyRepository;
    private final LanguageRepository languageRepository;
    private final ProjectRepository projectRepository;
    private final SkillRepository skillRepository;
    private final ViewRepository viewRepository;

    public Response<ResumeResponseDto> create(ResumeRequestDto resumeDto) {
        resumeRepository.findByName(resumeDto.getName()).ifPresent(resume -> {
            throw new IllegalArgumentException("Resume name must be unique");
        });

        Resume resume = resumeDto.toEntity();
        resume.setUser(userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found")));

        return Response.<ResumeResponseDto>builder()
                .result(new ResumeResponseDto(resumeRepository.save(resume)))
                .message("Resume created successfully")
                .build();
    }

    public Response<List<ResumeResponseDto>> getMyResume() {
        List<ResumeResponseDto> resumeDtoList = resumeRepository.findAllByUserId(1L).stream()
                .map(ResumeResponseDto::new)
                .toList();

        return Response.<List<ResumeResponseDto>>builder()
                .result(resumeDtoList)
                .build();
    }

    public Response<ResumeResponseDto> update(Long id,@Valid ResumeRequestDto resumeRequestDto) {
        resumeRepository.findByName(resumeRequestDto.getName()).ifPresent(resume -> {
            if (!resume.getId().equals(id)) {
                throw new IllegalArgumentException("Resume name must be unique");
            } else if (!resume.getUser().getId().equals(1L)) {
                throw new IllegalArgumentException("You can't update this resume");
            }
        });

        Resume resume = resumeRepository.findById(id).orElseThrow(() -> new RuntimeException("Resume not found"));
        resume.setName(resumeRequestDto.getName());
        resume.setFirstName(resumeRequestDto.getFirstName());
        resume.setLastName(resumeRequestDto.getLastName());
        resume.setPicture(resumeRequestDto.getPicture());
        resume.setTitle(resumeRequestDto.getTitle());


        return Response.<ResumeResponseDto>builder()
                .result(new ResumeResponseDto(resumeRepository.save(resume)))
                .message("Resume updated successfully")
                .build();
    }

    public Response<String> delete(Long id) {

        resumeRepository.findById(id).orElseThrow(() -> new RuntimeException("Resume not found"));

        resumeRepository.deleteById(id);
        return Response.<String>builder()
                .result("Resume deleted successfully")
                .build();
    }

    public Response<FullResumeResponseDto> getByName(String name) {
        Resume resume = resumeRepository.findByName(name).orElseThrow(() -> new RuntimeException("Resume not found"));

        FullResumeResponseDto fullResumeResponseDto = new FullResumeResponseDto(resume);
        fullResumeResponseDto.setAddresses(addressRepository.findAllByResumeId(resume.getId()).stream()
                .map(AddressResponseDto::new)
                .toArray(AddressResponseDto[]::new));
        fullResumeResponseDto.setCertificates(certificateRepository.findAllByResumeId(resume.getId()).stream()
                .map(CertificateResponseDto::new)
                .toArray(CertificateResponseDto[]::new));
        fullResumeResponseDto.setContacts(contactRepository.findAllByResumeId(resume.getId()).stream()
                .map(ContactResponseDto::new)
                .toArray(ContactResponseDto[]::new));
        fullResumeResponseDto.setEducations(educationRepository.findAllByResumeId(resume.getId()).stream()
                .map(EducationResponseDto::new)
                .toArray(EducationResponseDto[]::new));
        fullResumeResponseDto.setExperiences(experienceRepository.findAllByResumeId(resume.getId()).stream()
                .map(ExperienceResponseDto::new)
                .toArray(ExperienceResponseDto[]::new));
        fullResumeResponseDto.setHobbies(hobbyRepository.findAllByResumeId(resume.getId()).stream()
                .map(HobbyResponseDto::new)
                .toArray(HobbyResponseDto[]::new));
        fullResumeResponseDto.setLanguages(languageRepository.findAllByResumeId(resume.getId()).stream()
                .map(LanguageResponseDto::new)
                .toArray(LanguageResponseDto[]::new));
        fullResumeResponseDto.setProjects(projectRepository.findAllByResumeId(resume.getId()).stream()
                .map(ProjectResponseDto::new)
                .toArray(ProjectResponseDto[]::new));
        fullResumeResponseDto.setSkills(skillRepository.findAllByResumeId(resume.getId()).stream()
                .map(SkillResponseDto::new)
                .toArray(SkillResponseDto[]::new));
        fullResumeResponseDto.setViews(viewRepository.findAllByResumeId(resume.getId()).stream()
                .map(ViewResponseDto::new)
                .toArray(ViewResponseDto[]::new));

        return Response.<FullResumeResponseDto>builder()
                .result(fullResumeResponseDto)
                .build();


    }
}
