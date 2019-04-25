# Aggregate Jacoco Coverage reports for Unit and Integration tests

Big thanks to: https://stackoverflow.com/a/41625080/4841710 (Chad Van De Hey)

## What is in this project

An example of how to (possibly) aggregate Jacoco coverage reports from Unit tests and Integration tests (run in test containers)
and push the result to SoarQube.

To run all tests:

`mvn clean verify`

To push to SonarQube:

`mvn sonar:sonar`
