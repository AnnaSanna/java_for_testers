package ru.java_for_testers.addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroupOrContact("selected[]");
    app.getGroupHelper().deleteSelectedGroups("delete");
    app.getGroupHelper().returnToGroupPage();
  }

}
