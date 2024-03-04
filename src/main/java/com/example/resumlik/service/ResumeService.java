package com.example.resumlik.service;


import com.example.resumlik.dto.request.ResumeRequestDto;
import com.example.resumlik.dto.response.ResumeResponseDto;
import com.example.resumlik.model.Resume;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.repository.UserRepository;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    private final UserRepository userRepository;

//    public Response<List<ResumeResponseDto>> getAllResumes() {
//
//        List<ResumeResponseDto> resumeDtoList = resumeRepository.findAll().stream()
//                .map(ResumeResponseDto::fromEntity)
//                .toList();
//
//        return Response.<List<ResumeResponseDto>>builder()
//                .result(resumeDtoList)
//                .build();
//
//    }

    public Response<ResumeResponseDto> create(ResumeRequestDto resumeDto) {

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

    public Response<ResumeResponseDto> update(Long id, ResumeRequestDto resumeRequestDto) {
        Resume resume = resumeRepository.findById(id).orElseThrow(() -> new RuntimeException("Resume not found"));
        resume.setTitle(resumeRequestDto.getTitle());
        resume.setFirstName(resumeRequestDto.getFirstName());
        resume.setLastName(resumeRequestDto.getLastName());
        resume.setPicture(resumeRequestDto.getPicture());

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
}
