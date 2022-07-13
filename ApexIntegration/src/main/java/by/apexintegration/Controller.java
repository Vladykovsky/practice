package by.apexintegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process")
public class Controller {
    private final String sharedKey = "SHARED_KEY";
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int AUTH_FAILURE = 102;

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
}
