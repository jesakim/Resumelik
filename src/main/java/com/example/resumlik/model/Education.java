package com.example.resumlik.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String establishment;
    private String degree;
    private Date fromDate;
    private Date toDate;

    @ManyToOne
    private Resume resume;
}
