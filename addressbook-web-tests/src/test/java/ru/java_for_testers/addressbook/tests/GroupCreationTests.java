package ru.java_for_testers.addressbook.tests;

import org.testng.annotations.*;
import ru.java_for_testers.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation("new");
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupCreation("submit");
    app.getGroupHelper().returnToGroupPage();
    app.getSessionHelper().logout("Logout");
  }

}