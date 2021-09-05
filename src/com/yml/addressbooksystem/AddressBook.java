import java.util.*;

public class AddressBook {
    private static AddressBook instance;
    private Set<Contact> contacts = new HashSet<Contact>();

    private AddressBook(){

    }

    public static AddressBook getInstance(){
        if(instance == null){
            instance = new AddressBook();
        }
        return instance;
    }

    public void addContact(Contact c) {
        contacts.add(c);
    }
    
    public Set<Contact> getAddressBook() {
        return contacts;
    }

    
}
