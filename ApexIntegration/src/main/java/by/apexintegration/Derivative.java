package by.apexintegration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Derivative {
    public static void main(String[] args) {
        //-----------Custom implementation derivative--------------
        //-----------Define testings bounds--------------
        final double START = -4;
        final double END = 4;
        final double STEP = 0.01;
        List<Double> xArguments = new ArrayList<>();
        //-----------Generate x values--------------
        for(double x = START; x <= END; x += STEP) {
            xArguments.add(x);
        }
        //-----------Generate f(x) values--------------
        List<Double> yArguments = new ArrayList<>();
        for(int i = 0; i < xArguments.size() - 1; i++ ) {
            yArguments.add(f(xArguments.get(i)));
        }
        System.out.println("x values");
        System.out.println(xArguments.stream().collect(Collectors.toList()));
        System.out.println("F(x) values");
        System.out.println(yArguments.stream().collect(Collectors.toList()));

        //-----------Calculated derivative using diff function--------------
        List<Double> yDerivative = new ArrayList<>();
        for(int i = 0; i < yArguments.size() - 1; i++ ) {
            yDerivative.add((yArguments.get(i + 1) - yArguments.get(i)) / STEP);
        }
        System.out.println("F'(x) values");
        System.out.println(yDerivative.stream().collect(Collectors.toList()));



        //-----------Calculate derivative for  analytical der. function--------------
        List<Double> yDerivativeAnalytic = new ArrayList<>();
        for(int i = 0; i < xArguments.size() - 1; i++ ) {
            yDerivativeAnalytic.add(fDerivative(xArguments.get(i)));
        }
        System.out.println("F'(x) analytical values");
        System.out.println(yDerivativeAnalytic.stream().collect(Collectors.toList()));

        //-----------Compare analytical and custom derivative calculation--------------
        List<Double> difference = new ArrayList<>();
        for(int i = 0; i < yDerivative.size() - 1; i++ ) {
            difference.add(Math.abs(yDerivativeAnalytic.get(i) - yDerivative.get(i)));
        }
        System.out.println("difference in each element");
        System.out.println(difference.stream().collect(Collectors.toList()));

    }

    //-----------Define testings function--------------
    public static double f(double x) {
        return x*x;
    }

    //-----------Analytic derivative of testing function--------------
    public static double fDerivative(double x) {
        return 2*x;
    }
}
