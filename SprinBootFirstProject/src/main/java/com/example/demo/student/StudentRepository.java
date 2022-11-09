package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//this repository is responsible for data access
//it will fetch students info from student database to Student object
@Repository
public interface StudentRepository
        extends JpaRepository<Student/*object type*/, Long/*id type*/> {

                //SELECT * FROM student WHERE email = ?
                //@Query("SELECT s FROM Student s WHERE s.email = ?1")
                //"s" means Student (we typed  @Entity for it in Student class)
                Optional<Student> findStudentByEmail(String email);
        }
