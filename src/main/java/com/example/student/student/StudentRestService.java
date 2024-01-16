package com.example.student.student;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class StudentRestService {
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getStudents() throws Exception {
        return studentService.getStudents();
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<StudentEntity> findStudentById(@PathVariable(name = "studentId") Long studentId) throws Exception {
        return studentService.findStudentById(studentId);
    }

    @PostMapping
    public ResponseEntity<StudentEntity> create(@RequestBody StudentEntity student) throws Exception {
        return studentService.create(student);
    }

    @PutMapping(value = "{studentId}")
    public ResponseEntity<StudentEntity> update(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) throws Exception {
        return studentService.update(studentId, name, email);
    }

    @DeleteMapping(value = "{studentId}")
    public ResponseEntity<StudentEntity> delete(Long studentId) throws Exception {
        return studentService.delete(studentId);
    }
}
