package ru.java_for_testers.addressbook.tests;

import org.testng.annotations.*;
import ru.java_for_testers.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation("new");
    app.fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.submitGroupCreation("submit");
    app.returnToGroupPage();
    app.logout("Logout");
  }

}
