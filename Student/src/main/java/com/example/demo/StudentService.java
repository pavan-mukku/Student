package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> listAll(int page, int size) {
        return studentRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Student> get(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public Page<Student> searchByName(String name, int page, int size) {
        return studentRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size));
    }

    public Page<Student> filterByClass(String className, int page, int size) {
        return studentRepository.findByClassName(className, PageRequest.of(page, size));
    }
}
