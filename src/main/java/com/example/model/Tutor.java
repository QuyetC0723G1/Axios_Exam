package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @ManyToMany(mappedBy = "tutors", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("tutors")
    private Set<Student> students;

    public Tutor(Long id) {
        this.id = id;
    }
}
