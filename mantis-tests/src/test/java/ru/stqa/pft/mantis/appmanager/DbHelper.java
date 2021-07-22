package ru.stqa.pft.mantis.appmanager;

import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {

  // private final SessionFactory sessionFactory;
  private final ApplicationManager app;

  public DbHelper(ApplicationManager app) {
    /* A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();*/
    this.app = app;
  }

  public List<String> getUser() {
    List<String> usersData = new ArrayList<>();
    Connection connection = null;
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" +
              "user=root&password=");
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT USERNAME, EMAIL " + "FROM mantis_user_table " + "WHERE USERNAME != 'administrator'" + "LIMIT 1");
      resultSet.next();
      usersData.add(resultSet.getString("USERNAME"));
      usersData.add(resultSet.getString("EMAIL"));
      resultSet.close();
      statement.close();
      connection.close();
    } catch (SQLException ex) {
      // handle any errors
      //System.out.println("SQLException: " + ex.getMessage());
      //System.out.println("SQLState: " + ex.getSQLState());
      //System.out.println("VendorError: " + ex.getErrorCode());
    }
    return usersData;
  }
}
