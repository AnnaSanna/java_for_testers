package ru.java_for_testers.addressbook;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.initContactCreation("add new");
    app.fillContactForm(new ContactData("first name", "last name", "address", "telephone", "email"));
    app.submitContactCreation("(//input[@name='submit'])[2]");
    app.returnToHomePage();
  }

}
