package by.apexintegration;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class DerivativeServiceTest {

    @Test
    void derivative() {
       // List<Double> yDerivativeAnalytic = getFirstDerivativeAnalytics(null);
        final double START = -4;
        final double END = 4;
        final double STEP = 0.5;

        List<Double> xArguments = new ArrayList<>();
        for(double x = START; x <= END; x += STEP) {
            xArguments.add(x);
        }

        List<Double> yArguments = new ArrayList<>();
        for(int i = 0; i < xArguments.size() - 1; i++ ) {
            yArguments.add(f(xArguments.get(i)));
        }
    }

    private List<Double> getFirstDerivativeAnalytics(List<Point> points) {
        List<Double> yDerivativeAnalytic = new ArrayList<>();
        for(int i = 0; i < points.size() - 1; i++) {
            yDerivativeAnalytic.add(fDerivative(points.get(i).getX()));
        }
        return yDerivativeAnalytic;
    }
    private double fDerivative(double x) {
        return 2*x;
    }

    private double f(double x) {
        return x*x;
    }
}