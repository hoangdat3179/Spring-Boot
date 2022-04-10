package vn.techmaster.miniproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.miniproject.request.BmiRequest;
import vn.techmaster.miniproject.service.BmiService;

@RestController
@AllArgsConstructor
public class BmiController {
    private BmiService bmiService;
    // Get
    @GetMapping("/bmi")
    public ResponseEntity<?> calculateBmiGet(@RequestParam double height,@RequestParam double weight){
        double bmi = bmiService.calculateBmi(height,weight);
        return ResponseEntity.ok(bmi);
    }

    //Post
    @PostMapping("/bmi")
    public ResponseEntity<?> calculateBmiPost(@RequestBody BmiRequest bmiRequest){
        double bmi = bmiService.calculateBmi(bmiRequest.getHeight(), bmiRequest.getWeight());
        return ResponseEntity.ok(bmi);
    }
}
