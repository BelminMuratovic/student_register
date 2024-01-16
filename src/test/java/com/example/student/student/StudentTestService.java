package com.example.student.student;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentTestService {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentRestService studentRestService;

    @Test
    void testGetStudents() throws Exception {
        StudentEntity student1 = new StudentEntity();
        student1.setName("Student1");
        StudentEntity student2 = new StudentEntity();
        student2.setName("Student2");
        List<StudentEntity> students = List.of(student1, student2);

        when(studentService.getStudents()).thenReturn(new ResponseEntity<>(students, HttpStatus.OK));

        ResponseEntity<List<StudentEntity>> response = studentRestService.getStudents();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(students);
    }

    @Test
    void findStudentById() throws Exception {
        Long studentId = 1L;
        StudentEntity student = new StudentEntity();
        student.setId(studentId);
        student.setName("Student_name");

        when(studentService.findStudentById(studentId)).thenReturn(new ResponseEntity<>(student, HttpStatus.OK));

        ResponseEntity<StudentEntity> response = studentRestService.findStudentById(studentId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(student);
    }

    @Test
    void testCreateStudent() throws Exception {
        StudentEntity student = new StudentEntity();
        student.setName("Student_name");

        when(studentService.create(any(StudentEntity.class))).thenReturn(new ResponseEntity<>(student, HttpStatus.OK));

        ResponseEntity<StudentEntity> response = studentRestService.create(student);

        assertThat(response)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.OK, student);
    }

    @Test
    void testUpdateStudent() throws Exception {
        StudentEntity student = new StudentEntity();
        student.setId(11L);
        student.setName("Student_name");
        student.setEmail("Student_email");

        StudentEntity updatedStudent = new StudentEntity();
        updatedStudent.setId(11L);
        updatedStudent.setName("New_name");
        updatedStudent.setEmail("New_email");

        when(studentService.update(anyLong(), anyString(), anyString())).thenReturn(new ResponseEntity<>(updatedStudent, HttpStatus.OK));

        ResponseEntity<StudentEntity> response = studentRestService.update(11L, "New_name", "New_email");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(updatedStudent);
    }

    @Test
    void testDeleteStudent() throws Exception {
        Long studentId = 11L;

        when(studentService.delete(anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<StudentEntity> response = studentRestService.delete(studentId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNull();
    }
}
