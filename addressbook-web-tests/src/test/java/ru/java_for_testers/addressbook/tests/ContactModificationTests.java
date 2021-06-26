package ru.java_for_testers.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_for_testers.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("first name"), true);

    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(deletedContact.getId()).withFirstName("first name").withLastName("last name").withAddress("address").withTelephone("telephone").withEmail("email").withGroup("test1");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(deletedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
