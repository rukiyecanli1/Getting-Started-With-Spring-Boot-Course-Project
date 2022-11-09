package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
 public class StudentService {

     private final StudentRepository studentRepository;

      @Autowired
     public StudentService(StudentRepository studentRepository) {
          this.studentRepository = studentRepository;
     }


     public List<Student> getStudents() {
          //will return all info from database as a list
          return studentRepository.findAll();

     /* before repository we return data as a list with below codes
      return List.of(
                new Student(
                        1L,
                        "Mariam",
                        "mariam.jamal@gmail.com",
                        21,
                        LocalDate.of(2000, Month.JANUARY, 5)

                )
        ); // it will return json object [{}]*/
    }

     public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository
                .findStudentByEmail(student.getEmail());
        //if email has already existed in database
        if(studentByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
     }

    public void deleteStudent(Long studentId) {
         boolean exists =  studentRepository.existsById(studentId);
         if(!exists) {
             throw new IllegalStateException(
                     "student with id " + studentId + " does not exist");
         }
         studentRepository.deleteById(studentId);

    }

    // when we have this annotation the entity goes into a manage statement
    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
          Student student = studentRepository.findById(studentId)
                  .orElseThrow(()-> new IllegalStateException(
                          "student with id "+ studentId + "  does not exist"
                  ));
                  if(name != null &&
                          name.length() > 0 &&
                          !Objects.equals(student.getName(), name)) {
                      student.setName(name);
                  }
                  if(email != null &&
                          email.length() > 0 &&
                          !Objects.equals(student.getEmail(), email)) {
                  Optional<Student> studentOptional = studentRepository
                          .findStudentByEmail(email);
                  if(studentOptional.isPresent()) {
                      throw new IllegalStateException("email taken");
                  }
                  student.setEmail(email);
        }
    }

}
