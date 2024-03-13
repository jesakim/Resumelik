package com.example.resumlik.controller;

import com.example.resumlik.dto.request.CertificateRequestDto;
import com.example.resumlik.dto.response.CertificateResponseDto;
import com.example.resumlik.service.CertificateService;
import com.example.resumlik.util.Constants;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_VERSION+"/certificate")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping
    public ResponseEntity<Response<CertificateResponseDto>> save(@Valid @RequestBody CertificateRequestDto certificateRequestDto) {
        return ResponseEntity.ok(certificateService.save(certificateRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(certificateService.delete(id));
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<Response<List<CertificateResponseDto>>> getAllByResumeId(@PathVariable Long resumeId) {
        return ResponseEntity.ok(certificateService.getAllByResumeId(resumeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<CertificateResponseDto>> update(@PathVariable Long id, @Valid @RequestBody CertificateRequestDto certificateRequestDto) {
        return ResponseEntity.ok(certificateService.update(id, certificateRequestDto));
    }



}
