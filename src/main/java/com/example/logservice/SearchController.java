
package com.example.logservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    // TODO: implement filters and pagination
    @GetMapping("/search")
    public ResponseEntity<String> search(
            @RequestParam(required=false) String app,
            @RequestParam(required=false) String level,
            @RequestParam(required=false) String q,
            @RequestParam(required=false) String from,
            @RequestParam(required=false) String to,
            @RequestParam(required=false, defaultValue="50") int limit,
            @RequestParam(required=false, defaultValue="0") int offset) {
        return ResponseEntity.status(501).body("Not Implemented");
    }
}
