package com.kadConsulting.studentManagement.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
//@EnableSwagger2
public class studentConfiguration {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student kodjane = Student.builder()
					.name("Kodjane")
					.email("Kodjane@KadConsulting.com")
					.dob(LocalDate.of(1991, Month.MAY, 21))
					.build();

			Student dusenge = Student.builder()
					.name("Dusenge")
					.email("Dusenge@KadConsulting.com")
					.dob(LocalDate.of(1992, Month.MARCH, 26))
					.build();

			studentRepository.saveAll(List.of(kodjane, dusenge));
		};
	}

//	@Bean
//	public Docket postsApi() {
//		return new Docket(DocumentationType.SWAGGER_2).groupName("Kad Consulting").apiInfo(apiInfo()).select()
//				.paths(regex("/api/v1.*")).build();
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("Student Service")
//				.description("Sample Documentation Generateed Using SWAGGER2 for our Student Rest API")
//				.termsOfServiceUrl("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ")
//				.license("Kad Consulting License")
//				.licenseUrl("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ").version("1.0").build();
//	}
}
