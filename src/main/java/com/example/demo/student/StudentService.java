package com.example.demo.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email Taken");
        }

        studentRepository.save(student);
    }

    public Student updateStudentInformation(Student student, Long id) {
        Optional<Student> studentOptional = studentRepository.findStudentById(id);

        if (studentOptional.isEmpty()) {
            throw new IllegalStateException(String.format("Id not found, student with %o does not exist",id));
        }

        Student updateStudent = studentOptional.get();

        updateStudent.setName(student.getName());
        updateStudent.setEmail(student.getEmail());
        updateStudent.setDob(student.getDob());

       return studentRepository.save(updateStudent);

//        return student;
    }


//    public Student postStudent() {
//
//    }
}
