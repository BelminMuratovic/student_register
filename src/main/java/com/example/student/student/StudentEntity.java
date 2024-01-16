package com.example.student.student;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;

    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;
}
