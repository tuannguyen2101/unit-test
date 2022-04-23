package vn.sapo.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import vn.sapo.demo.dal.CalculatorHistory;
import vn.sapo.demo.dal.CalculatorHistoryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(AppTestConfiguration.class)
@DataJpaTest
class CalculatorServiceTest {

    @Autowired
    CacheService cacheService;
    @Autowired
    CalculatorHistoryRepository calculatorHistoryRepository;

    @Test
    void shouldPlusSuccess() {
        var service = new CalculatorService(cacheService, calculatorHistoryRepository);
        var firstNumber = 1;
        var secondNumber = 2;
        //when
        var result = service.plus(firstNumber, secondNumber);
        //then
        assertEquals(3, result);

        var data = calculatorHistoryRepository.findAll();
        assertEquals(1, data.size());
        assertEquals(3, data.get(0).getResult());
        assertEquals(1, data.get(0).getFirstNumber());
        assertEquals(2, data.get(0).getSecondNumber());
        assertEquals(CalculatorHistory.Operator.PLUS, data.get(0).getOperator());
    }

    @Test
    void shouldMinusSuccess() {
        var service = new CalculatorService(cacheService, calculatorHistoryRepository);
        var firstNumber = 2;
        var secondNumber = 1;
        //when
        var result = service.minus(firstNumber, secondNumber);
        //then
        assertEquals(1, result);

        var data = calculatorHistoryRepository.findAll();
        assertEquals(1, data.size());
        assertEquals(1, data.get(0).getResult());
        assertEquals(2, data.get(0).getFirstNumber());
        assertEquals(1, data.get(0).getSecondNumber());
        assertEquals(CalculatorHistory.Operator.MINUS, data.get(0).getOperator());
    }

    @Test
    void shouldMultipliedSuccess() {
        var service = new CalculatorService(cacheService, calculatorHistoryRepository);
        var firstNumber = 3;
        var secondNumber = 2;
        //when
        var result = service.multiplied(firstNumber, secondNumber);
        //then
        assertEquals(6, result);

        var data = calculatorHistoryRepository.findAll();
        assertEquals(1, data.size());
        assertEquals(6, data.get(0).getResult());
        assertEquals(3, data.get(0).getFirstNumber());
        assertEquals(2, data.get(0).getSecondNumber());
        assertEquals(CalculatorHistory.Operator.MULTIPLIED, data.get(0).getOperator());
    }

    @Test
    void shouldDividedSuccess() {
        var service = new CalculatorService(cacheService, calculatorHistoryRepository);
        var firstNumber = 4;
        var secondNumber = 2;
        //when
        var result = service.divided(firstNumber, secondNumber);
        //then
        assertEquals(2, result);

        var data = calculatorHistoryRepository.findAll();
        assertEquals(1, data.size());
        assertEquals(2, data.get(0).getResult());
        assertEquals(4, data.get(0).getFirstNumber());
        assertEquals(2, data.get(0).getSecondNumber());
        assertEquals(CalculatorHistory.Operator.DIVIDED, data.get(0).getOperator());
    }

}