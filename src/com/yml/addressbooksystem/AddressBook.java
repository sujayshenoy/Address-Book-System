import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    
    public void saveToFile(String fileName) throws IOException {
        File file = new File("data/" + fileName + ".txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);

        for (Contact contact : contacts) {
            fileWriter.write(contact+"\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
