package com.kadConsulting.studentManagement.student;

import java.util.List;
import java.util.Optional;

import com.kadConsulting.studentManagement.excetpion.StudentErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kadConsulting.studentManagement.excetpion.BadRequestException;
import com.kadConsulting.studentManagement.excetpion.StudentNotExistException;

import static java.lang.System.currentTimeMillis;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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

	@GetMapping(path = "/students/{email}")
	public ResponseEntity<Student> getStudent(@PathVariable String email) {
		return ResponseEntity.ok(studentService.getStudentByName(email).orElseGet(Student::new));
	}

	@GetMapping(path = "/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) throws StudentNotExistException {
		Optional<Student> optionalStudent = studentService.getStudentById(id);

		if (optionalStudent.isEmpty())
			throw new StudentNotExistException("Student Not Found");

		return ResponseEntity.ok(optionalStudent.get());
	}

	@GetMapping(path = "/students")
	public ResponseEntity<List<Student>> getStudents(){
		return ResponseEntity.ok(studentService.getStudents());
	}


	@PostMapping(path = "/students")
	public void registerStudent(@RequestBody Student student) throws BadRequestException {
		studentService.addNewStudent(student);
	}

	@DeleteMapping(path = "/students/{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId) throws StudentNotExistException {
		studentService.deleteStudent(studentId);
	}

	@PutMapping(path = "/students/{studentId}")
	public void updateStudent(@PathVariable("studentId") Long studentId,
							  @RequestParam(required = false) String name,
							  @RequestParam(required = false) String email){
		studentService.updateStudent(studentId, name, email);

	}
}
