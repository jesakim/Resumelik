package com.example.resumlik.dto.request;

import com.example.resumlik.model.Certificate;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Setter
@Getter
public class CertificateRequestDto {

        @NotNull(message = "Name is required")
        @NotBlank(message = "Name is required")
        private String name;

        @NotNull(message = "Date is required")
        private Date date;

        @NotNull(message = "Issuer is required")
        @NotBlank(message = "Issuer is required")
        private String issuer;

        @NotNull(message = "ResumeId is required")
        @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "ResumeId is not valid")
        private Long resumeId;

        public Certificate toEntity() {
            return Certificate.builder()
                    .name(name)
                    .date(date)
                    .issuer(issuer)
                    .build();
        }


}
