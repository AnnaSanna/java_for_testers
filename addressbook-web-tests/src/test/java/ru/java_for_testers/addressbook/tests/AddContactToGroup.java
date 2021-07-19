package ru.java_for_testers.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_for_testers.addressbook.model.*;
import java.util.Comparator;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {

  ContactData contact;
  GroupData group;

  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    if (groups.size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    Contacts contacts = app.db().contacts();
    Optional<ContactData> contactOpt = contacts.stream().filter(c -> c.getGroups().size() < groups.size()).findFirst();
    if(contactOpt.isPresent()) contact = contactOpt.get();
    else {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("first name"), true);
      Contacts allContacts = app.db().contacts();
      int lastId = allContacts.stream().map(c -> c.getId()).max(Comparator.naturalOrder()).get();
      contact = allContacts.stream().filter(c -> c.getId() == lastId).findAny().get();
    }

    Groups allGroups = app.db().groups();
    group = allGroups.stream().filter(g -> !g.getContacts().contains(contact)).findAny().get();
  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().homePage();
    app.contact().addContactToGroup(contact.getId(), group.getName());

    ContactData updatedContact = app.db().contacts().stream().filter(c -> c.getId() == contact.getId()).findFirst().get();
    assertThat(contact.getGroups().withAdded(group), equalTo(updatedContact.getGroups()));
  }
}
