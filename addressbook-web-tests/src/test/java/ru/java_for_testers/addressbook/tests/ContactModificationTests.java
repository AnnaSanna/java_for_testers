package ru.java_for_testers.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_for_testers.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();

    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("first name", "last name", "address", "telephone", "email", "test1"), true);
    }
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification("edit");

    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"first name", "last name", "address", "telephone", "email", null);

    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification("update");
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }
}
