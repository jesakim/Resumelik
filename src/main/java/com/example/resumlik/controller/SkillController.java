package com.example.resumlik.controller;

import com.example.resumlik.dto.request.SkillRequestDto;
import com.example.resumlik.dto.response.SkillResponseDto;
import com.example.resumlik.model.Skill;
import com.example.resumlik.repository.SkillRepository;
import com.example.resumlik.service.SkillService;
import com.example.resumlik.util.Constants;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_VERSION+"/skill")
@RequiredArgsConstructor
public class SkillController {
    public final SkillService skillService;

    @GetMapping("/{resumeId}")
    public ResponseEntity<Response<List<SkillResponseDto>>> getAllByResumeId(@PathVariable Long resumeId) {
        return ResponseEntity.ok(skillService.getAllByResumeId(resumeId));
    }

    @PostMapping
    public ResponseEntity<Response<SkillResponseDto>> save(@Valid @RequestBody SkillRequestDto skillRequestDto) {
        return ResponseEntity.ok(skillService.save(skillRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.delete(id));
    }

}
