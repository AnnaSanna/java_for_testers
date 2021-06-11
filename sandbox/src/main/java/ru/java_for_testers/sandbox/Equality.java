package ru.java_for_testers.sandbox;

public class Equality {

   public static void maun(String[] args) {
     String s1 = "firefox";
     String s2 = s1;

     System.out.println(s1 == s2);
     System.out.println(s1.equals(s2));
   }
}
