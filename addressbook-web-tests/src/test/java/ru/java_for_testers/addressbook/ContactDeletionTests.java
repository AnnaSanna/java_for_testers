package ru.java_for_testers.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.selectGroupOrContact("selected[]");
    app.deleteSelectedContacts(By.xpath("//input[@value='Delete']"));
    app.closeAlertWindow();
    app.returnToHomePage();
  }

}
