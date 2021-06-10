package ru.java_for_testers.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.java_for_testers.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation(String s) {
    click(By.xpath(s));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getTelephone());
    type(By.name("email"), contactData.getEmail());
  }

  public void initContactCreation(String s) {
    click(By.linkText(s));
  }

  public void closeAlertWindow() {
    closeWindow();
  }

  public void deleteSelectedContacts(By xpath) {
    click(xpath);
  }

  public void selectGroupOrContact(String s) {
    click(By.name(s));
  }

  public void initContactModification(By xpath) {
    click(xpath);
  }

  public void submitContactModification(By xpath) {
    click(xpath);
  }
}
