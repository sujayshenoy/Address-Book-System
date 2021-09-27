import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.ObjectUtils;

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

    public void saveCsvToFile(String fileName) throws IOException {
        File file = new File("data/" + fileName + ".csv");
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter writer = new CSVWriter(fileWriter);
        file.createNewFile();

        String[] header = { "firstName", "lastName", "address", "city", "state", "zip", "email", "phone" };
        writer.writeNext(header);
        for (Contact contact : contacts) {
            String[] contactData = { contact.getFirstName(), contact.getLastName(), contact.getAddress(),
                    contact.getCity(), contact.getState(), Integer.toString(contact.getZip()), contact.getEmail(),
                    contact.getPhone().toString() };
            writer.writeNext(contactData);
        }
        writer.flush();
        writer.close();
    }
    
    public void readCsvFile(String fileName) throws IOException, CsvException {
        File file = new File("data/" + fileName + ".csv");
        PrintWriter out = new PrintWriter(System.out,true);
        FileReader fileReader = new FileReader(file);
        CSVReader reader = new CSVReader(fileReader);

        List<String[]> data = reader.readAll();
        for (String[] line : data) {
            for (String word : line) {
                out.print(word + " ");
                out.flush();
            }
            out.println();
        }
        reader.close();
    }
}
