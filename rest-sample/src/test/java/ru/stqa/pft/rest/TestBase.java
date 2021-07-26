package ru.stqa.pft.rest;

import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

public class TestBase {

  boolean isIssueOpen(int issueId) {
    String json = RestAssured.get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)).asString();
    if (json.contains("\"state_name\":\"Open\"")) {
      return true;
    } else {
      return false;
    }
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
