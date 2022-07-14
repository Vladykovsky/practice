package by.apexintegration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/process")
public class Controller {
//    private final String sharedKey = "SHARED_KEY";
//    private static final String SUCCESS_STATUS = "success";
//    private static final String ERROR_STATUS = "error";
//    private static final int CODE_SUCCESS = 100;
//    private static final int AUTH_FAILURE = 102;

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
    @ResponseBody
    public ResponseEntity<List<Point>> calculateDerivative(
            @RequestBody List<Point> points) {
        List<Point> derivativePoints = this.derivativeService.derivative(points);
        return new ResponseEntity<List<Point>>(derivativePoints, HttpStatus.OK);
    }
}
