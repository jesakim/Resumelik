package com.example.resumlik.model;

import com.example.resumlik.enums.ContactType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ContactType type = ContactType.OTHER ;
    private String text;
    private String link;

    @ManyToOne
    private Resume resume;
}

