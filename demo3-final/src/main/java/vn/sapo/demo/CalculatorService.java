package vn.sapo.demo;

import org.springframework.stereotype.Service;
import vn.sapo.demo.dal.CalculatorHistory;
import vn.sapo.demo.dal.CalculatorHistoryRepository;

import java.time.Instant;
import java.util.List;

@Service
public class CalculatorService {

    private final CacheService cacheService;
    private final CalculatorHistoryRepository calculatorHistoryRepository;

    public CalculatorService(CacheService cacheService,
                             CalculatorHistoryRepository calculatorHistoryRepository) {
        this.cacheService = cacheService;
        this.calculatorHistoryRepository = calculatorHistoryRepository;
    }

    public int plus(int firstNumber, int secondNumber) {
        var cacheKey = firstNumber + "_plus_" + secondNumber;
        var data = cacheService.get(cacheKey);
        if (data != null) {
            saveHistory(firstNumber, secondNumber, data, CalculatorHistory.Operator.PLUS);
            return data;
        }

        var result = firstNumber + secondNumber;

        saveHistory(firstNumber, secondNumber, result, CalculatorHistory.Operator.PLUS);
        cacheService.set(cacheKey, result);
        return result;
    }

    public int minus(int firstNumber, int secondNumber) {
        var cacheKey = firstNumber + "_minus_" + secondNumber;
        var data = cacheService.get(cacheKey);
        if (data != null) {
            saveHistory(firstNumber, secondNumber, data, CalculatorHistory.Operator.MINUS);
            return data;
        }

        var result = firstNumber - secondNumber;

        saveHistory(firstNumber, secondNumber, result, CalculatorHistory.Operator.MINUS);
        cacheService.set(cacheKey, result);
        return result;
    }

    public int multiplied(int firstNumber, int secondNumber) {
        var cacheKey = firstNumber + "_multiplied_" + secondNumber;
        var data = cacheService.get(cacheKey);
        if (data != null) {
            saveHistory(firstNumber, secondNumber, data, CalculatorHistory.Operator.MULTIPLIED);
            return data;
        }

        var result = firstNumber * secondNumber;

        saveHistory(firstNumber, secondNumber, result, CalculatorHistory.Operator.MULTIPLIED);
        cacheService.set(cacheKey, result);
        return result;
    }

    public int divided(int firstNumber, int secondNumber) {
        var cacheKey = firstNumber + "_divided_" + secondNumber;
        var data = cacheService.get(cacheKey);
        if (data != null) {
            saveHistory(firstNumber, secondNumber, data, CalculatorHistory.Operator.DIVIDED);
            return data;
        }

        var result = firstNumber / secondNumber;

        saveHistory(firstNumber, secondNumber, result, CalculatorHistory.Operator.DIVIDED);
        cacheService.set(cacheKey, result);
        return result;
    }

    private void saveHistory(int firstNumber, int secondNumber, int result, CalculatorHistory.Operator operator) {
        var history = new CalculatorHistory();
        history.setFirstNumber(firstNumber);
        history.setSecondNumber(secondNumber);
        history.setOperator(operator);
        history.setResult(result);
        calculatorHistoryRepository.save(history);
        System.out.println(history);
    }

    public List<CalculatorHistory> getCalculator(Instant instant, CalculatorHistory.Operator operator) {
        return calculatorHistoryRepository.getCalculatorHistoryByCreatedAtAndOperator(instant, operator);
    }

}
