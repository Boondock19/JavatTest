package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }


    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }


    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id)
    {return studentService.updateStudentInformation(student,id);}


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
         studentService.deleteStudentById(id);
    }
}
