package com.example.jpademo.api;

import com.example.jpademo.entity.Student;
import com.example.jpademo.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student entity = studentRepository.save(student);
        return ResponseEntity.created(null).body(entity);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/test")
    public void test() {
        studentRepository.doSomething();
    }
}
