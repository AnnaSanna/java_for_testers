package ru.java_for_testers.addressbook.tests;

import org.testng.annotations.Test;
import ru.java_for_testers.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroupOrContact("selected[]");
    app.getGroupHelper().initGroupModification("edit");
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupModification("update");
    app.getGroupHelper().returnToGroupPage();
  }
}
