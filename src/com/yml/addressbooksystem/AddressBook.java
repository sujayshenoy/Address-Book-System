import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBook {
    private List<Contact> contacts = new ArrayList<Contact>();

    public void addContact(Contact c) {
        List<Contact> existsInAddressBook = contacts.stream().filter(contact->{
            if (contact.equals(c)) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        if (existsInAddressBook.size() == 0) {
            contacts.add(c);
        }
        else {
            System.out.println("Contact Already exists");
        }
    }
    
    public List<Contact> getAddressBook() {
        return contacts;
    }
    
}
