package ru.java_for_testers.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    // System.out.println("haaaaaaaaaaaay");
    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);

    System.out.println("(функция) Расстояние между точками = " + distance(p1, p2));
    System.out.println("(метод) Расстояние между точками = " + p1.distance(p2));
  }

    public static double distance(Point p1, Point p2) {
      return Math.sqrt((p2.a - p1.a) * (p2.a - p1.a) + (p2.b - p1.b) * (p2.b - p1.b));
    }


  }