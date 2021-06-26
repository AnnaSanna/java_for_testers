package ru.java_for_testers.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.java_for_testers.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();

    ContactData contact = new ContactData()
              .withFirstName("first name").withLastName("last name").withAddress("address").withTelephone("telephone").withEmail("email").withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);

  }

}
