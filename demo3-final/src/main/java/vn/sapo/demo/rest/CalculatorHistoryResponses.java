package vn.sapo.demo.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.sapo.demo.dal.CalculatorHistory;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class CalculatorHistoryResponses {
    private List<CalculatorHistory> histories;
    public CalculatorHistoryResponses(List<CalculatorHistory> histories) {
        this.histories = histories;
    }
}
