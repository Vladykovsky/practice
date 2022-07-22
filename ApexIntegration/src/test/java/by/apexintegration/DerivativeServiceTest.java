package by.apexintegration;

import by.apexintegration.exceptions.CalculationException;
import by.apexintegration.model.DerivativeSignal;
import by.apexintegration.model.DerivativeType;
import by.apexintegration.model.Point;
import by.apexintegration.model.Signal;
import by.apexintegration.service.DerivativeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

@SpringBootTest
public class DerivativeServiceTest {

    @Autowired
    private DerivativeService derivativeService;


    @Test
    void firstDerivativeTest() throws JsonProcessingException {

        final double STEP = 0.005;
        final double START = -4;
        final double END = 4;
        final double E = 0.1;
        UnaryOperator<Double> f = x -> x*x*x;
        UnaryOperator<Double> df = x -> 3*x*x;
        Signal signal = generateSignal("x*x*x", START, END, STEP, f); // x*x?
        try {
            DerivativeSignal derivativeSignal = derivativeService.calculatedFirstDerivative(signal);
            DerivativeSignal derivativeAnalytics = getDerivativeAnalytics(signal, df);
            List<Double> diff = getDifference(derivativeSignal, derivativeAnalytics);
            System.out.println(diff);
            Assertions.assertThat(diff).allMatch(derDiff -> derDiff < E);

        } catch (CalculationException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    void secondDerivativeTest() {
        final double STEP = 0.005;
        final double START = -4;
        final double END = 4;
        final double E = 0.1;
        UnaryOperator<Double> f = x -> x*x*x;
        UnaryOperator<Double> ddf = x -> 6*x;
        Signal signal = generateSignal("x*x*x", START, END, STEP, f);

        try {
            DerivativeSignal derivativeSignal = derivativeService.calculateSecondDerivative(signal);
            DerivativeSignal derivativeAnalytics = getDerivativeAnalytics(signal, ddf);
            List<Double> diff = getDifference(derivativeSignal, derivativeAnalytics);
            System.out.println(diff);
            Assertions.assertThat(diff).allMatch(derDiff -> derDiff < E);

        } catch (CalculationException e) {
            e.printStackTrace();
        }

//        Assertions.assertThat(getDifference(yyDerivative, yyDerivativeAnalytics)).allMatch(derDiff -> derDiff < 6*STEP + E);
    }

    private List<Double> getDifference(DerivativeSignal derivativeSignal, DerivativeSignal derivativeSignalAnalytic) {
        List<Double> difference = new ArrayList<>();
        for(int i = 0; i < derivativeSignal.getPoints().size() - 1; i++ ) {
            difference.add(Math.abs(derivativeSignal.getPoints().get(i).getY()
                    - derivativeSignalAnalytic.getPoints().get(i).getY()));
        }
        return difference;
    }

    private DerivativeSignal getDerivativeAnalytics(Signal signal, UnaryOperator<Double> fDerivative) {
        List<Point> points = signal.getPoints();
        List<Point> derivativePoints = new ArrayList<>();
        for(int i = 0; i < points.size() - 1; i++) {
            Double y = fDerivative.apply(points.get(i).getX());
            derivativePoints.add(new Point(points.get(i).getX(), y));
        }
       return DerivativeSignal
                .builder()
                .name("Derivative Analytic " + signal.getName())
                .points(derivativePoints)
                .build();
    }

    private Signal generateSignal(String name,
                                  Double start,
                                  Double end,
                                  Double step,
                                  UnaryOperator<Double> f) {
        return Signal
                .builder()
                .name(name)
                .points(generatePoints(start, end, step, f))
                .build();
    }

    private List<Point> generatePoints(double start, double end, double step, UnaryOperator<Double> f) {
        List<Point> points = new ArrayList<Point>();
        for(double i = start; i <= end; i+= step) {
            Point p = new Point();
            p.setX(i);
            p.setY(f.apply(i));
            points.add(p);
        }
        return points;
    }

}