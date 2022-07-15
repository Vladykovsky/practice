package by.apexintegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/process")
public class Controller {
    private final IDerivativeService derivativeService;

    public Controller(IDerivativeService derivativeService) {
        this.derivativeService = derivativeService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }

    @PostMapping("/response")
    @ResponseBody
    public ResponseEntity<String> postResponseController(
            @RequestBody LoginForm loginForm) {
        return new ResponseEntity<String>("Thanks For Posting!!!", HttpStatus.OK);
    }

    @PostMapping("/derivative")
    public ResponseEntity<List<Double>> calculateDerivative() throws JsonProcessingException {
        List<Double> derivativePoints = this.derivativeService.derivative();
        return ResponseEntity.ok(derivativePoints);
    }
}
