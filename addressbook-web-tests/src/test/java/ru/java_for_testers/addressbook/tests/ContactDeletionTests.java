package ru.java_for_testers.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.java_for_testers.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstName("first name"), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;

    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);

  }

}
