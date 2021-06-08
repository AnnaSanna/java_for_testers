package ru.java_for_testers.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase{

  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String user, String pass, By xpath, String username, String password) {
    type(By.name(user), username);
    type(By.name(pass), password);
    click(xpath);
  }

  public void logout(String logout) {
    click(By.linkText(logout));
  }
}
