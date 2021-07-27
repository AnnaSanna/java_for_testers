package ru.stqa.pft.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

public class TestBase {

  boolean isIssueOpen(int issueId) {
    String json = RestAssured.get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)).asString();
//    if (json.contains("\"state_name\":\"Open\"")) {
//      return true;
//    } else {
//      return false;
//    }
    JsonElement parsed = new JsonParser().parse(json);
    JsonArray issues = parsed.getAsJsonObject().getAsJsonArray("issues");
    if (issues.get(0).getAsJsonObject().get("state_name").getAsString().equals("Open")) {
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
