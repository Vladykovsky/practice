package by.apexintegration.controller;

import by.apexintegration.exceptions.CalculationException;
import by.apexintegration.model.DerivativeSignal;
import by.apexintegration.model.Signal;
import by.apexintegration.model.Point;
import by.apexintegration.service.IDerivativeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/process")
public class IntegratorController {
    private final IDerivativeService derivativeService;

    private final ObjectMapper mapper;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }


    @PostMapping("/derivative/first/file")
    public ResponseEntity<DerivativeSignal> calculateDerivative() throws CalculationException, JsonProcessingException {
        List<Point> points = this.getInfoFromJSON("array.json");
        Signal signal = Signal.builder()
                .name("File signal")
                .points(points)
                .build();
        DerivativeSignal derivativePoints = this.derivativeService.calculatedFirstDerivative(signal);
        return ResponseEntity.ok(derivativePoints);
    }


    @PostMapping("/derivative/first")
    public ResponseEntity<DerivativeSignal> derivativeFirst(@RequestBody @NotNull Signal signal) throws CalculationException {
        DerivativeSignal derivativePoints = this.derivativeService.calculatedFirstDerivative(signal);
        return ResponseEntity.ok(derivativePoints);
    }

    @PostMapping("/derivative/second")
    public ResponseEntity<DerivativeSignal> derivativeSecond(@RequestBody @NotNull Signal signal) throws CalculationException {
        DerivativeSignal derivativePoints = this.derivativeService.calculateSecondDerivative(signal);
        return ResponseEntity.ok(derivativePoints);
    }


    private List<Point> getInfoFromJSON(String fileName) throws JsonProcessingException {
        List<Point> points = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        String result = null;
        if (inputStream != null) {
            result = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
            Signal dataModel = mapper.readValue(result, Signal.class);
            points = dataModel.getPoints();
        }
        return points;
    }
}
