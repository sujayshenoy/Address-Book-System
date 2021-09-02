import java.util.*;

public class AddressBook {
    private static AddressBook instance;

    private List<Contact> contacts = new ArrayList<Contact>();

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
    
    public List<Contact> getAddressBook() {
        return contacts;
    }

}
