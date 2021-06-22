package ru.java_for_testers.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_for_testers.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("first name", "last name", "address", "telephone", "email", "test1"), true);
    }
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().initContactModification("edit");
    app.getContactHelper().fillContactForm(new ContactData("first name", "last name", "address", "telephone", "email", null), false);
    app.getContactHelper().submitContactModification("update");
    app.getNavigationHelper().goToHomePage();
  }
}
