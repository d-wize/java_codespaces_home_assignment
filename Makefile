
.PHONY: bootstrap test run generate clean

bootstrap:
	mvn -q -DskipTests package

test:
	mvn -q test

run:
	mvn -q spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=8080"

generate:
	mvn -q -DskipTests exec:java -Dexec.mainClass=com.example.tools.GenerateLogs -Dexec.args="data/logs.ndjson 5000"

clean:
	mvn -q clean

echo-endpoints:
	@echo "POST /ingest (NDJSON)"
	@echo "GET  /healthz"
	@echo "GET  /search"
	@echo "GET  /aggregations/top-apps"
	@echo "GET  /aggregations/error-rate"
	@echo "GET  /anomalies"
