
package com.example.logservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnomaliesController {
    // TODO: implement anomaly detection (mu + alpha*sigma over buckets)
    @GetMapping("/anomalies")
    public ResponseEntity<String> anomalies(
            @RequestParam(required=false) String app,
            @RequestParam(required=false) String from,
            @RequestParam(required=false) String to,
            @RequestParam(required=false, defaultValue="3.0") double alpha,
            @RequestParam(required=false, defaultValue="60") int windowSec) {
        return ResponseEntity.status(501).body("Not Implemented");
    }
}
