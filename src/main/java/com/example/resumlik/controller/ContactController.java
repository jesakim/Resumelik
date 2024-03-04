package com.example.resumlik.controller;

import com.example.resumlik.dto.request.ContactRequestDto;
import com.example.resumlik.dto.response.ContactResponseDto;
import com.example.resumlik.service.ContactService;
import com.example.resumlik.util.Constants;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_VERSION+"/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Response<ContactResponseDto>> save(@Valid @RequestBody ContactRequestDto contactRequestDto) {
        return ResponseEntity.ok(contactService.save(contactRequestDto));
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<Response<List<ContactResponseDto>>> findByResumeId(@PathVariable Long resumeId) {
        return ResponseEntity.ok(contactService.findByResumeId(resumeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.delete(id));
    }
}
