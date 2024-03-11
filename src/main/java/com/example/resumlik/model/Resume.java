package com.example.resumlik.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String firstName;
    private String lastName;
    private String picture;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "resume" , cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "resume" , cascade = CascadeType.ALL)
    private List<Education> educations;

    @OneToMany(mappedBy = "resume" , cascade = CascadeType.ALL)
    private List<View> views;

    @OneToMany(mappedBy = "resume" , cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @OneToMany(mappedBy = "resume" , cascade = CascadeType.ALL)
    private List<Language> languages;

    @OneToMany(mappedBy = "resume" , cascade = CascadeType.ALL)
    private List<Skill> skills;

    @OneToMany(mappedBy = "resume" , cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "resume" , cascade = CascadeType.ALL)
    private List<Certificate> certificates;

    @OneToMany(mappedBy = "resume" , cascade = CascadeType.ALL)
    private List<Hobby> hobbies;

    @OneToMany(mappedBy = "resume" , cascade = CascadeType.ALL)
    private List<Project> projects;

    // Setters and getters
}

