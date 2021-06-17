package ru.java_for_testers.addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.java_for_testers.addressbook.model.ContactData;
import ru.java_for_testers.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("first name", "last name", "address", "telephone", "email", "test1"), true);
    }
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectGroupOrContact("selected[]");
    app.getContactHelper().deleteSelectedContacts("delete");
    app.getContactHelper().closeAlertWindow();
    app.getNavigationHelper().goToHomePage();
  }

}
