package ru.java_for_testers.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.java_for_testers.addressbook.model.ContactData;
import ru.java_for_testers.addressbook.model.GroupData;

public class TestContactModification extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().initContactModification(By.xpath("//img[@alt='Edit']"));
    app.getContactHelper().fillContactForm(new ContactData("first name", "last name", "address", "telephone", "email"));
    app.getContactHelper().submitContactModification(By.xpath("//input[@name='update']"));
    app.getNavigationHelper().returnToHomePage();
  }
}
