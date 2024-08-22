# Gradle

## basics

```bash
# to create the gradlew command
./gradle wrapper

# clean compile execute contract and integration tests
./gradlew clean build contractTest acceptanceTest

# clean compile execute contract and integration tests and show the logs
./gradlew clean build contractTest acceptanceTest -d
 
# stop gradlew execution
./gradlew --stop
```

To watch the dependency tree
```bash
./gradlew dependencies >> dependencyReport.txt
```

If setup the `id("project-report")` in the plugin section do the same too
```bash
./gradlew dependencyReport
```

Execute gradle without cache
```bash
./gradlew clean build integrationTest --no-configuration-cache
```