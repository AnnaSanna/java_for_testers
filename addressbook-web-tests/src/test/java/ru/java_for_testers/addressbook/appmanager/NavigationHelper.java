package ru.java_for_testers.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
  private WebDriver wd;

  public NavigationHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void returnToHomePage() {
    //wd.findElement(By.linkText("s")).click();
    wd.findElement(By.linkText("home")).click();
  }
}
