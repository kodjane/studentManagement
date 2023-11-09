package com.kadConsulting.studentManagement.student;

import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * Created By
 * @author Aime D. Kodjane
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStudentTest() throws Exception {
        String expectedEmail = "Kodjane@KadConsulting.com";

        mockMvc.perform(get("/api/v1/student/{email}", expectedEmail))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(expectedEmail));
    }

}