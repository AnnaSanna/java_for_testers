package ru.java_for_testers.addressbook.tests;

import org.testng.annotations.*;
import ru.java_for_testers.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation("add new");
    app.getContactHelper().fillContactForm(new ContactData("first name", "last name", "address", "telephone", "email"));
    app.getContactHelper().submitContactCreation("(//input[@name='submit'])[2]");
    app.getNavigationHelper().returnToHomePage();
  }

}
