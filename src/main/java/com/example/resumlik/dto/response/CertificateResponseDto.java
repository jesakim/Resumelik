package com.example.resumlik.dto.response;

import com.example.resumlik.model.Certificate;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CertificateResponseDto {
    private Long id;
    private String name;
    private Date date;
    private String issuer;

    public CertificateResponseDto(Certificate certificate) {
        this.id = certificate.getId();
        this.name = certificate.getName();
        this.date = certificate.getDate();
        this.issuer = certificate.getIssuer();
    }
}
