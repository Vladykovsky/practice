package by.apexintegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/process")
public class Controller {
    private final IDerivativeService derivativeService;

    private final ObjectMapper mapper;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }


    @PostMapping("/derivative")
    public ResponseEntity<List<Double>> calculateDerivative() throws JsonProcessingException {
        List<Point> points = this.getInfoFromJSON("array.json");
        List<Double> derivativePoints = this.derivativeService.derivative(points);
        return ResponseEntity.ok(derivativePoints);
    }

    private List<Point> getInfoFromJSON(String fileName) throws JsonProcessingException {
        List<Point> points = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        String result = null;
        if (inputStream != null) {
            result = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
            DataModel dataModel = mapper.readValue(result,DataModel.class);
            points = dataModel.getPoints();
        }
        return points;
    }
}
