package com.example.resumlik.service;

import com.example.resumlik.dto.request.CertificateRequestDto;
import com.example.resumlik.dto.response.CertificateResponseDto;
import com.example.resumlik.model.Certificate;
import com.example.resumlik.model.Resume;
import com.example.resumlik.model.Skill;
import com.example.resumlik.repository.CertificateRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.repository.SkillRepository;
import com.example.resumlik.util.AuthHelper;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;
    private final ResumeRepository resumeRepository;

    public Response<CertificateResponseDto> save(CertificateRequestDto certificateRequestDto) {
        Resume resume = resumeRepository.findById(certificateRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found."));
        if (!AuthHelper.checkOwnerShip(resume)){
            throw new RuntimeException("You are not authorized to create certificate for this resume.");
        }
        Certificate certificate = certificateRequestDto.toEntity();
        certificate.setResume(resume);

        return Response.<CertificateResponseDto>builder()
                .result(
                        new CertificateResponseDto(
                                certificateRepository.save(certificate)
                        ))
                .build();
    }

    public Response<String> delete(Long id) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new RuntimeException("Certificate not found."));

        if (!AuthHelper.checkOwnerShip(certificate)){
            throw new RuntimeException("You are not authorized to delete this certificate.");
        }


        certificateRepository.deleteById(id);

        return Response.<String>builder()
                .message("Certificate deleted.")
                .result("Certificate deleted.")
                .build();
    }

    public Response<List<CertificateResponseDto>> getAllByResumeId(Long resumeId) {
        List<CertificateResponseDto> certificateResponseDtos = certificateRepository.findAllByResumeId(resumeId)
                .stream()
                .map(CertificateResponseDto::new)
                .toList();

        return Response.<List<CertificateResponseDto>>builder()
                .result(certificateResponseDtos)
                .build();
    }

    public Response<CertificateResponseDto> update(Long id, CertificateRequestDto certificateRequestDto) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new RuntimeException("Certificate not found."));
        if (!AuthHelper.checkOwnerShip(certificate)){
            throw new RuntimeException("You are not authorized to update this certificate.");
        }
        certificate.setName(certificateRequestDto.getName());
        certificate.setIssuer(certificateRequestDto.getIssuer());
        certificate.setDate(certificateRequestDto.getDate());

        return Response.<CertificateResponseDto>builder()
                .result(new CertificateResponseDto(certificateRepository.save(certificate)))
                .message("Certificate updated.")
                .build();

    }
}
