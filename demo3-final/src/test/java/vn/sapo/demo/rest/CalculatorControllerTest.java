package vn.sapo.demo.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import vn.sapo.demo.CalculatorService;
import vn.sapo.demo.dal.CalculatorHistory;

import javax.print.attribute.standard.Media;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    CalculatorService calculatorService;

    @Test
    void shouldPlusSuccess() throws Exception {

        var json = "{\"firstNumber\": 1, \"secondNumber\": 2}";

        when(calculatorService.plus(eq(1), eq(2))).thenReturn(3);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/plus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(3));

    }

    @Test
    void shouldMinusSuccess() throws Exception {
        var json = "{\"firstNumber\": 2, \"secondNumber\": 1}";

        when(calculatorService.minus(eq(2), eq(1))).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/minus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1));

    }

    @Test
    void shouldMultipliedSuccess() throws Exception {
        var json = "{\"firstNumber\": 3, \"secondNumber\": 2}";

        when(calculatorService.multiplied(eq(3), eq(2))).thenReturn(6);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/multiplied")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(6));

    }

    @Test
    void shouldDividedSuccess() throws Exception {
        var json = "{\"firstNumber\": 4, \"secondNumber\": 2}";

        when(calculatorService.divided(eq(4), eq(2))).thenReturn(2);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/divided")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(2));

    }

    @Test
    void shouldGetCalculatorHistoriesSuccess() throws Exception {
        Instant instant = Instant.now();
        var json = "{\"instant\": 2022-04-23T18:35:24.00Z, \"operator\": PLUS}";

        when(calculatorService.getCalculator(eq(instant), eq(CalculatorHistory.Operator.PLUS))).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.histories").value(new ArrayList<>()));
    }

}