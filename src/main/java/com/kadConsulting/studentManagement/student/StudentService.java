package com.kadConsulting.studentManagement.student;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kadConsulting.studentManagement.excetpion.BadRequestException;
import com.kadConsulting.studentManagement.excetpion.StudentNotExistException;

@Service
public class StudentService {
	private StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) throws BadRequestException {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

		if (studentOptional.isPresent())
			throw new BadRequestException("Email taken");

		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) throws StudentNotExistException {
		boolean exists = studentRepository.existsById(studentId);

		if (!exists){
			throw new StudentNotExistException("Student with id " + studentId + " does not exist");
		}

		studentRepository.deleteById(studentId);
	}

	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId).
				orElseThrow(() -> new IllegalArgumentException("student with id " + studentId + " does not exist"));

		if (name != null && name.length() > 0 && !name.equals(student.getName())){
			student.setName(name);
			studentRepository.save(student);
		}

		if (email != null && email.length() > 0 && !email.equals(student.getEmail())){
			student.setEmail(email);
			studentRepository.save(student);
		}
	}

	public Optional<Student> getStudentByName(String email) {
		return studentRepository.findStudentByEmail(email);
	}
}
