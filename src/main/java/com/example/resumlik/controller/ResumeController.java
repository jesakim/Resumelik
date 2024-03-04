package com.example.resumlik.controller;

import com.example.resumlik.dto.request.ResumeRequestDto;
import com.example.resumlik.dto.response.ResumeResponseDto;
import com.example.resumlik.model.Resume;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.service.ResumeService;
import com.example.resumlik.util.Constants;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_VERSION +"/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

//    @GetMapping
//    public ResponseEntity<Response<List<ResumeResponseDto>>> getAll() {
//        return ResponseEntity.ok(resumeService.getAllResumes());
//    }
//    get just my resumes by user id

    @GetMapping
    public ResponseEntity<Response<List<ResumeResponseDto>>> getMyResume() {
        return ResponseEntity.ok(resumeService.getMyResume());
    }




    @PostMapping
    public ResponseEntity<Response<ResumeResponseDto>> create(@Valid @RequestBody ResumeRequestDto resumeRequestDto) {
        return ResponseEntity.ok(resumeService.create(resumeRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<ResumeResponseDto>> update(@PathVariable Long id, @Valid @RequestBody ResumeRequestDto resumeRequestDto) {
        return ResponseEntity.ok(resumeService.update(id, resumeRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(resumeService.delete(id));
    }



}
