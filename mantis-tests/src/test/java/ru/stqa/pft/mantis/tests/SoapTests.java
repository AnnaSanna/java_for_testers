package ru.stqa.pft.mantis.tests;

import com.google.protobuf.ServiceException;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{

  @Test
  public void testGetProject() throws MalformedURLException, RemoteException, ServiceException, javax.xml.rpc.ServiceException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
      System.out.println(project.getId());
    }
  }

  @Test
  public void testCreatedIssue() throws MalformedURLException, RemoteException, ServiceException, javax.xml.rpc.ServiceException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("summary").withDescription("description")
            .withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }
}
