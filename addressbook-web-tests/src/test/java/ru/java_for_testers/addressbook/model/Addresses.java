package ru.java_for_testers.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Addresses extends ForwardingSet<AddressInGroups> {

  private Set<AddressInGroups> delegate;

  public Addresses(Addresses addresses) {
    this.delegate = new HashSet<AddressInGroups>(addresses.delegate);
  }

  public Addresses() {
    this.delegate = new HashSet<AddressInGroups>();
  }

  public Addresses(Collection<AddressInGroups> addresses) {
    this.delegate = new HashSet<AddressInGroups>(addresses);
  }

  @Override
  protected Set<AddressInGroups> delegate() {
    return delegate;
  }

  public Addresses withAdded(AddressInGroups contact) {
    Addresses addresses = new Addresses(this);
    addresses.add(contact);
    return addresses;
  }

  public Addresses without(AddressInGroups contact) {
    Addresses addresses = new Addresses(this);
    addresses.remove(contact);
    return addresses;
  }
}
