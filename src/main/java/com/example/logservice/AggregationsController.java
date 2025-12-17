
package com.example.logservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregationsController {
    // TODO: implement top-apps and error-rate
    @GetMapping("/aggregations/top-apps")
    public ResponseEntity<String> topApps(
            @RequestParam(required=false) String from,
            @RequestParam(required=false) String to,
            @RequestParam(required=false, defaultValue="5") int k) {
        return ResponseEntity.status(501).body("Not Implemented");
    }

    @GetMapping("/aggregations/error-rate")
    public ResponseEntity<String> errorRate(
            @RequestParam String app,
            @RequestParam(required=false, defaultValue="5m") String window) {
        return ResponseEntity.status(501).body("Not Implemented");
    }
}
