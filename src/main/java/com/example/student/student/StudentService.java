package com.example.student.student;

import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The interface Student service.
 */
public interface StudentService {

    /**
     * Gets students.
     *
     * @return the students
     * @throws Exception the exception
     */
    public ResponseEntity<List<StudentEntity>> getStudents() throws Exception;


    /**
     * Find student by id response entity.
     *
     * @param studentId the student id
     * @return the response entity
     * @throws Exception the exception
     */
    public ResponseEntity<StudentEntity> findStudentById(Long studentId) throws Exception;


    /**
     * Create student entity.
     *
     * @param student the student
     * @return the student entity
     * @throws Exception the exception
     */
    public ResponseEntity<StudentEntity> create(StudentEntity student) throws Exception;


    /**
     * Update response entity.
     *
     * @param studentId the student id
     * @param name      the name
     * @param email     the email
     * @return the response entity
     * @throws Exception the exception
     */
    @Transactional
    public ResponseEntity<StudentEntity> update(Long studentId, String name, String email) throws Exception;

    /**
     * Delete response entity.
     *
     * @param studentId the student id
     * @return the response entity
     * @throws Exception the exception
     */
    public ResponseEntity<StudentEntity> delete(Long studentId) throws Exception;


    /**
     * Student exists boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean studentExists(String email);
}
