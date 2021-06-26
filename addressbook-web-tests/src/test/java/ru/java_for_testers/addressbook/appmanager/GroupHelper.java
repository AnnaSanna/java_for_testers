package ru.java_for_testers.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.java_for_testers.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("groups"));
  }

  public void submitGroupCreation(String submit) {
    click(By.name(submit));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation(String s) {
    click(By.name(s));
  }

  public void deleteSelectedGroups(String delete) {
    click(By.name(delete));
  }

  public void selectGroupOrContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initGroupModification(String edit) {
    click(By.name(edit));
  }

  public void submitGroupModification(String update) {
    click(By.name(update));
  }

  public void create(GroupData group) {
    initGroupCreation("new");
    fillGroupForm(group);
    submitGroupCreation("submit");
    returnToGroupPage();
  }

  public void modify(int index, GroupData group) {
    selectGroupOrContact(index);
    initGroupModification("edit");
    fillGroupForm(group);
    submitGroupModification("update");
    returnToGroupPage();
  }

  public void delete(int index) {
    selectGroupOrContact(index);
    deleteSelectedGroups("delete");
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<>();
    List <WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }
}
