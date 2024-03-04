package com.example.resumlik.model;

import com.example.resumlik.enums.ProjectMode;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String title;

    @Enumerated(EnumType.STRING)
    private ProjectMode mode;
    private Date realisedAt;
    private String description;

    @ManyToOne
    private Resume resume;
}

