
package com.example.logservice;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "log_records")
public class LogRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant ts;
    private String app;
    private String level;

    @Column(length = 2000)
    private String message;

    @Column(length = 4000)
    private String attrsJson; // store attrs as JSON text for simplicity

    public Long getId() { return id; }
    public Instant getTs() { return ts; }
    public void setTs(Instant ts) { this.ts = ts; }
    public String getApp() { return app; }
    public void setApp(String app) { this.app = app; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getAttrsJson() { return attrsJson; }
    public void setAttrsJson(String attrsJson) { this.attrsJson = attrsJson; }
}
