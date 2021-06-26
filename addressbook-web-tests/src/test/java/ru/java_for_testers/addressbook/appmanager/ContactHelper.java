package ru.java_for_testers.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.java_for_testers.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

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
 //   closeWindow();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    acceptNextAlert = true;
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public String closeAlertAndGetItsText() {
    try {
      Alert alert = wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  public void deleteSelectedContacts(String delete) {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectGroupOrContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModification(String update) {
    click(By.xpath("//input[@name='update']"));
  }

  public void createContact(ContactData contact, boolean b) {
    initContactCreation("add new");
    fillContactForm(contact, true);
    submitContactCreation("(//input[@name='submit'])[2]");
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> rows = wd.findElements(By.tagName("tr"));
    for (WebElement row : rows.subList(1, rows.size())) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
        String lastName = cells.get(1).getText();
        String firstName = cells.get(2).getText();
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstName, lastName, null, null, null, null);
        contacts.add(contact);
    }
    return contacts;
  }
}
