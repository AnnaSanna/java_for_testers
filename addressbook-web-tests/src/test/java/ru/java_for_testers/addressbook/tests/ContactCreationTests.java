package ru.java_for_testers.addressbook.tests;

import org.testng.annotations.*;
import ru.java_for_testers.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactData("first name", "last name", "address", "telephone", "email", "test1"), true);
    app.getNavigationHelper().goToHomePage();
  }

}
