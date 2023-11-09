package com.kadConsulting.studentManagement.student;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
	@Id
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	@Transient
	private Integer age;

	@Override
	public String toString() {
		return "Student{" +
			   "id=" + id +
			   ", name='" + name + '\'' +
			   ", email='" + email + '\'' +
			   ", dob=" + dob +
			   ", age=" + age +
			   '}';
	}
}
