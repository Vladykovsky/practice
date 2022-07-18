package by.apexintegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class DerivativeService implements IDerivativeService {

    @Override
    public List<Double> derivative(List<Point> points) throws JsonProcessingException {
        // значения не null, проверить, если точки придут null
        List<Double> yDerivative = this.getFirstDerivative(points);
        List<Double> yDerivativeAnalytic = getFirstDerivativeAnalytics(points);
        List<Double> difference = getDifference(yDerivative, yDerivativeAnalytic);
        return difference;
    }

    private List<Double> getFirstDerivative(List<Point> points) {
        List<Double> yDerivative = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            yDerivative.add((points.get(i+1).getY() - points.get(i).getY()) /
                    (points.get(i+1).getX() - points.get(i).getX()));
        }
        return yDerivative;
    }

    private List<Double> getFirstDerivativeAnalytics(List<Point> points) {
        List<Double> yDerivativeAnalytic = new ArrayList<>();
        for(int i = 0; i < points.size() - 1; i++) {
            yDerivativeAnalytic.add(fDerivative(points.get(i).getX()));
        }
        return yDerivativeAnalytic;
    }

    private List<Double> getDifference(List<Double> yDerivative, List<Double> yDerivativeAnalytic) {
        List<Double> difference = new ArrayList<>();
        for(int i = 0; i < yDerivative.size() - 1; i++ ) {
            difference.add(Math.abs(yDerivativeAnalytic.get(i) - yDerivative.get(i)));
        }
        return difference;
    }

    //-----------Analytic derivative of testing function--------------
    private double fDerivative(double x) {
        return 2*x;
    }

}
