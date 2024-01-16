package com.example.student.student;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Override
    public ResponseEntity<List<StudentEntity>> getStudents() throws Exception {
        List<StudentEntity> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentEntity> findStudentById(Long studentId) throws Exception {
        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            StudentEntity student = studentOptional.get();
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<StudentEntity> create(StudentEntity student) throws Exception {
        StudentEntity savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<StudentEntity> update(Long studentId, String name, String email) throws Exception {
        StudentEntity student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("Student with id: " + studentId + " does not exists!"));

        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            boolean studentExists = this.studentExists(email);
            if (studentExists) {
                throw new IllegalStateException("email taken!");
            }
            student.setEmail(email);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentEntity> delete(Long studentId) throws Exception {
        studentRepository.deleteById(studentId);

        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean studentExists(String email) {
        return studentRepository.studentExists(email);
    }
}
