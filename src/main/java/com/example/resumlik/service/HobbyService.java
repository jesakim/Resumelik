package com.example.resumlik.service;

import com.example.resumlik.dto.request.HobbyRequestDto;
import com.example.resumlik.dto.response.HobbyResponseDto;
import com.example.resumlik.model.Hobby;
import com.example.resumlik.model.Resume;
import com.example.resumlik.repository.HobbyRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.util.AuthHelper;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;
    private final ResumeRepository resumeRepository;

    public Response<List<HobbyResponseDto>> getAllByResumeId(Long resumeId) {
        List<HobbyResponseDto> hobbyDtoList = hobbyRepository.findAllByResumeId(resumeId).stream()
                .map(HobbyResponseDto::new)
                .toList();

        return Response.<List<HobbyResponseDto>>builder()
                .result(hobbyDtoList)
                .build();
    }

    public Response<HobbyResponseDto> save(HobbyRequestDto hobbyRequestDto) {
        Resume resume = resumeRepository.findById(hobbyRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found."));
        if (!AuthHelper.checkOwnerShip(resume)){
            throw new RuntimeException("You are not authorized to create hobby for this resume.");
        }
        Hobby hobby = hobbyRequestDto.toEntity();
        hobby.setResume(resume);

        return Response.<HobbyResponseDto>builder()
                .result(new HobbyResponseDto(hobbyRepository.save(hobby)))
                .message("Hobby created successfully")
                .build();
    }

    public Response<String> delete(Long id) {
        Hobby hobby = hobbyRepository.findById(id).orElseThrow(()->new RuntimeException("Hobby not found"));
        if (!AuthHelper.checkOwnerShip(hobby)){
            throw new RuntimeException("You are not authorized to delete this hobby.");
        }

        hobbyRepository.deleteById(id);

        return Response.<String>builder()
                .message("Hobby deleted successfully")
                .build();
    }

    public Response<HobbyResponseDto> update(Long id, HobbyRequestDto hobbyRequestDto) {
        Hobby hobby = hobbyRepository.findById(id).orElseThrow(()->new RuntimeException("Hobby not found"));
        if (!AuthHelper.checkOwnerShip(hobby)){
            throw new RuntimeException("You are not authorized to update this hobby.");
        }
        hobby.setName(hobbyRequestDto.getName());

        return Response.<HobbyResponseDto>builder()
                .result(new HobbyResponseDto(hobbyRepository.save(hobby)))
                .message("Hobby updated successfully")
                .build();
    }
}
