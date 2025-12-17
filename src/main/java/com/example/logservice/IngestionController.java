
package com.example.logservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;

@RestController
public class IngestionController {
    private final LogRecordRepository repo;
    private final ObjectMapper mapper = new ObjectMapper();

    public IngestionController(LogRecordRepository repo) {
        this.repo = repo;
    }

    @PostMapping(value = "/ingest", consumes = MediaType.APPLICATION_NDJSON_VALUE)
    @Transactional
    public ResponseEntity<String> ingestNdjson(@RequestBody String ndjsonBody) throws IOException {
        int processed = 0;
        try (BufferedReader br = new BufferedReader(new StringReader(ndjsonBody))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                JsonNode node = mapper.readTree(line);
                LogRecord r = new LogRecord();
                // parse fields with minimal validation
                String tsStr = node.path("ts").asText(null);
                if (tsStr != null && !tsStr.isEmpty()) {
                    try { r.setTs(Instant.parse(tsStr)); } catch (Exception e) { r.setTs(null); }
                }
                r.setApp(node.path("app").asText("unknown"));
                r.setLevel(node.path("level").asText("INFO"));
                r.setMessage(node.path("message").asText(""));
                JsonNode attrs = node.path("attrs");
                if (!attrs.isMissingNode()) {
                    r.setAttrsJson(attrs.toString());
                }
                repo.save(r);
                processed++;
            }
        }
        return ResponseEntity.ok("processed=" + processed);
    }
}
