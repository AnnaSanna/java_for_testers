package ru.java_for_testers.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_for_testers.addressbook.model.ContactData;
import ru.java_for_testers.addressbook.model.Contacts;
import ru.java_for_testers.addressbook.model.GroupData;
import ru.java_for_testers.addressbook.model.Groups;


import java.io.File;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("first name"), true);
    }
  }

  @Test
  public void testContactModification() {

    //  Contacts before = app.contact().all();
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/photo.jpg");
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("first name")
            .withLastName("last name")
            .withAddress("address")
            .withTelephone("telephone")
            .withEmail("email")
            .withGroup("test1")
            .withPhoto(photo);
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    //   Contacts after = app.contact().all();
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }

}
