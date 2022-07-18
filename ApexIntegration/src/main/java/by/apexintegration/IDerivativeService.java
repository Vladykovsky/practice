package by.apexintegration;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IDerivativeService {
    List<Double> derivative(List<Point> points) throws JsonProcessingException;
}
