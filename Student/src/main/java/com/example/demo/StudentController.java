package com.example.demo;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listStudents(@RequestParam(defaultValue = "0") int page,
                                Model model) {
        Page<Student> studentPage = studentService.listAll(page, 5);
        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "students/list";
    }

    @GetMapping("/add")
    public String showAddForm(Student student) {
        return "students/add";
    }

    @PostMapping("/add")
    public String addStudent(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "students/add";
        }
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.get(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        model.addAttribute("student", student);
        return "students/edit";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, @Valid Student student,
                                BindingResult result) {
        if (result.hasErrors()) {
            student.setStudentId(id);
            return "students/edit";
        }
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, @RequestParam(defaultValue = "0") int page,
                         Model model) {
        Page<Student> studentPage = studentService.searchByName(name, page, 5);
        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "students/list";
    }
}
