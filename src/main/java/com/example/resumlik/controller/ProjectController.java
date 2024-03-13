package com.example.resumlik.controller;

import com.example.resumlik.dto.request.ProjectRequestDto;
import com.example.resumlik.dto.response.ProjectResponseDto;
import com.example.resumlik.service.ProjectService;
import com.example.resumlik.util.Constants;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_VERSION+"/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Response<ProjectResponseDto>> save(@Valid @RequestBody ProjectRequestDto projectRequestDto) {
        return ResponseEntity.ok(projectService.save(projectRequestDto));
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<Response<List<ProjectResponseDto>>> findByResumeId(@PathVariable Long resumeId) {
        return ResponseEntity.ok(projectService.findByResumeId(resumeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<ProjectResponseDto>> update(@PathVariable Long id, @Valid @RequestBody ProjectRequestDto projectRequestDto) {
        return ResponseEntity.ok(projectService.update(id, projectRequestDto));
    }
}
