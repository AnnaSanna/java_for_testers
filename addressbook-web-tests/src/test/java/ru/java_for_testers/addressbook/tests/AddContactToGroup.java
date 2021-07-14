package ru.java_for_testers.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_for_testers.addressbook.model.*;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase{



  @BeforeMethod
  public void ensurePreconditions() {
//    if (app.db().contacts().size() == 0) {
//      app.goTo().homePage();
//      app.contact().create(new ContactData().withFirstName("first name"), true);
//    }
  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().homePage();
    Contacts beforeContacts = app.db().contacts();
    ContactData contactToAdd = beforeContacts.iterator().next();

    Groups beforeGroups = app.db().groups();
    GroupData groupToAdd = beforeGroups.iterator().next();

    app.contact().selectGroupOrContactById(contactToAdd.getId());
    app.wd.findElement(By.name("add")).click();
 //   new Select(app.wd.findElement(By.name("add"))).selectByVisibleText(groupToAdd.getName());
    Addresses afterAddresses = app.db().addressInGroups();
    AddressInGroups created = afterAddresses
            .stream()
            .filter(s -> s.getId() == contactToAdd.getId() && s.getGroup_id() == groupToAdd.getId())
            .collect(Collectors.toList()).get(0);

    assertThat(created, notNullValue());

  }
}
