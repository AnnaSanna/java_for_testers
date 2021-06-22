package ru.java_for_testers.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.java_for_testers.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();

    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroupOrContact(before - 1);
    app.getGroupHelper().deleteSelectedGroups("delete");
 //   app.getGroupHelper().returnToGroupPage();
    app.getNavigationHelper().gotoGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);
  }

}
