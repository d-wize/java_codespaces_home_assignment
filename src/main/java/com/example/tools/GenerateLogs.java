
package com.example.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateLogs {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: GenerateLogs <outputFile> <count>");
            System.exit(1);
        }
        String output = args[0];
        int count = Integer.parseInt(args[1]);
        Random rnd = new Random(42);
        String[] apps = {"api-gateway", "auth", "billing", "inventory", "search"};
        String[] levels = {"INFO", "WARN", "ERROR"};
        String[] regions = {"eu-west-1", "us-east-1", "ap-south-1"};
        DateTimeFormatter fmt = DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
            Instant base = Instant.parse("2025-07-01T00:00:00Z");
            for (int i = 0; i < count; i++) {
                // create bursts: every 1000th..1100th record spike ERROR for one app
                String app = apps[rnd.nextInt(apps.length)];
                String level = levels[rnd.nextInt(levels.length)];
                if (i % 1000 >= 1000 && i % 1000 < 1100) {
                    app = "api-gateway";
                    level = "ERROR";
                }
                Instant ts = base.plusSeconds(i * (rnd.nextInt(3) + 1));
                String message = switch (level) {
                    case "ERROR" -> "failed to process request id=" + (100000 + i);
                    case "WARN" -> "retrying operation for user";
                    default -> "request completed";
                };
                String region = regions[rnd.nextInt(regions.length)];
                String json = String.format("{"ts":"%s","app":"%s","level":"%s","message":"%s","attrs":{"region":"%s"}}",
                        fmt.format(ts), app, level, message, region);
                bw.write(json);
                bw.newLine();
            }
        }
        System.out.println("Wrote " + count + " logs to " + output);
    }
}
