package ru.java_for_testers.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.java_for_testers.addressbook.model.ContactData;
import ru.java_for_testers.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().initContactModification("edit");
    app.getContactHelper().fillContactForm(new ContactData("first name", "last name", "address", "telephone", "email"));
    app.getContactHelper().submitContactModification("update");
    app.getNavigationHelper().returnToHomePage();
  }
}
