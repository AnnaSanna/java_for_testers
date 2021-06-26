package ru.java_for_testers.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.java_for_testers.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("first name"), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);

  }

}
