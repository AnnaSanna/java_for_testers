package ru.java_for_testers.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void test1() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);

    Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
  }

  public void test2() {
    Point p1 = new Point(1, 1);
    Point p2 = new Point(1, 1);

    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  public void test3() {
    Point p1 = new Point(-1, -1);
    Point p2 = new Point(0, 0);

    Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
  }
}
