package io.github.benslabbert.coverage.pack;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PackerTest {

  @Test
  public void test() {

    Packer p = new Packer();
    boolean pack = p.pack();
    assertTrue(pack);
  }
}
