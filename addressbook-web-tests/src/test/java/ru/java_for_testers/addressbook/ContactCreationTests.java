package ru.java_for_testers.addressbook;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation("add new");
    fillContactForm(new ContactData("first name", "last name", "address", "telephone", "email"));
    submitContactCreation("(//input[@name='submit'])[2]");
    returnToHomePage();
  }

}
