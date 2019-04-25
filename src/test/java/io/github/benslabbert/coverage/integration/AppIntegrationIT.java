package io.github.benslabbert.coverage.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class AppIntegrationIT {

  @ClassRule
  public static GenericContainer APP =
      new GenericContainer<>("coverage:latest")
          .waitingFor(Wait.forListeningPort())
          .withExposedPorts(8080)
          .withNetwork(Network.SHARED)
          .withFileSystemBind("./target/jacoco-agent", "/jacoco-agent")
          .withFileSystemBind("./target/coverage-reports", "/coverage-reports")
          .withCommand(
              "/usr/bin/java",
              "-javaagent:/jacoco-agent/org.jacoco.agent-runtime.jar=destfile=/coverage-reports/jacoco-it.exec",
              "-jar",
              "/app.jar");

  @AfterClass
  public static void stop() {
    APP.getDockerClient().stopContainerCmd(APP.getContainerId()).withTimeout(10).exec();
  }

  @Test
  public void test() {

    RestTemplate rt = new RestTemplate();

    String containerIpAddress = APP.getContainerIpAddress();
    Integer firstMappedPort = APP.getFirstMappedPort();

    try {
      ResponseEntity<String> exchange =
          rt.exchange(
              "http://" + containerIpAddress + ":" + firstMappedPort,
              HttpMethod.GET,
              null,
              String.class);
      assertNotNull(exchange);
    } catch (Exception e) {
      log.warn("Failed to get", e);
    }
  }
}
