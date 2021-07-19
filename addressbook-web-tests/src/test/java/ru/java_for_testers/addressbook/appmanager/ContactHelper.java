package ru.java_for_testers.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.java_for_testers.addressbook.model.ContactData;
import ru.java_for_testers.addressbook.model.Contacts;

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
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
//    type(By.name("home"), contactData.getTelephone());
//    type(By.name("email"), contactData.getEmail());
//    attach(By.name("photo"), contactData.getPhoto());
    if (creation) {
      if (contactData.getGroups().size() > 0 ) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
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

  public void selectGroupOrContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    //wd.findElement(By.cssSelector(String.format("a[@href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification(String update) {
    click(By.xpath("//input[@name='update']"));
  }

  public void create(ContactData contact, boolean b) {
    initContactCreation("add new");
    fillContactForm(contact, true);
    submitContactCreation("(//input[@name='submit'])[2]");
    contactCache = null;
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification("update");
    contactCache = null;
    returnToHomePage();
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.tagName("tr"));
    for (WebElement row : rows.subList(1, rows.size())) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();

      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
      .withAllPhones(allPhones)
      .withAllEmails(allEmails)
      .withAddress(address));
    }
    return new Contacts(contactCache);
  }

  public void delete(ContactData contact) {
    selectGroupOrContactById(contact.getId());
    deleteSelectedContacts("delete");
    closeAlertWindow();
    contactCache = null;
    returnToHomePage();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");

    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withAddress(address);
  }

  public void addContactToGroup(int contactId) {
    selectGroupOrContactById(contactId);
    wd.findElement(By.name("add")).click();
  }

  public void deleteContactFromGroup(int contactId) {
    selectGroupOrContactById(contactId);
    wd.findElement(By.name("remove")).click();
  }
}
