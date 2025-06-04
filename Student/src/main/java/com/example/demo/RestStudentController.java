package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class RestStudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Page<Student> list(@RequestParam(defaultValue = "0") int page) {
        return studentService.listAll(page, 5);
    }

    @GetMapping("/{id}")
    public Optional<Student> get(@PathVariable Long id) {
        return studentService.get(id);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        student.setStudentId(id);
        return studentService.save(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

    @GetMapping("/search")
    public Page<Student> search(@RequestParam String name, @RequestParam(defaultValue = "0") int page) {
        return studentService.searchByName(name, page, 5);
    }
}
