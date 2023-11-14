package com.kadConsulting.studentManagement.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kadConsulting.studentManagement.excetpion.BadRequestException;
import com.kadConsulting.studentManagement.excetpion.StudentNotExistException;

@RestController
@RequestMapping(path = "/api/v1")
public class StudentController {

	private final StudentService studentService;
	@Value("${author.name}")
	private String authorName;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping(path = "/home")
	public String devToolTest(){
		return "it works " + authorName + " :)";
	}

	@GetMapping(path = "/student/{email}")
	public ResponseEntity<Student> getStudent(@PathVariable String email) {
		return ResponseEntity.ok(studentService.getStudentByName(email).orElseGet(Student::new));
	}

	@GetMapping(path = "/students")
	public ResponseEntity<List<Student>> getStudents(){
		return ResponseEntity.ok(studentService.getStudents());
	}


	@PostMapping
	public void registerStudent(@RequestBody Student student) throws BadRequestException {
		studentService.addNewStudent(student);
	}

	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId) throws StudentNotExistException {
		studentService.deleteStudent(studentId);
	}

	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId") Long studentId,
							  @RequestParam(required = false) String name,
							  @RequestParam(required = false) String email){
		studentService.updateStudent(studentId, name, email);

	}
}
