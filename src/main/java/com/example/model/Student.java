package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDay;
    private String gender;
    private String image;
    @ManyToOne
    private ClassRoom classRoom;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_tutor",
            joinColumns = {
                    @JoinColumn (name = "student_id",referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tutor_id",referencedColumnName = "id")
            }
    )
    @JsonIgnoreProperties("students")
    private Set<Tutor> tutors;


}
