package com.example.studentmanagement.service;

import com.example.studentmanagement.Student;
import com.example.studentmanagement.repository.StudentRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Student not found with ID: " + id));
    }

    public Student updateStudent(int id, Student student) {
        Student existingStudent = getStudentById(id);

        existingStudent.setStudentName(student.getStudentName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCourseName(student.getCourseName());
        existingStudent.setProgrammingScore(student.getProgrammingScore());
        existingStudent.setMathematicsScore(student.getMathematicsScore());
        existingStudent.setTechnicalSkills(student.getTechnicalSkills());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(int id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}
