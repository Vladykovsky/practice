package by.apexintegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DerivativeServiceTest {
    @Autowired
    private DerivativeService derivativeService;

    @Test
    void derivativeTest() throws JsonProcessingException {

        final double STEP = 0.1;
        final double START = -4;
        final double END = 4;
        final double E = 0.001;

        List<Point> points = derivativeService.generatePoints(START, END, STEP);

        Assertions.assertThat(derivativeService.derivative(points)).allMatch(derDiff -> derDiff < STEP + E);

    }
}