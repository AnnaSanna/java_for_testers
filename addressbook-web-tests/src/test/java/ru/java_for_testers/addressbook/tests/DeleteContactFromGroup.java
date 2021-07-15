package ru.java_for_testers.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_for_testers.addressbook.model.*;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().addressInGroups().size() == 0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1"));
      }
      if (app.db().contacts().size() == 0) {
        app.goTo().homePage();
        app.contact().create(new ContactData().withFirstName("first name"), true);
      }
      app.goTo().homePage();
      ContactData contactToAdd = app.db().contacts().iterator().next();
      app.contact().addContactToGroup(contactToAdd.getId());
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    app.goTo().homePage();
    Addresses before = app.db().addressInGroups();
    AddressInGroups deleteContact = app.db().addressInGroups().iterator().next();
    String groupName = app.db().groups().stream().filter(g -> g.getId() == deleteContact.getGroupId()).collect(Collectors.toList()).get(0).getName();

    new Select(app.wd.findElement(By.name("group"))).selectByVisibleText(groupName);
    app.contact().selectGroupOrContactById(deleteContact.getId());
    app.wd.findElement(By.name("remove")).click();

    Addresses after = app.db().addressInGroups();
    assertThat(after, equalTo(before.without(deleteContact)));
  }
}
