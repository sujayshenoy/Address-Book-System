import java.util.*;

public class AddressBook {
    private Set<Contact> contacts = new HashSet<Contact>();

    public void addContact(Contact c) {
        contacts.add(c);
    }
    
    public Set<Contact> getAddressBook() {
        return contacts;
    }
    
}
