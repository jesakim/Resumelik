package com.example.resumlik.service;

import com.example.resumlik.dto.request.CertificateRequestDto;
import com.example.resumlik.dto.response.CertificateResponseDto;
import com.example.resumlik.model.Certificate;
import com.example.resumlik.model.Skill;
import com.example.resumlik.repository.CertificateRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.repository.SkillRepository;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {
//    public final SkillRepository skillRepository;
//    private final ResumeRepository resumeRepository;
//
//    public Response<List<SkillResponseDto>> getAllByResumeId(Long resumeId) {
//
//        List<SkillResponseDto> skillResponseDtos = skillRepository.findAllByResumeId(resumeId)
//                .stream()
//                .map(SkillResponseDto::new)
//                .toList();
//
//        return Response.<List<SkillResponseDto>>builder()
//                .result(skillResponseDtos)
//                .build();
//    }
//
//    public Response<SkillResponseDto> save(SkillRequestDto skillRequestDto) {
//
//        Skill skill = skillRequestDto.toEntity();
//        skill.setResume(resumeRepository.findById(skillRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found.")));
//
//        return Response.<SkillResponseDto>builder()
//                .result(new SkillResponseDto(skillRepository.save(skill)))
//                .build();
//    }
//
//    public Response<String> delete(Long id) {
//        skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found."));
//
//        skillRepository.deleteById(id);
//
//        return Response.<String>builder()
//                .message("Skill deleted.")
//                .result("Skill deleted.")
//                .build();
//    } follow this pattern to create a new service

    private final CertificateRepository certificateRepository;
    private final ResumeRepository resumeRepository;

    public Response<CertificateResponseDto> save(CertificateRequestDto certificateRequestDto) {
        Certificate certificate = certificateRequestDto.toEntity();
        certificate.setResume(resumeRepository.findById(certificateRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found.")));

        return Response.<CertificateResponseDto>builder()
                .result(
                        new CertificateResponseDto(
                                certificateRepository.save(certificate)
                        ))
                .build();
    }

    public Response<String> delete(Long id) {
        certificateRepository.findById(id).orElseThrow(() -> new RuntimeException("Certificate not found."));

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

}
