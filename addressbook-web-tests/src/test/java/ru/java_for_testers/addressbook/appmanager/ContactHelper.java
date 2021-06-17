package ru.java_for_testers.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.java_for_testers.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation(String s) {
    click(By.xpath(s));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getTelephone());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new group")));
    }
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

  public void initContactModification(String edit) {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification(String update) {
    click(By.xpath("//input[@name='update']"));
  }
}
