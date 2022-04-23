package vn.sapo.demo.rest;

import lombok.Getter;
import lombok.Setter;
import vn.sapo.demo.dal.CalculatorHistory;

import java.time.Instant;

@Getter
@Setter
public class CalculatorHistoryRequest {
    private Instant instant;
    private CalculatorHistory.Operator operator;
}
