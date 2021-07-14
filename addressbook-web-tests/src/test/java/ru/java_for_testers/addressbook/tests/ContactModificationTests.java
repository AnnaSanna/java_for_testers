package ru.java_for_testers.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_for_testers.addressbook.model.ContactData;
import ru.java_for_testers.addressbook.model.Contacts;

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
    Contacts before = app.db().contacts();
  //  File photo = new File("src/test/resources/photo.jpg");
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("first name")
            .withLastName("last name")
            .withAddress("address")
            .withHomePhone("homePhone")
            .withMobilePhone("mobilePhone")
            .withWorkPhone("workPhone")
            .withEmail("email")
            .withEmail2("email2")
            .withEmail3("email3");
//            .withGroup("test1")
//            .withPhoto(photo);
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
