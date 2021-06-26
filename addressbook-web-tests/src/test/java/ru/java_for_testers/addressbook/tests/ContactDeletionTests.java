package ru.java_for_testers.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.java_for_testers.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("first name", "last name", "address", "telephone", "email", "test1"), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectGroupOrContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts("delete");
    app.getContactHelper().closeAlertWindow();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }

}
