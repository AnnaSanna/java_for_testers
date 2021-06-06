package ru.java_for_testers.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    selectGroupOrContact("selected[]");
    deleteSelectedContacts(By.xpath("//input[@value='Delete']"));
    closeAlertWindow();
    returnToHomePage();
  }

}
