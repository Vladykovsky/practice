package by.apexintegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Service
public class DerivativeService implements IDerivativeService {

    @Override
    public List<Double> derivative(List<Point> points) throws JsonProcessingException {
        List<Double> yDerivative = this.getFirstDerivative(points);
        List<Double> yDerivativeAnalytic = getFirstDerivativeAnalytics(points);
        List<Double> yyDerivative = getSecondDerivative(points);
        List<Double> yyDerivativeAnalytic = getSecondDerivativeAnalytics(points);
        List<Double> difference = getDifference(yDerivative, yDerivativeAnalytic);
        return difference;
        List<Double> secondDifference = getSecondDifference(yyDerivative, yyDerivativeAnalytic);
        return secondDifference;
    }

    private List<Double> getFirstDerivative(List<Point> points) {
        List<Double> yDerivative = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            yDerivative.add((points.get(i+1).getY() - points.get(i).getY()) /
                    (points.get(i+1).getX() - points.get(i).getX()));
        }
        return yDerivative;
    }

    private List<Double> getSecondDerivative(List<Point> points) {
        List<Double> yyDerivative = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            yyDerivative.add((points.get(i+1).getY() - points.get(i).getY()) /
                    (points.get(i+1).getX() - points.get(i).getX()));
        }
        return yyDerivative;
    }

    private List<Double> getFirstDerivativeAnalytics(List<Point> points) {
        List<Double> yDerivativeAnalytic = new ArrayList<>();
        for(int i = 0; i < points.size() - 1; i++) {
            yDerivativeAnalytic.add(fDerivative(points.get(i).getX()));
        }
        return yDerivativeAnalytic;
    }

    private List<Double> getSecondDerivativeAnalytics(List<Point> points) {
        List<Double> yyDerivativeAnalytic = new ArrayList<>();
        for(int i = 0; i < points.size() - 1; i++) {
            yyDerivativeAnalytic.add(sDerivative(points.get(i).getX()));
        }
        return yyDerivativeAnalytic;
    }

    private List<Double> getDifference(List<Double> yDerivative, List<Double> yDerivativeAnalytic) {
        List<Double> difference = new ArrayList<>();
        for(int i = 0; i < yDerivative.size() - 1; i++ ) {
            difference.add(Math.abs(yDerivativeAnalytic.get(i) - yDerivative.get(i)));
        }
        return difference;
    }

    private List<Double> getSecondDifference(List<Double> yyDerivative, List<Double> yyDerivativeAnalytic) {
        List<Double> secondDifference = new ArrayList<>();
        for(int i = 0; i < yyDerivative.size() - 1; i++ ) {
            secondDifference.add(Math.abs(yyDerivativeAnalytic.get(i) - yyDerivative.get(i)));
        }
        return secondDifference;
    }

    public List<Point> generatePoints(double start, double end, double step) {
        List<Point> points = new ArrayList<Point>();
        for(double i = start; i <= end; i+= step) {
            Point p = new Point();
            p.setX(i);
            p.setY(f(i));
            points.add(p);
        }
        return points;
    }

    //-----------Testing functions--------------

    private double fDerivative(double x) { return 3*x*x;}

    private double f(double x) {
        return x*x*x;
    }

    private double sDerivative(double x) { return 6*x; }

}
