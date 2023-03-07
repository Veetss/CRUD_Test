package co.develhope.crud.controllers;

import co.develhope.crud.entities.Student;
import co.develhope.crud.repositories.StudentRepository;
import co.develhope.crud.services.StudentService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public @ResponseBody
    Student createStudents(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping("/read")
    public @ResponseBody
    List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody  Student getSingleStudent(@PathVariable long id){
        Optional<Student> student =  studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }else{
            return null;
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody Student updateStudents(@PathVariable long id, @RequestBody @NotNull Student student){
        student.setId(id);
        return studentRepository.save(student);
    }

    @PutMapping("/{id}/work")
    public @ResponseBody Student setStudentIsWorking(@PathVariable long id, @RequestParam("working") boolean working){
        return studentService.setStudentIsWorkingStatus(id, working);
    }

    @DeleteMapping("/{id}")
    public void deleteStudents(@PathVariable long id){
        studentRepository.deleteById(id);
    }

}