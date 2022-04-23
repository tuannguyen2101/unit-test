package vn.sapo.demo.rest;

import org.springframework.web.bind.annotation.*;
import vn.sapo.demo.CalculatorService;
import vn.sapo.demo.dal.CalculatorHistory;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/plus")
    public CalculatorResponse plus(@RequestBody CalculatorRequest request) {
        var result = calculatorService.plus(request.getFirstNumber(), request.getSecondNumber());
        return new CalculatorResponse(result);
    }

    @PostMapping("/minus")
    public CalculatorResponse minus(@RequestBody CalculatorRequest request) {
        var result = calculatorService.minus(request.getFirstNumber(), request.getSecondNumber());
        return new CalculatorResponse(result);
    }

    @PostMapping("/multiplied")
    public CalculatorResponse multiplied(@RequestBody CalculatorRequest request) {
        var result = calculatorService.multiplied(request.getFirstNumber(), request.getSecondNumber());
        return new CalculatorResponse(result);
    }

    @PostMapping("/divided")
    public CalculatorResponse divided(@RequestBody CalculatorRequest request) {
        var result = calculatorService.divided(request.getFirstNumber(), request.getSecondNumber());
        return new CalculatorResponse(result);
    }

    @GetMapping("/history")
    public List<CalculatorHistory> get(@RequestBody CalculatorHistoryRequest request) {
        return calculatorService.getCalculator(request.getInstant(), request.getOperator());
    }

}
