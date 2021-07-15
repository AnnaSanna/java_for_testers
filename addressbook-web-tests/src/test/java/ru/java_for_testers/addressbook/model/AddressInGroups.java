package ru.java_for_testers.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@XStreamAlias("group")
@Entity
@Table(name = "address_in_groups")
public class AddressInGroups {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "group_id")
  private int groupId;

  public int getId() {
    return id;
  }

  public int getGroupId() {
    return groupId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AddressInGroups that = (AddressInGroups) o;

    if (id != that.id) return false;
    return groupId == that.groupId;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + groupId;
    return result;
  }
}
