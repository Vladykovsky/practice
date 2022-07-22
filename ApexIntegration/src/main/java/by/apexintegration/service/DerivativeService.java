package by.apexintegration.service;

import by.apexintegration.exceptions.CalculationException;
import by.apexintegration.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@Service
public class DerivativeService implements IDerivativeService {

    @Override
    public DerivativeSignal calculatedFirstDerivative(Signal signal) throws CalculationException {
        if (Objects.isNull(signal) || Objects.isNull(signal.getPoints())) {
            throw new CalculationException("Signal data is null");
        }
        try {
            List<Point> yDerivativePoints = calculateSlopes(signal.getPoints());
            return DerivativeSignal
                    .builder()
                    .name("Derivative 1 of " + signal.getName())
                    .type(DerivativeType.FIRST)
                    .points(yDerivativePoints)
                    .build();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new CalculationException(ex.getMessage());
        }
    }

    @Override
    public DerivativeSignal calculateSecondDerivative(Signal signal) throws CalculationException {
        DerivativeSignal firstDerivative = this.calculatedFirstDerivative(signal);
        if (Objects.isNull(firstDerivative) || Objects.isNull(firstDerivative.getPoints())) {
            throw new CalculationException("First derivative data is null");
        }
        try {
            List<Point> yDerivativeSecondPoints = calculateSlopes(firstDerivative.getPoints()); // почему аргумент вызова начальные данные?
            return DerivativeSignal
                    .builder()
                    .name("Derivative 2 of " + signal.getName())
                    .type(DerivativeType.SECOND)
                    .points(yDerivativeSecondPoints)
                    .build();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new CalculationException(ex.getMessage());
        }
    }

    @Override
    public ApexSignal foundApex(DerivativeSignal signal) throws CalculationException {
        if (Objects.isNull(signal.getPoints())) {
            throw new CalculationException("Derivative data is null");
        }
        try {
            List<Point> apex = new ArrayList<>();
            List<Point> derivative = signal.getPoints();
            for (int i = 1; i < derivative.size() - 1; i++) {
                if (derivative.get(i - 1).getY() > 0 && derivative.get(i + 1).getY() < 0) {
                    apex.add(derivative.get(i));
                }
            }
            return ApexSignal
                    .builder()
                    .name("Apex of " + signal.getName())
                    .points(apex)
                    .build();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new CalculationException(ex.getMessage());
        }
    }

    private List<Point> calculateSlopes(List<Point> points) {
        List<Point> slopePoints = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            Double yValueOfPointSlope =  (points.get(i+1).getY() - points.get(i).getY()) /
                    (points.get(i+1).getX() - points.get(i).getX());
            slopePoints.add(new Point(points.get(i).getX(), yValueOfPointSlope));
        }
        return slopePoints;
    }
}
