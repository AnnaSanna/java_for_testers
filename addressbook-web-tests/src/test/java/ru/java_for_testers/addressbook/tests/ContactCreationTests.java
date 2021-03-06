package ru.java_for_testers.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.java_for_testers.addressbook.model.ContactData;
import ru.java_for_testers.addressbook.model.Contacts;
import ru.java_for_testers.addressbook.model.GroupData;
import ru.java_for_testers.addressbook.model.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
   try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
     String json = "";
     String line = reader.readLine();
     while (line != null) {
       json += line;
       line = reader.readLine();
     }
     Gson gson = new Gson();
     List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
     return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
   }
  }


 // @Test(dataProvider = "validContactsFromJson")
  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();

 //   File photo = new File("src/test/resources/photo.jpg");
    ContactData newContact = new ContactData()
            .withFirstName("first name")
            .withLastName("last name")
            .withAddress("address")
            .withHomePhone("homePhone")
            .withMobilePhone("mobilePhone")
            .withWorkPhone("workPhone")
            .withEmail("email")
            .withEmail2("email2")
            .withEmail3("email3")
            .inGroup(groups.iterator().next());

    app.contact().create(newContact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/photo.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

  @Test(enabled = false)
  public void testBadContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();

    ContactData contact = new ContactData()
            .withFirstName("first name'").withLastName("last name").withAddress("address").withTelephone("telephone").withEmail("email");
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

}
