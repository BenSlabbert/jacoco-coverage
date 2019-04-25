package io.github.benslabbert.coverage.temp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerHome {

  @GetMapping("/home")
  public String get() {
    return "hello";
  }
}
