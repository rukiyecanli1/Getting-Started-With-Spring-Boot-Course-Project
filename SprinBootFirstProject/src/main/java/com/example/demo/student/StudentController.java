package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")

// the class which will have all of the resources for our API
public class StudentController {

    private final StudentService studentService;

    // to avoid
    // we are saying that studentService(above) should be autowired for us
    // so this will be magically instantion for us
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET method is used to send data from the server to the client via API
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    // POST method is used to send data from the client to the server via API
    @PostMapping
    public void registerNewStudent (@RequestBody Student student) {

        studentService.addNewStudent(student);

    }


    // delete method is used to delete a student by studentId
    // in Postman we typed
    //"http://localhost:8080/api/v1/student/3" to delete student with id 3
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(
            @PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    //put method is used to update a student by studentId
    // in Postman we typed
    //"http://localhost:8080/api/v1/student/2?name=jhon" to update name
    //"http://localhost:8080/api/v1/student/2?email=jhon12@gmail.com" to update email
    // in request url for PUT method
   @PutMapping( path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
            studentService.updateStudent(studentId, name, email);
   }

}

 /* student = new Student();
        student.setName("Bilal");
        student.setEmail("bilal.ahmad@gmail.com");
        student.setDob( LocalDate.of(2003, Month.JANUARY, 5));*/