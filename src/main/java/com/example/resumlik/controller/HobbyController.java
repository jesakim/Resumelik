package com.example.resumlik.controller;

import com.example.resumlik.dto.request.HobbyRequestDto;
import com.example.resumlik.dto.response.HobbyResponseDto;
import com.example.resumlik.service.HobbyService;
import com.example.resumlik.util.Constants;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_VERSION+"/hobby")
@RequiredArgsConstructor
public class HobbyController {

    private final HobbyService hobbyService;

    @PostMapping
    public ResponseEntity<Response<HobbyResponseDto>> save(@Valid @RequestBody HobbyRequestDto hobbyRequestDto) {
        return ResponseEntity.ok(hobbyService.save(hobbyRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(hobbyService.delete(id));
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<Response<List<HobbyResponseDto>>> getAllByResumeId(@PathVariable Long resumeId) {
        return ResponseEntity.ok(hobbyService.getAllByResumeId(resumeId));
    }
}
