package com.kadConsulting.studentManagement.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.kadConsulting.studentManagement.excetpion.BadRequestException;
import com.kadConsulting.studentManagement.excetpion.StudentNotExistException;

@DataJpaTest
class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;
	private StudentService underTest;

	@BeforeEach
	void setUp() {
		underTest = new StudentService(studentRepository);
	}

	@Test
	void canGetStudents() {

		List<Student> expectedStudents = List.of(
				Student.builder()
						.name("Joshua")
						.email("Joshua@kadConsulting.com")
						.dob(LocalDate.of(2021, Month.JANUARY, 16))
						.build()
				,
				Student.builder()
						.name("Aime")
						.email("Aime@kadConsulting.com")
						.dob(LocalDate.of(2021, Month.MAY, 21))
						.build()
		);

		Mockito.when(studentRepository.findAll()).thenReturn(expectedStudents);

		List<Student> results = underTest.getStudents();

		assertThat(results).hasSize(2)
				.isEqualTo(expectedStudents);
	}

	@Test
	void addStudentThrowsAnExceptionWhenEmailIsTaken() {
		Student expectedStudent = Student.builder()
				.name("Aime")
				.email("Aime@kadConsulting.com")
				.dob(LocalDate.of(1991, Month.MAY, 21))
				.build();

		Mockito.when(studentRepository.findStudentByEmail(expectedStudent.getEmail())).thenReturn(Optional.of(expectedStudent));

		assertThatThrownBy(() -> underTest.addNewStudent(expectedStudent))
				.isExactlyInstanceOf(BadRequestException.class)
				.hasMessage("Email taken");
	}

	@Test
	void canAddStudent() throws BadRequestException {
		Student expectedStudent = Student.builder()
				.name("Aime")
				.email("Aime@kadConsulting.com")
				.dob(LocalDate.of(1991, Month.MAY, 21))
				.build();

		underTest.addNewStudent(expectedStudent);

		ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

		verify(studentRepository).save(studentArgumentCaptor.capture());

		Student studentArgumentCaptorValue = studentArgumentCaptor.getValue();

		assertThat(studentArgumentCaptorValue).isEqualTo(expectedStudent);
	}

	@Test
	void canDeleteThrowExceptionWhenStudentDoesNotExist() {

		long studentId = 1L;
		assertThatThrownBy(() -> underTest.deleteStudent(studentId))
				.hasMessage("Student with id " + studentId + " does not exist")
				.isExactlyInstanceOf(StudentNotExistException.class);
	}

	@Test
	void canDeleteStudent() throws StudentNotExistException {
		long expectedLongValue = 1L;

		Mockito.when(studentRepository.existsById(expectedLongValue)).thenReturn(true);

		underTest.deleteStudent(expectedLongValue);

		ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);

		Mockito.verify(studentRepository).existsById(longArgumentCaptor.capture());

		Long argumentCaptorValue = longArgumentCaptor.getValue();

		assertThat(argumentCaptorValue).isEqualTo(expectedLongValue);
	}
}