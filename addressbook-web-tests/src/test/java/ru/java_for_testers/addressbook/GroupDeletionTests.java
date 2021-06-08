package ru.java_for_testers.addressbook;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.gotoGroupPage();
    app.selectGroupOrContact("selected[]");
    app.deleteSelectedGroups("delete");
    app.returnToGroupPage();
  }

}
